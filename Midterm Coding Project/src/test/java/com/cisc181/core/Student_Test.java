package com.cisc181.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cisc181.eNums.eMajor;

public class Student_Test {
	
	static ArrayList<Course> courses;
	static Course courseone;
	static Course coursetwo;
	static Course coursethree;
	
	
	static ArrayList<Semester> semesters;
	static Semester Fall;
	static Semester Spring;
	
	static ArrayList<Section> sections;
	static Section FallSectionOne;
	static Section FallSectionTwo;
	static Section FallSectionThree;
	static Section SpringSectionOne;
	static Section SpringSectionTwo;
	static Section SpringSectionThree;
	
	static ArrayList<Student> students;
	static Student student1;
	static Student student2;
	static Student student3;
	static Student student4;
	static Student student5;
	static Student student6;
	static Student student7;
	static Student student8;
	static Student student9;
	static Student student10;
	
	static ArrayList<Enrollment> enrollList = new ArrayList<Enrollment>();

	@BeforeClass
	public static void setup() {
		students = new ArrayList<Student>();
		student1 = new Student("FirstName", "MiddleName", "LastName", new Date(), eMajor.BUSINESS, "Address", 
				"123-456-7890", "student@email.com");
		student2 = new Student("FirstName", "MiddleName", "LastName", new Date(), eMajor.BUSINESS, "Address", 
				"123-456-7890", "student@email.com");
		student3 = new Student("FirstName", "MiddleName", "LastName", new Date(), eMajor.BUSINESS, "Address", 
				"123-456-7890", "student@email.com");
		student4 = new Student("FirstName", "MiddleName", "LastName", new Date(), eMajor.BUSINESS, "Address", 
				"123-456-7890", "student@email.com");
		student5 = new Student("FirstName", "MiddleName", "LastName", new Date(), eMajor.BUSINESS, "Address", 
				"123-456-7890", "student@email.com");
		student6 = new Student("FirstName", "MiddleName", "LastName", new Date(), eMajor.BUSINESS, "Address", 
				"123-456-7890", "student@email.com");
		student7 = new Student("FirstName", "MiddleName", "LastName", new Date(), eMajor.BUSINESS, "Address", 
				"123-456-7890", "student@email.com");
		student8 = new Student("FirstName", "MiddleName", "LastName", new Date(), eMajor.BUSINESS, "Address", 
				"123-456-7890", "student@email.com");
		student9 = new Student("FirstName", "MiddleName", "LastName", new Date(), eMajor.BUSINESS, "Address", 
				"123-456-7890", "student@email.com");
		student10 = new Student("FirstName", "MiddleName", "LastName", new Date(), eMajor.BUSINESS, "Address", 
				"123-456-7890", "student@email.com");
		students.add(student1);
		students.add(student2);
		students.add(student3);
		students.add(student4);
		students.add(student5);
		students.add(student6);
		students.add(student7);
		students.add(student8);
		students.add(student9);
		students.add(student10);
		
		courses = new ArrayList<Course>();
		courseone = new Course("Physics", 12, eMajor.PHYSICS);
		coursetwo = new Course("Chem",16, eMajor.CHEM);
		coursethree = new Course("Business", 8, eMajor.BUSINESS);
		courses.add(courseone);
		courses.add(coursetwo);
		courses.add(coursethree);
		
		semesters = new ArrayList<Semester>();
		Spring = new Semester(new Date(),new Date());
		Fall = new Semester(new Date(),new Date());
		semesters.add(Spring);
		semesters.add(Fall);
		
		sections = new ArrayList<Section>();
		FallSectionOne = new Section(courseone.getCourseID(), Fall.getSemesterID(), 001);
		FallSectionTwo = new Section(courseone.getCourseID(), Fall.getSemesterID(), 002);
		FallSectionThree = new Section(courseone.getCourseID(), Fall.getSemesterID(), 003);
		SpringSectionOne = new Section(courseone.getCourseID(), Spring.getSemesterID(), 004);
		SpringSectionTwo = new Section(coursetwo.getCourseID(), Spring.getSemesterID(), 005);
		SpringSectionThree = new Section(coursethree.getCourseID(), Spring.getSemesterID(), 006);
		sections.add(FallSectionOne);
		sections.add(FallSectionTwo);
		sections.add(FallSectionThree);
		sections.add(SpringSectionOne);
		sections.add(SpringSectionTwo);
		sections.add(SpringSectionThree);
		
		for(Student s1: students)
			for(Section s2: sections)
				enrollList.add(new Enrollment(s1.getStudentID(),s2.getSectionID()));
	}

