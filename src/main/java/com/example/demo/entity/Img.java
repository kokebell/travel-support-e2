package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "imgs")
public class Img {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Getter
		@Setter
		private Integer id;

		@Getter
		@Setter
		@Column(name="image_id")
		private String imageId;
		
		@Getter
		@Setter
		private String img; // 作成者のID
		


		// コンストラクタ
		public Img() {
			
		}
		
		public Img(String imageId, String img) {
			this.imageId = imageId;
			this.img = img;
		}
		
		
		
}
