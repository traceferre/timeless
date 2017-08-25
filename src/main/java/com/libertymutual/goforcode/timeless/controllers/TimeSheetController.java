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
	
	//replace sum with method that calls from model
	private double sum = 0;
	private ArrayList<Double> hours;
	private TimeSheetRepo repo;
	private String date;
	
	public TimeSheetController(TimeSheetRepo repo)
	{
		this.repo = repo;
	}
	
	@GetMapping("")
	public String sendBackToTimeSheet() 
	{
		return "redirect:/gettingSheet";
	}
	
	@GetMapping("gettingSheet")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView("timesheet/default");
        List<WeekOfHours> weeks = repo.getFileOfWeeks();
        mv.addObject("weeks", weeks);
        mv.addObject("hasWeeksOfHours", !weeks.isEmpty());
        return mv;
    }
	
	@GetMapping("timesheet")
	public ModelAndView getMyFullTimeSheet()
	{
		ModelAndView mv = new ModelAndView("timesheet/updated");
		List<WeekOfHours> weeks = repo.getFileOfWeeks();
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
	public ModelAndView updateMyTimeSheet(WeekOfHours week, String updateOrSubmit)
	{		
		if (updateOrSubmit.equals("update"))
		{
			ModelAndView mv = new ModelAndView("redirect:/timesheet");
			date = week.getDate();
			sum = week.getSum();
			hours = week.getAllHours();
			return mv;
		}
		else
		{
			ModelAndView mv = new ModelAndView("redirect:/gettingSheet");
			repo.writeWeekToFile(week);
			return mv;
		}
	}
}