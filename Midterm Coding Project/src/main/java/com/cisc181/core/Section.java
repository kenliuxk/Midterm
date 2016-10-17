package com.cisc181.core;

import java.util.UUID;


public class Section {
	private UUID CourseID;
	private UUID SemesterID;
	private UUID SectionID;
	private int RoomID;
	public Section(UUID courseID, UUID semesterID, int roomID) {
		super();
		this.SectionID = UUID.randomUUID();
		CourseID = courseID;
		SemesterID = semesterID;
		RoomID = roomID;
	}
	
	public int getRoomID() {
		return RoomID;
	}
	public void setRoomID(int roomID) {
		RoomID = roomID;
	}
	public UUID getCourseID() {
		return CourseID;
	}
	public UUID getSemesterID() {
		return SemesterID;
	}
	public UUID getSectionID() {
		return SectionID;
	}
	

}
