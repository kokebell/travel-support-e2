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
@Table(name = "notes")
public class Note {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Getter
		@Setter
		private Integer id; // ID

		@Getter
		@Setter
		@Column(name="author_id")
		private Integer authorId; // 作成者のID
		
		@Getter
		@Setter
		private String title; // タイトル

		@Getter
		@Setter
		private String memo; // コメント

		@Getter
		@Setter
		@Column(name="added_date")
		private String addedDate; // 作成日時
		
		@Getter
		@Setter
		@Column(name="updated_date")
		private String updatedDate; // 更新日時
		
		@Getter
		@Setter
		private String article; // 記事
		
		@Getter
		@Setter
		@Column(name="img_id")
		private String imgId; //画像
		
//		@Getter
//		@Setter
//		private String img2; //2枚目の画像
//		
//		@Getter
//		@Setter
//		private String img3; //3枚目の画像

		// コンストラクタ
		public Note() {
			
		}
		
		public Note(Integer authorId, String title, String memo, String updatedDate, String imgId) {
			this.authorId = authorId;
			this.title = title;
			this.memo = memo;
			this.updatedDate = updatedDate;
			this.imgId = imgId;
		}
		
		public Note(Integer authorId, String title, String memo, String addedDate, String updatedDate, String article, String imgId) {
			this.authorId = authorId;
			this.title = title;
			this.memo = memo;
			this.addedDate = addedDate;
			this.updatedDate = updatedDate;
			this.article = article;
			this.imgId = imgId;
		}
		
		
		
}
