package com.example.demo.controller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Img;
import com.example.demo.entity.Note;
import com.example.demo.entity.User;
import com.example.demo.model.FileForm;
import com.example.demo.repository.NoteRepository;
import com.example.demo.repository.UserRepository;

@Controller
public class MypageController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	NoteRepository noteRepository;
    
	//「マイページ」の表示
    @GetMapping("/mypage/{uid}")
    String index(
    		@PathVariable("uid")Integer uid,
    		Model model) {
    	Optional<User> userInfo = userRepository.findById(uid);
    	User user = userInfo.get();
    	model.addAttribute("userInfo", user);
    	List<Note> noteList = noteRepository.findAllByAuthorId(uid);
    	model.addAttribute("noteList", noteList);
        return "mypage";
    }
    
    //「アカウント情報の編集」画面の表示
    @GetMapping("/editaccount/{email}")
    String show(
    		@PathVariable("email")String email,
    		Model model){
    	Optional<User> userInfo = userRepository.findByEmail(email);
    	model.addAttribute("userInfo", userInfo.get());
    	return "editInfo";
    }
    
    //アカウントの編集
    @PostMapping("/editaccount/{email}")
    String update(
    		@PathVariable("email")String email,
    		@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "address", required = false) String address,
			@RequestParam(name = "tel", required = false) String tel,
			@RequestParam(name = "password", required = false) String password,
    		Model model){
    	
    	Optional<User> userInfo = userRepository.findByEmail(email);
    	model.addAttribute("userInfo", userInfo.get());
    	User newUserInfo = userInfo.get();
    	
    	
    	List<String> messages = new ArrayList<>();

		if (name == null || name.equals("")) {
			messages.add("名前は必須です");
		}

		if (address == null || address.equals("")) {
			messages.add("住所は必須です");
		}

		if (tel == null || tel.equals("")) {
			messages.add("電話番号は必須です");
		}

		if (password == null || password.equals("")) {
			messages.add("パスワードは必須です");
		}

		if (messages.size() > 0) {
			model.addAttribute("messages", messages);
			System.out.println(messages);
			model.addAttribute("name", name);
			model.addAttribute("address", address);
			model.addAttribute("tel", tel);
			model.addAttribute("password", password);
			return "editInfo";
		}
		newUserInfo.setName(name);
		newUserInfo.setAddress(address);
		newUserInfo.setTel(tel);
		newUserInfo.setPassword(password);
		userRepository.save(newUserInfo);
		model.addAttribute("email", email);

		return "mypage";
    }
    
    //「マイページの詳細画面」の表示
    @GetMapping("/note/{authorId}/{noteId}")
    String show(
    		@PathVariable("authorId")Integer authorId,
    		@PathVariable("noteId")Integer noteId,
    		Model model) {
    	Optional<Note> noteInfo = noteRepository.findByAuthorIdAndId(authorId, noteId);
    	
    	model.addAttribute("noteInfo", noteInfo);
        return "noteDetail";
    }
    
    //「トラベルノートの追加」画面を表示する
    @GetMapping("/addnote/{uid}")
    String addnote(
    		@PathVariable("uid")Integer uid,
    		Model model){
    	model.addAttribute("uid", uid);
    	model.addAttribute("fileForm", new FileForm());
    	
    	return "addNote";
    }
    
    //トラベルノートを追加する
    @PostMapping("/savenote/{uid}")
    String save(
    		@PathVariable("uid")Integer uid,
    		@RequestParam(name="title", required=false) String title,
    		@RequestParam(name="memo", required=false) String memo,
    		@RequestParam(name="article", required=false) String article,
    		@RequestParam(name="pathForSave", required=false) List<String> img,
    		Model model){
    	
    	//追加日時、更新日時を設定するための現在時刻、
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
    	LocalDateTime ldt = LocalDateTime.now();
    	String added_date = ldt.format(dtf);
    	
    	//トラベルノートを新規作成する場合に必要なデータを用意する
    	Note newNote = new Note(); 
    	newNote.setAuthorId(uid);
    	newNote.setTitle(title);
    	newNote.setMemo(memo);
    	newNote.setAddedDate(added_date);
    	newNote.setUpdatedDate(added_date);
    	newNote.setArticle(article);
    	Integer noteId = newNote.getId();
    	newNote.setImgId(noteId.toString() + uid);
    	
    	List<Img> newImg = new ArrayList<>();
    	newImg.setImageId(noteId.toString() + uid);
    	
    	
    	//トラベルノートをデータベースに登録する
    	noteRepository.save(newNote);
    	
    	model.addAttribute("uid", uid);
    	
    	return "redirect:/mypage/" + uid ;
    }
    
    @PostMapping("/uploadimgfile/{uid}")
    String up(
    		@PathVariable("uid")String uid,
    		@RequestParam(name="title", required=false) String title,
    		@RequestParam(name="memo", required=false) String memo,
    		@RequestParam(name="article", required=false) String article,
    		@RequestParam(name="img", required=false) String[] img,
    		Model model, FileForm fileForm) {
    	List<String> pathForSave = new ArrayList<>();
        List<MultipartFile> mfile = fileForm.getMultipartFile();
        mfile.forEach( f -> { 
        	//ファイル名を現在時刻で初期化する
        	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        	LocalDateTime ldt = LocalDateTime.now();
        	String initializedName = ldt.format(dtf);
        	System.out.println(initializedName);
        	//ファイル名の初期化に用いるファイルフォーマットを取得する
        	String fileName = f.getOriginalFilename();
        	fileName.indexOf(".");
        	String fileFormat = fileName.substring(fileName.indexOf("."));
        	//指定した文字列からファイルパスを作成する
        	Path filePath = Paths.get("C:/pleiades/2022-12/workspace/travel-support-e2/src/main/resources/static/img/" + initializedName + fileFormat);
        	//ファイルを取得する
        	try {
            	Files.copy(f.getInputStream(), filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
//        	File fileImg = new File(filePath.toString());
//    		String str = fileImg.getAbsolutePath();
//    		System.out.println("path : " + str);
        	String shorterPath = "/img/" + initializedName + fileFormat;
        	System.out.println(shorterPath);
    		pathForSave.add(shorterPath);
    		
        });
        
        model.addAttribute("uid", uid);
        model.addAttribute("title", title);
        model.addAttribute("memo", memo);
        model.addAttribute("article", article);
        model.addAttribute("pathForSave", pathForSave);
        model.addAttribute("img", img);
        
        try {
        	Thread.sleep(5000);
		} catch (InterruptedException e) {
			
		}
        
        return "confirmNote";
    }
    
}