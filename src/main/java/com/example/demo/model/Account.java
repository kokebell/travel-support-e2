package com.example.demo.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Data;

@SessionScope
@Component
@Data
public class Account {
	private String name; // 名前
	private String email; // メールアドレス

}
