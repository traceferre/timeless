package com.libertymutual.goforcode.timeless.models;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ModelsTests {

	private ArrayList<Double> testList;
	private double monday;
	private double tuesday;
	private double wednesday;
	private double thursday;
	private double friday;
	private double allHours;
	private String date;
	private WeekOfHours week;
	
	
	@Before
	public void setUp() throws Exception 
	{
		testList = new ArrayList<Double>();
		testList.add(5.0);
		testList.add(10.0);
		testList.add(0.0);
		testList.add(5.5);
		testList.add(0.0);
		
		week = new WeekOfHours();
		week.setDate("05/18/1986");
		week.setMonHours(5.0);
		week.setTuesHours(10.0);
		week.setWedHours(0.0);
		week.setThursHours(5.5);
		week.setFriHours(0.0);
	}

	@Test
	public void test_getting_monday_hours()
	{
		monday = week.getMonHours();
		
		assertThat(monday).isEqualTo(5.0);
	}
	
	@Test
	public void test_getting_tuesday_hours()
	{
		tuesday = week.getTuesHours();
		
		assertThat(tuesday).isEqualTo(10.0);
	}
	
	@Test
	public void test_getting_wednesday_hours()
	{
		wednesday = week.getWedHours();
		
		assertThat(wednesday).isEqualTo(0.0);
	}
	
	@Test
	public void test_getting_thursday_hours()
	{
		thursday = week.getThursHours();
		
		assertThat(thursday).isEqualTo(5.5);
	}
	
	@Test
	public void test_getting_friday_hours()
	{
		friday = week.getFriHours();
		
		assertThat(friday).isEqualTo(0.0);
	}
	
	@Test
	public void test_getting_date()
	{
		date = week.getDate();
		
		assertThat(date).isEqualTo("05/18/1986");
	}
	
	@Test
	public void test_getting_sum()
	{
		allHours = week.getSum();
		
		assertThat(allHours).isEqualTo(20.5);
	}
	
	@Test
	public void test() 
	{
		ArrayList<Double> otherTestingList = new ArrayList<Double>();
		
		otherTestingList = week.getAllHours();
		
		assertThat(otherTestingList.size()).isEqualTo(5);
		assertThat(otherTestingList).isEqualTo(testList);
	}

}
