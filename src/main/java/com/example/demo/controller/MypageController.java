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

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import java.util.Optional;

@Controller
public class MypageController {
	
	@Autowired
	UserRepository userRepository;
    
	//「マイページ」の表示
    @GetMapping("/mypage/{email}")
    String index(
    		@PathVariable("email")String email,
    		Model model) {
    	Optional<User> userInfo = userRepository.findByEmail(email);
    	model.addAttribute("userInfo", userInfo.get());
    	
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
    
}