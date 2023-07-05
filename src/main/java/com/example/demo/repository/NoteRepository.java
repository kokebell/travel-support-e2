package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Note;

public interface NoteRepository extends JpaRepository<Note, Integer> {
	
	//SELECT * FROM notes WHERE author_id = ?;
	List<Note>findAllByAuthorId(Integer id);
	//SELECT * FROM notes WHERE email = ?;
	
}
