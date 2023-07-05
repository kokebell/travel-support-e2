package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
public class User {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Getter
		@Setter
		private Integer id; // ID

		@Getter
		@Setter
		private String name; // 名前

		@Getter
		@Setter
		private String address; // 住所

		@Getter
		@Setter
		private String tel; // 電話番号

		@Getter
		@Setter
		private String email; // メールアドレス
		
		@Getter
		@Setter
		private String password;

		// コンストラクタ
		public User() {
			
		}

		public User(String email) {
			this.email = email;
		}
		
		public User(String name, String address, String tel, String email, String password) {
			this.name = name;
			this.address = address;
			this.tel = tel;
			this.email = email;
			this.password = password;
		}
		
		public User(String name, String address, String tel, String password) {
			this.name = name;
			this.address = address;
			this.tel = tel;
			this.password = password;
		}
		
		
}
