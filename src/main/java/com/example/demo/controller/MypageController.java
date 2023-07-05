package com.example.demo.controller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Note;
import com.example.demo.entity.Pref;
import com.example.demo.entity.Spot;
import com.example.demo.entity.User;
import com.example.demo.model.FileForm;
import com.example.demo.repository.NoteRepository;
import com.example.demo.repository.UserRepository;

import java.util.Optional;

@Controller
public class MypageController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	NoteRepository noteRepository;
    
	//「マイページ」の表示
    @GetMapping("/mypage/{email}")
    String index(
    		@PathVariable("email")String email,
    		Model model) {
    	Optional<User> userInfo = userRepository.findByEmail(email);
    	User user = userInfo.get();
    	model.addAttribute("userInfo", user);
    	List<Note> noteList = noteRepository.findAllByAuthorId(user.getId());
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

		return "redirect:/mypage/" + email;
    }
    
    //「トラベルノートの追加」画面を表示する
    @GetMapping("/addnote/{email}")
    String addnote(
    		@PathVariable("email")String email,
    		Model model){
    	return "addNote2";
    }
    
    //トラベルノートを追加する
    @PostMapping("/savenote/{email}")
    String save(
    		@PathVariable("email")String email,
    		@RequestParam(name="title", required=false) String title,
    		@RequestParam(name="memo", required=false) String memo,
    		@RequestParam(name="article", required=false) String article,
    		@RequestParam(name="img1", required=false) String img1,
    		Model model){
    	
    	Optional<User> userInfo = userRepository.findByEmail(email);
    	User user = userInfo.get();
    	Integer userId = user.getId();
    	model.addAttribute("userInfo", userInfo.get());
    	//追加日時、更新日時を設定するための現在時刻、
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
    	LocalDateTime ldt = LocalDateTime.now();
    	String added_date = ldt.format(dtf);
    	
    	//トラベルノートを新規作成する場合に必要なデータを用意する
    	Note newNote = new Note(); 
    	newNote.setAuthorId(userId);
    	newNote.setTitle(title);
    	newNote.setMemo(memo);
    	newNote.setAddedDate(added_date);
    	newNote.setUpdatedDate(added_date);
    	newNote.setArticle(article);
    	newNote.setImg1(img1);
    	
    	//トラベルノートをデータベースに登録する
    	noteRepository.save(newNote);
    	
    	return "addNote";
    }
    
    @PostMapping("/uploadimgfile")
    String uploadimgfile(Model model, FileForm fileForm) {
    	List<String> pathForSave = null;
        List<MultipartFile> mfile = fileForm.getMultipartFile();
        mfile.forEach( f -> { 
        	//ファイル名を現在時刻で初期化する
        	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        	LocalDateTime ldt = LocalDateTime.now();
        	String initializedName = ldt.format(dtf);
        	//ファイル名の初期化に用いるファイルフォーマットを取得する
        	String fileName = f.getOriginalFilename();
        	fileName.indexOf(".");
        	String fileFormat = fileName.substring(fileName.indexOf("."));
        	//指定した文字列からファイルパスを作成する
        	Path filePath = Paths.get("C:/pleiades/2022-12/workspace/travel-support-e/src/main/resources/static/img/" + initializedName + fileFormat);
        	//ファイルを取得する
        	try {
            	Files.copy(f.getInputStream(), filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        	
    		String shorterPath = "/img/" + initializedName + fileFormat;
    		pathForSave.add(shorterPath);
        });
        
        model.addAttribute(pathForSave);
        
        return "addNote2";
    }
    
}