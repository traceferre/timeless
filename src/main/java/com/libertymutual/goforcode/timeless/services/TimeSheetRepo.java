package com.libertymutual.goforcode.timeless.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.libertymutual.goforcode.timeless.models.WeekOfHours;

@Service
public class TimeSheetRepo {
	
	private ArrayList<WeekOfHours> reverser(List<WeekOfHours> allMyLogs)
	{
		ArrayList<WeekOfHours> reversedList = new ArrayList<WeekOfHours>();
		
		for (int i = (allMyLogs.size()-1); i >= 0; i--)
		{
			reversedList.add(allMyLogs.get(i));
		}
		return reversedList;
	}
	
	public void writeWeekToFile(WeekOfHours week)
	{
		try (FileWriter writer = new FileWriter("logofweeks.csv", true);
	         BufferedWriter buff = new BufferedWriter(writer);
	         CSVPrinter printer = new CSVPrinter(buff, CSVFormat.DEFAULT)) {
			
			 ArrayList<String> aSingleWeek = new ArrayList<String>();
			 aSingleWeek.add(week.getDate());
			 aSingleWeek.add(Double.toString(week.getMonHours()));
			 aSingleWeek.add(Double.toString(week.getTuesHours()));
			 aSingleWeek.add(Double.toString(week.getWedHours()));
			 aSingleWeek.add(Double.toString(week.getThursHours()));
			 aSingleWeek.add(Double.toString(week.getFriHours()));
			 aSingleWeek.add(Double.toString(week.getSum()));
			 
			 printer.printRecord(aSingleWeek);
			 
		} catch (FileNotFoundException e) {
           	System.err.println("Could not find file");
   		} catch (IOException e) {
   			System.err.println("Could not read file");
   		} 
	}
	
	public List<WeekOfHours> getFileOfWeeks()
	{
		try (FileReader reader = new FileReader("logofweeks.csv");
	         BufferedReader br = new BufferedReader(reader)) {
			
			ArrayList<WeekOfHours> allMyLogs = new ArrayList<WeekOfHours>();
			Iterable<CSVRecord> record = CSVFormat.DEFAULT.parse(br);
			
			for (CSVRecord individualRecord : record)
			{
				WeekOfHours week = new WeekOfHours();
				week.setDate(individualRecord.get(0));
				week.setMonHours(Double.valueOf(individualRecord.get(1)));
				week.setTuesHours(Double.valueOf(individualRecord.get(2)));
				week.setWedHours(Double.valueOf(individualRecord.get(3)));
				week.setThursHours(Double.valueOf(individualRecord.get(4)));
				week.setFriHours(Double.valueOf(individualRecord.get(5)));
				allMyLogs.add(week);
			}
			//reverse order of list
			allMyLogs = reverser(allMyLogs);
			return allMyLogs;
			
		} catch (FileNotFoundException e) {
        	System.err.println("Could not find file");
			return Collections.emptyList();
		} catch (IOException e) {
			System.err.println("Could not read file");
			return Collections.emptyList();
		}
	}
}
