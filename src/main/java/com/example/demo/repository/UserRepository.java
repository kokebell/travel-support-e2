package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	//SELECT * FROM users WHERE id = ?;
	Optional<User>findById (Integer id);
	//SELECT * FROM users WHERE email = ?;
	Optional<User>findByEmail(String email);
	//SELECT * FROM users WHERE email = ? AND password = ?;
	Optional<User>findByEmailAndPassword(String email, String password);
	//DELETE FROM users WHERE email = ?;
	void deleteByEmail(String email);

	
	
}
