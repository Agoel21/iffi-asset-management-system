package com.iffi;

import java.util.List;
/*
 * A basic data class that models a person.
 */

public class Person {

	private final String code;
	private final String lastName;
	private final String firstName;
	private final Address address;
	private final List<String> emailAddress;

	public Person(String code, String lastName, String firstName, Address address, List<String> emailAddress) {
		super();
		this.code = code;
		this.lastName = lastName;
		this.firstName = firstName;
		this.address = address;
		this.emailAddress = emailAddress;
	}

	public String getCode() {
		return code;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public Address getAddress() {
		return address;
	}

	public List<String> getEmailAddress() {
		return emailAddress;
	}

	/**
	 * Mehtod to produce A person name in Last name , First name format
	 */
	public String toString() {
		return lastName + ", " + firstName + "\n" + emailAddress + "\n" + address + "\n";
	}

}
