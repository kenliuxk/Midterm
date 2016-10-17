package com.cisc181.core;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.PersonException;

/*
 * comment
 */
public abstract class Person implements java.io.Serializable {

	private Date DOB;
	private String FirstName;
	private String MiddleName;
	private String LastName;
	private String address;
	private String phone_number;
	private String email_address;

	public String getFirstName() {
		return FirstName;
	}

	
	public void setFirstName(String FirstName) {
		this.FirstName = FirstName;
	}

	public String getMiddleName() {
		return MiddleName;
	}

	public void setMiddleName(String MiddleName) {
		this.MiddleName = MiddleName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String LastName) {
		this.LastName = LastName;
	}

	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date DOB) throws PersonException {
		Calendar todayDate = Calendar.getInstance();
		Calendar dateOfBirth = Calendar.getInstance();
		
		int age = 0;
		dateOfBirth.setTime(DOB);
		if (dateOfBirth.after(todayDate)) {
			throw new IllegalArgumentException("Not possible to be born in the future");
		}
		age = todayDate.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
		if (todayDate.get(Calendar.MONTH) < dateOfBirth.get(Calendar.MONTH)) {
			age--;
		} else if (todayDate.get(Calendar.MONTH) == dateOfBirth.get(Calendar.MONTH) 
				&& todayDate.get(Calendar.DAY_OF_MONTH) < dateOfBirth.get(Calendar.DAY_OF_MONTH)) {
			age--;
		}
		if (age < 100) {
			this.DOB = DOB;
		} else {
			throw new PersonException(this);
		}
		
		
		
	}

	public void setAddress(String newAddress) {
		address = newAddress;
	}

	public String getAddress() {
		return address;
	}

	public void setPhone(String newPhone_number) throws PersonException {
		String regex = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(newPhone_number);
		if (matcher.matches()) {
			this.phone_number = newPhone_number;
		} else {
			throw new PersonException(this);
		}
		
	
	}

	public String getPhone() {
		return phone_number;
	}

	public void setEmail(String newEmail) {
		email_address = newEmail;
	}

	public String getEmail() {
		return email_address;
	}

	/*
	 * Constructors No Arg Constructor
	 */
	public Person() {

	}

	/*
	 * Constructors Constructor with arguments
	 */

	public Person(String FirstName, String MiddleName, String LastName,
			Date DOB, String Address, String Phone_number, String Email)
	{
		this.FirstName = FirstName;
		this.MiddleName = MiddleName;
		this.LastName = LastName;
		try {
			this.setDOB(DOB);
		} catch (PersonException e) {
			System.out.println("Date of birth cannot be more than 100 years old.");
		}
		this.address = Address;
		try {
			this.setPhone(Phone_number);
		} catch (PersonException e) {
			System.out.println("Your phone number format is not correct.");
		}
		this.email_address = Email;
		
	}

	public void PrintName() {
		System.out.println(this.FirstName + ' ' + this.MiddleName + ' '
				+ this.LastName);
	}

	public void PrintDOB() {
		System.out.println(this.DOB);
	}

	public int PrintAge() {
		Calendar today = Calendar.getInstance();
		Calendar birthDate = Calendar.getInstance();

		int age = 0;
		birthDate.setTime(this.DOB);
		if (birthDate.after(today)) {
			throw new IllegalArgumentException("Can't be born in the future");
		}
		age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

		// If birth date is greater than todays date (after 2 days adjustment of
		// leap year) then decrement age one year
		if ((birthDate.get(Calendar.DAY_OF_YEAR)
				- today.get(Calendar.DAY_OF_YEAR) > 3)
				|| (birthDate.get(Calendar.MONTH) > today.get(Calendar.MONTH))) {
			age--;

			// If birth date and todays date are of same month and birth day of
			// month is greater than todays day of month then decrement age
		} else if ((birthDate.get(Calendar.MONTH) == today.get(Calendar.MONTH))
				&& (birthDate.get(Calendar.DAY_OF_MONTH) > today
						.get(Calendar.DAY_OF_MONTH))) {
			age--;
		}

		System.out.println("age is " + age);

		return age;

	}
}