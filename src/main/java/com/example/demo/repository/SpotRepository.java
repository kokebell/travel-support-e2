package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Spot;

public interface SpotRepository extends JpaRepository<Spot, Integer> {
	
	List<Spot>findAllByPrefId(Integer id);
	List<Spot>findAllById(Integer spotId);
}
