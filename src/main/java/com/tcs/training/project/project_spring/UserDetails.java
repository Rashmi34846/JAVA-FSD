package com.tcs.training.project.project_spring;

public class UserDetails {
	
	private long accountNumber;
	private String userName;
	private String panNumber;
	private String gender;
	private String address;
	private int pin_no;
	private double balance;
	
	public UserDetails(long accountNumber, String userName, String panNumber, String gender, String address, int pin_no) {
		super();
		this.accountNumber = accountNumber;
		this.userName = userName;
		this.panNumber = panNumber;
		this.gender = gender;
		this.address = address;
		this.pin_no = pin_no;
		balance = 0;
	}


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}


	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public int getPin_no() {
		return pin_no;
	}


	public void setPin_no(int pin_no) {
		this.pin_no = pin_no;
	}
	
	
	
}
