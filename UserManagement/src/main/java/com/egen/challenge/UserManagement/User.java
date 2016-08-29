package com.egen.challenge.UserManagement;

import java.util.UUID;
import java.time.*;

public class User {

	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private Address address;
	private String dateCreated;
	private String profilePicture;
	private Company company;

	public User(String id, String firstName, String lastName, String email, Address address, String dateCreated,
			String profilePicture, Company company) {
		super();

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.dateCreated = dateCreated;
		this.profilePicture = profilePicture;
		this.company = company;
	}

	public User(String firstName, String lastName, String email, Address address, String profilePicture,
			Company company) {
		super();
		this.id = UUID.randomUUID().toString();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.dateCreated = Instant.now().toString();
		this.profilePicture = profilePicture;
		this.company = company;
	}

	public String getId() {
		return id;
	}

	public void generateID() {
		this.id = UUID.randomUUID().toString();
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public void generateDate(){
		this.dateCreated = Instant.now().toString();
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", address=" + address + ", dateCreated=" + dateCreated + ", profilePicture=" + profilePicture
				+ ", company=" + company + "]";
	}

}
