package com.tcs.training.project.project_spring;

import java.sql.Date;

public class TransactionDetails {

	private long accountNumber;
	private double amount;
	private String  transactionType;
	private Date transactionTime;
	
	public TransactionDetails() {
		
	}
	
	public TransactionDetails(long accountNumber, double amount, String transactionType, Date transactionTime) {
		super();
		this.accountNumber = accountNumber;
		this.amount = amount;
		this.transactionType = transactionType;
		this.transactionTime = transactionTime;
	}


	public long getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public String getTransactionType() {
		return transactionType;
	}


	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}


	public Date getTransactionTime() {
		return transactionTime;
	}


	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}
	
	
}
