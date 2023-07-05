package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "inns")
public class Inn{

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Getter
		private Integer id; // ID

		@Getter
		@Column(name="spot_id")
		private Integer spotId;
		
		@Getter
		private String img; 
		
		@Getter
		private String name;

		@Getter
		private String outline; 
		

		// コンストラクタ
		public Inn() {
			
		}

		public Inn(Integer spotId, String img, String name, String outline) {
			this.spotId = spotId;
			this.img = img;
			this.name = name;
			this.outline = outline;
		}
		
}
