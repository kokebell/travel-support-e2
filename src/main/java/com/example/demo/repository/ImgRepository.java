package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Img;
import com.example.demo.entity.Note;

public interface ImgRepository extends JpaRepository<Img, Integer> {
	
	//SELECT * FROM imgs WHERE image_id = ?;
	List<Note>findByImageId(Integer imageId);
	Optional<Note>findByNumAndImageId(Integer num, Integer imageId);
	
}
