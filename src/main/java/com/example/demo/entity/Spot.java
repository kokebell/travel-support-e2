package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "spots")
public class Spot{

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Getter
		private Integer id; // ID

		@Getter
		@Column(name="pref_id")
		private Integer prefId;
		
		@Getter
		private String img1; 
		
		@Getter
		private String img2; 
		
		@Getter
		private String img3; 
		
		@Getter
		private String name; 
		 
		@Getter
		private String location; 


		@Getter
		private String outline; 
		
		@Getter
		private String access; 
		
		@Getter
		private String price; 
		

		// コンストラクタ
		public Spot() {
			
		}

		public Spot(Integer prefId,String img1,String img2,String img3, String name, String location, String outline, String access, String price) {
			this.prefId = prefId;
			this.img1 = img1;
			this.img2 = img2;
			this.img3 = img3;
			this.name = name;
			this.location = location;
			this.outline = outline;
			this.access = access;
			this.price = price;
		}
		
}
