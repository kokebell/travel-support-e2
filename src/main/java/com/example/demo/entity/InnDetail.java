package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "inns_detail")
public class InnDetail{

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Getter
		private Integer id; // ID

		@Getter
		@Column(name="inn_id")
		private Integer innId;
		
		@Getter
		private String name;
		
		@Getter
		private String img1; 
		
		@Getter
		private String img2; 
		
		@Getter
		private String img3; 
		
		@Getter
		private String location;

		@Getter
		private String access; 
		
		@Getter
		private String type; 
		
		@Getter
		private String meal; 
		
		@Getter
		private String price; 
		
		
		// コンストラクタ
		public InnDetail() {
			
		}

		public InnDetail(Integer innId, String name,String img1, String img2, String img3, String location, String access, String type, String meal, String price) {
			this.innId = innId;
			this.name = name;
			this.img1 = img1;
			this.img2 = img2;
			this.img3 = img3;
			this.location = location;
			this.access = access;
			this.type = type;
			this.meal = meal;
			this.price = price;
		}
		
}
