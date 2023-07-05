package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.InnDetail;

public interface InnDetailRepository extends JpaRepository<InnDetail, Integer> {
	
	List<InnDetail>findAllByInnId(Integer innId);
}
