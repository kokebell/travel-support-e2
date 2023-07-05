package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Inn;
import com.example.demo.entity.Pref;
import com.example.demo.entity.Restaurant;
import com.example.demo.entity.Spot;
import com.example.demo.repository.InnRepository;
import com.example.demo.repository.PrefRepository;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.repository.SpotRepository;

@Controller
public class SpotController {

	@Autowired
	PrefRepository prefRepository;

	@Autowired
	SpotRepository spotRepository;

	@Autowired
	RestaurantRepository restaurantRepository;

	@Autowired
	InnRepository innRepository;

	//スポット画面を表示
	@GetMapping("/pref/{id}")
	public String show(
			@PathVariable("id") Integer id,
			Model m) {

		Pref pref = null;

		Optional<Pref> record = prefRepository.findById(id);

		if (record.isEmpty() == false) {
			pref = record.get();
		}

		List<Spot> spots = spotRepository.findAllByPrefId(id);

		m.addAttribute("pref", pref);
		m.addAttribute("spots", spots);

		return "pref";
	}

	//sanin画面に戻る
	@GetMapping("/back")
	public String back(
			Model m) {
		return "sanin";
	}

	//詳細画面表示
	@GetMapping("/detail/{spotId}")
	public String dindex(
			@PathVariable("spotId") Integer spotId,
			Model m) {

		List<Spot> spots = spotRepository.findAllById(spotId);

		m.addAttribute("spots", spots);

		return "spotDetail";
	}

	//おすすめ宿一覧表示
	@GetMapping("/inn/{spotId}")
	public String iindex(
			@PathVariable("spotId") Integer spotId,
			Model m) {

		List<Inn> inns = innRepository.findAllBySpotId(spotId);

		Spot spot = null;
		Optional<Spot> record = spotRepository.findById(spotId);

		if (record.isEmpty() == false) {
			spot = record.get();
		}

		m.addAttribute("inns", inns);
		m.addAttribute("spot", spot);

		return "inn";
	}

	//おすすめレストラン一覧表示
	@GetMapping("/restaurant/{spotId}")
	public String rindex(
			@PathVariable("spotId") Integer spotId,
			Model m) {

		List<Restaurant> restaurants = restaurantRepository.findAllBySpotId(spotId);

		Spot spot = null;
		Optional<Spot> record = spotRepository.findById(spotId);

		if (record.isEmpty() == false) {
			spot = record.get();
		}

		m.addAttribute("restaurants", restaurants);
		m.addAttribute("spot", spot);

		return "restaurant";
	}

}
