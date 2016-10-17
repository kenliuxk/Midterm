package com.cisc181.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cisc181.eNums.eTitle;
import exceptions.PersonException;

public class Staff_Test {
	
	static Staff Staff1;
	static Staff Staff2;
	static Staff Staff3;
	static Staff Staff4;
	static Staff WrongStaff;
	static ArrayList<Staff> Staff;
	
	
	@BeforeClass
	public static void setup() {
		Staff = new ArrayList<Staff>();
		Staff1 = new Staff("FirstName","MiddleName","LastName",new Date(),"Address","123-456-7890",
				"staff1@email.com","Smith Hall",1,10000,new Date(),eTitle.MR);
		Staff2 = new Staff("FirstName","MiddleName","LastName",new Date(),"Address","123-456-7890",
				"staff1@email.com","Smith Hall",1,20000,new Date(),eTitle.MR);
		Staff3 = new Staff("FirstName","MiddleName","LastName",new Date(),"Address","123-456-7890",
				"staff1@email.com","Smith Hall",1,30000,new Date(),eTitle.MR);
		Staff4 = new Staff("FirstName","MiddleName","LastName",new Date(),"Address","123-456-7890",
				"staff1@email.com","Smith Hall",1,40000,new Date(),eTitle.MR);
		WrongStaff = new Staff("FirstName","MiddleName","LastName",new Date(),"Address","123-456-7890",
				"staff1@email.com","Smith Hall",1,50000,new Date(),eTitle.MR);
		Staff.add(Staff1);
		Staff.add(Staff2);
		Staff.add(Staff3);
		Staff.add(Staff4);
		Staff.add(WrongStaff);
	}
	
	@Test
	public void testSalary() {
		double averageSalary = ((10000 + 20000 + 30000 + 40000 +50000)/5);
		double totalSalary = 0;
		for (Staff s: Staff) {
			totalSalary += s.getSalary();
		}
		double actualSalary = totalSalary/Staff.size();
		assertEquals(averageSalary,actualSalary,0.1);
	}	
	
	@Test(expected = PersonException.class)
	public void testID() throws PersonException {
		Calendar cal = Calendar.getInstance();
		cal.set(1900, 1, 1);
		Date date = cal.getTime();
		WrongStaff.setDOB(date);
	}
	
	@Test
	public void testNumber() throws PersonException {
		WrongStaff.setPhone("123-456-7890");
	}
	
	@Test(expected = PersonException.class)
	public void testWrongNumber() throws PersonException {
		WrongStaff.setPhone("234-567-890");
	}

}
