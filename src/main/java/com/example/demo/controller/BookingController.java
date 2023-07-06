package com.example.demo.controller;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.InnDetail;
import com.example.demo.repository.InnDetailRepository;
import com.example.demo.repository.InnRepository;

@Controller
public class BookingController {
    
	@Autowired
	InnDetailRepository innDetailRepository;
	
	@Autowired
	InnRepository innRepository;
	
	//inns_detail画面の表示
	@GetMapping("/bookingshow/{innId}")
		public String show(
				@PathVariable("innId")Integer innId,
				Model m) {
		
		List<InnDetail> inns = innDetailRepository.findAllByInnId(innId);
		
		m.addAttribute("inns",inns);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
    	LocalDate ldt = LocalDate.now();
    	String basedate = ldt.format(dtf);
    	LocalDate today = LocalDate.parse(basedate, dtf);
    	LocalDate promisingfuture = today.plusYears(1);
    	LocalDate schedule = null;
    	String result = null;
    	m.addAttribute("today", today);
    	m.addAttribute("promisingfuture", promisingfuture);
    	m.addAttribute("schedule", schedule);
    	m.addAttribute("result", result);
		
			return "bookingDetail";
		}
	
	//確認画面へ飛ぶ
	@PostMapping("/confirmBooking")
	String confirmBooking(
			@RequestParam(name = "schedule", required = false) LocalDate schedule,
			@RequestParam(name = "stayDate", required = false) Integer stayDate,
			@RequestParam(name = "people", required = false) Integer people,
			@RequestParam(name = "plan", required = false) Integer plan,
			Model model) {
		
		Integer price = null;
		if(plan == 1) {
			price = 29000 * stayDate;
		} else if(plan==2) {
			price= (29000 + 800) * stayDate;
		}else if(plan==3) {
			price= (29000 + 600) * stayDate;
		}else if(plan==4) {
			price= (29000 + 1500) * stayDate;
		}
		
		Integer sum = price * people;
		
		model.addAttribute("schedule", schedule);
		model.addAttribute("stayDate", stayDate);
		model.addAttribute("people", people);
		model.addAttribute("plan", plan);
		model.addAttribute("price", price);
		model.addAttribute("sum", sum);

		return "bookingConfirm";
	}
	
//	//予約を確定する
//	 @PostMapping("/confirmDecision")
//   String confirmDecision(
//   		Model model) {
//		 String result = "完了";
//   	model.addAttribute("result", result);
//   	
//       return "bookingConfirm";
//   }
	
//    @PostMapping("/bookingdetail")
//    String showplan(
//    		@RequestParam(name="schedule", required=false) LocalDate schedule,
//    		Model model) {
//    	model.addAttribute("schedule", schedule);
//    	
//        return "bookingDetail";
//    }
//    @PostMapping("/bookingcomplete")
//    String showcomplete(
//    		@RequestParam(name="schedule", required=false) LocalDate schedule,
//    		Model model) {
//    	String result = "完了";
//    	String booking = schedule.toString();
//    	model.addAttribute("booking", booking);
//    	model.addAttribute("result", result);
//
//        return "bookingDetail";
//    }
}