	@Test
	public void testEnroll() {
		assertTrue(enrollList.size() == 60);
	}
	
	@Test
	public void testGPA() {
		enrollList.get(0).setGrade(2);
		enrollList.get(1).setGrade(3);
		enrollList.get(2).setGrade(4);
		enrollList.get(3).setGrade(3);
		enrollList.get(4).setGrade(4);
		enrollList.get(5).setGrade(4);
		
		double GPA = (2*8+3*12+4*16+3*12+4*16+4*16)/(8+12+16+12+16+16);
		double student1GPA = 0;
		int GPATotal = 0;
		for (Enrollment e: enrollList) 
			
			if (e.getStudentID() == students.get(0).getStudentID()) {
				for (Section s: sections) 
					if (s.getSectionID() == e.getSectionID()) {
						for (Course c: courses) 
							if (s.getCourseID() == c.getCourseID()) {
								student1GPA += e.getGrade() * c.getGradePoints();
								GPATotal += c.getGradePoints();
							}
						
					}
				
			}
		
		double finalGPA = student1GPA/GPATotal;
		assertEquals(finalGPA,GPA,0.01);
		
		for(int i = 6; i < enrollList.size(); i++)
			enrollList.get(i).setGrade(3.0);
		
		
		GPA = 3.0;
		
		assertEquals(StudentGPA(students.get(1).getStudentID()), GPA, .001);
		assertEquals(StudentGPA(students.get(2).getStudentID()), GPA, .001);
		assertEquals(StudentGPA(students.get(3).getStudentID()), GPA, .001);
		assertEquals(StudentGPA(students.get(4).getStudentID()), GPA, .001);
		assertEquals(StudentGPA(students.get(5).getStudentID()), GPA, .001);
		assertEquals(StudentGPA(students.get(6).getStudentID()), GPA, .001);
		assertEquals(StudentGPA(students.get(7).getStudentID()), GPA, .001);
		assertEquals(StudentGPA(students.get(8).getStudentID()), GPA, .001);
		assertEquals(StudentGPA(students.get(9).getStudentID()), GPA, .001);
		
	}
	
	public double StudentGPA(UUID StudentID) {
		double student1GPA = 0;
		int GPATotal = 0;
		for(Enrollment e: enrollList)
			if(e.getStudentID() == StudentID){ // change to .get(1) through .get(9) to get all other students
				for(Section s: sections)
					if(s.getSectionID() == e.getSectionID()){
						for(Course c: courses)
							if(s.getCourseID() == c.getCourseID()){
								student1GPA += e.getGrade() * c.getGradePoints();
								GPATotal += c.getGradePoints();
							}
					}	
			}
		double studGPA = student1GPA/GPATotal;
		return studGPA;
		
	
	}
	
	@Test
	public void CourseAveragetest(){
											
		for(int i = 0; i < enrollList.size(); i++){
			enrollList.get(i).setGrade(i % 3 + 2); //sets all of the grades
		}
		//every 3 enrolls is the same course; so the first course should have an
		//average grade 2.0, the second course 3.0, and the third course 4.0
		
		double firstTotal = 0;
		double secondTotal = 0;
		double thirdTotal = 0;
		
		int first = 0;
		int second = 0;
		int third = 0;
		for(Course c: courses)
			for(Section s: sections)
				for(Enrollment e: enrollList)
					if (c.getCourseID() == s.getCourseID()){
						if(s.getSectionID() == e.getSectionID()){
							if(c.getCourseID() == courses.get(0).getCourseID()){
								firstTotal+= e.getGrade(); 
								first++;
							}else if(c == courses.get(1)) {
								secondTotal+= e.getGrade(); 
								second++;
							}else {
								thirdTotal += e.getGrade(); 
								third++;
							}
						}
					}
					
		double firstCourseAverage = firstTotal/first;
		double secondCourseAverage = secondTotal/second;
		double thirdCourseAverage = thirdTotal/third;
		
		assertEquals(firstCourseAverage, 3.0, 0);
		assertEquals(secondCourseAverage, 3.0, 0);
		assertEquals(thirdCourseAverage, 3.0, 0);
	}
	
	@Test
	public void changeMajorTest(){
		String oldMajor = students.get(5).getMajor().toString();
		students.get(5).setMajor(eMajor.BUSINESS);
		String newMajor = students.get(5).getMajor().toString();
		assertTrue(oldMajor != newMajor);
	}
	
	
	
}