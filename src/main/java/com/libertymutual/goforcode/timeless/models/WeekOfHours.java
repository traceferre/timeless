package com.libertymutual.goforcode.timeless.models;

import java.util.ArrayList;

public class WeekOfHours {
	
	private String date;
	private double monHours;
	private double tuesHours;
	private double wedHours;
	private double thursHours;
	private double friHours;
	
	public String getDate()
	{
		return this.date;
	}
	
	public double getMonHours()
	{
		return this.monHours;
	}
	
	public double getTuesHours()
	{
		return this.tuesHours;
	}
	
	public double getWedHours()
	{
		return this.wedHours;
	}
	
	public double getThursHours()
	{
		return this.thursHours;
	}
	
	public double getFriHours()
	{
		return this.friHours;
	}
	
	public ArrayList<Double> getAllHours()
	{
		ArrayList<Double> hoursForTheWeek = new ArrayList<Double>();
		hoursForTheWeek.add(monHours);
		hoursForTheWeek.add(tuesHours);
		hoursForTheWeek.add(wedHours);
		hoursForTheWeek.add(thursHours);
		hoursForTheWeek.add(friHours);
		return hoursForTheWeek;
	}
	
	public void setDate(String date)
	{
		this.date = date;
	}
	
	public void setMonHours(double monHours)
	{
		this.monHours = monHours;
	}
	
	public void setTuesHours(double tuesHours)
	{
		this.tuesHours = tuesHours;
	}
	
	public void setWedHours(double wedHours)
	{
		this.wedHours = wedHours;
	}
	
	public void setThursHours(double thursHours)
	{
		this.thursHours = thursHours;
	}
	
	public void setFriHours(double friHours)
	{
		this.friHours = friHours;
	}
	
	public double getSum()
	{
		return this.monHours + this.tuesHours + this.wedHours + this.thursHours + this.friHours;
	}
}
