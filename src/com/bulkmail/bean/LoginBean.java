package com.bulkmail.bean;

public class LoginBean {

	private int id;
	private String Email;
	private String Name;
	private String Password;
	private String Contact;
	private int Status;
	private int role;
	private int is_active;
private boolean isLoggedIn;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getEmail() {
	return Email;
}
public void setEmail(String email) {
	Email = email;
}
public String getName() {
	return Name;
}
public void setName(String name) {
	Name = name;
}
public String getPassword() {
	return Password;
}
public void setPassword(String password) {
	Password = password;
}
public String getContact() {
	return Contact;
}
public void setContact(String contact) {
	Contact = contact;
}
public int getStatus() {
	return Status;
}
public void setStatus(int status) {
	Status = status;
}
public int getRole() {
	return role;
}
public void setRole(int role) {
	this.role = role;
}
public boolean isLoggedIn() {
	return isLoggedIn;
}
public void setLoggedIn(boolean isLoggedIn) {
	this.isLoggedIn = isLoggedIn;
}
public int getIs_active() {
	return is_active;
}
public void setIs_active(int is_active) {
	this.is_active = is_active;
}
	
}
