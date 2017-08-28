package com.libertymutual.goforcode.timeless.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.libertymutual.goforcode.timeless.models.WeekOfHours;
import com.libertymutual.goforcode.timeless.services.TimeSheetRepo;

@Controller
@RequestMapping("/")
public class TimeSheetController {
	
	private String buttonClick = "update";
	private double sum = 0.0;
	private ArrayList<Double> hours;
	private TimeSheetRepo repo;
	private String date = "mm/dd/yyyy";
	
	public TimeSheetController(TimeSheetRepo repo) {
		this.repo = repo;
	}
	
	@GetMapping("")
	public String sendBackToTimeSheet() {
		return "redirect:/timesheet";
	}
	
	@GetMapping("timesheet")
	public ModelAndView getMyFullTimeSheet() {
		
		ModelAndView mv = new ModelAndView("timesheet/updated");
		List<WeekOfHours> weeks = new ArrayList<WeekOfHours>();        
		WeekOfHours week = new WeekOfHours();
		week = repo.getTempFileOfWeeks();
		
		if (buttonClick == "update") {
			hours = week.getAllHours();
			date = week.getDate();
			sum = week.getSum();
		}
			
		weeks = repo.getFileOfWeeks();
        mv.addObject("weeks", weeks);
        mv.addObject("hasWeeksOfHours", !weeks.isEmpty());
		mv.addObject("date", date);
		mv.addObject("sum", sum);
		mv.addObject("mon", hours.get(0));
		mv.addObject("tues", hours.get(1));
		mv.addObject("wed", hours.get(2));
		mv.addObject("thurs", hours.get(3));
		mv.addObject("fri", hours.get(4));
		return mv;
	}
	
	@PostMapping("timesheet")
	public ModelAndView updateMyTimeSheet(WeekOfHours week, String updateOrSubmit)	{		
		ModelAndView mv = new ModelAndView("redirect:/timesheet");
		
		if (updateOrSubmit.equals("update")) {
			buttonClick = "update";
			repo.writeTempWeekToFile(week);
			date = week.getDate();
			sum = week.getSum();
			hours = week.getAllHours();
			return mv;
		}
		else {
			buttonClick = "submit";
			repo.writeWeekToFile(week);
			date = "mm/dd/yyyy";
			sum = 0.0;
			hours.set(0, 0.0);
			hours.set(1, 0.0);
			hours.set(2, 0.0);
			hours.set(3, 0.0);
			hours.set(4, 0.0);
			return mv;
		}
	}
}
