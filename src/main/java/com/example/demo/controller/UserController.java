package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.model.Account;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	
	
	@Autowired
	HttpSession session;

	@Autowired
	Account account;

	@Autowired
	UserRepository userRepository;

	//ログイン画面を表示
	@GetMapping({"/login", "/logout"})
	public String index(
			Model model) {
		session.invalidate();
		return "login";
	}

	// ログイン処理
	@PostMapping("/login")
	public String login(
			@RequestParam(name = "EMAIL", required = false) String email,
			@RequestParam(name = "PASSWORD", required = false) String password,
			Model m) {

		List<String> message = new ArrayList<>();

		if (email == null || email.equals("")) {
			message.add("メールアドレスを入力してください");
		}

		if (password == null || password.equals("")) {
			message.add("パスワードを入力してください");
		}

		email = email == null ? "" : email;
		password = password == null ? "" : password;
		
		if (!email.equals("") && !password.equals("")) {
			// メールアドレスとパスワードが一致しなかった場合エラーとする
			Optional<User> optionalUser = userRepository.findByEmailAndPassword(email, password);

			Optional<User> record = userRepository.findByEmail(email);
			
			if (optionalUser.isEmpty() == false) {
				User user = optionalUser.get();
				account.setName(user.getName());
				account.setEmail(user.getEmail());
				
				return "sanin";
			}else if(record.isEmpty() == false) {
				User user = record.get();
					if(!user.getPassword().equals(password)) {
						message.add("パスワードが異なります");
					}
			} else {
				message.add("登録情報がありません。新規登録してください");
			}
		}


		

//		if (record.isEmpty() == false) {
//			User user = record.get();
//
//			if (!user.getPassword().equals(password)) {
//				message.add("パスワードが異なります");
//			}
//		}

		m.addAttribute("message", message);
		m.addAttribute("email", email);
		m.addAttribute("password", password);

		return "login";
	}

	@GetMapping("/register")
	public String show() {
		return "register";
	}

	//新規登録
	@PostMapping("/register")
	public String store(
			@RequestParam(name = "NAME", required = false) String name,
			@RequestParam(name = "ADDRESS", required = false) String address,
			@RequestParam(name = "TEL", required = false) String tel,
			@RequestParam(name = "EMAIL", required = false) String email,
			@RequestParam(name = "PASSWORD", required = false) String password,
			Model m) {

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

		if (email == null || email.equals("")) {
			messages.add("メールアドレスは必須です");
		} else {
			Optional<User> registored = userRepository.findByEmail(email);

			if (!registored.isEmpty()) {
				messages.add("登録済みのメールアドレスです");
			}
		}

		if (password == null || password.equals("")) {
			messages.add("パスワードは必須です");
		}

		if (messages.size() > 0) {
			m.addAttribute("messages", messages);

			m.addAttribute("name", name);
			m.addAttribute("address", address);
			m.addAttribute("tel", tel);
			m.addAttribute("email", email);
			return "register";
		}

		User user = new User(name, address, tel, email, password);
		userRepository.save(user);

		return "redirect:/login";

	}
	
}
