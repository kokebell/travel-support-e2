package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Inn;

public interface InnRepository extends JpaRepository<Inn, Integer> {
	
	List<Inn>findAllBySpotId(Integer spotId);
}
