package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "prefs")
public class Pref {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Getter
		private Integer id; // ID

		@Getter
		private String name; // 名前


		// コンストラクタ
		public Pref() {
			
		}

		public Pref(String name) {
			this.name = name;
		}
		
}
