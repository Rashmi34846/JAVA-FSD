package com.tcs.training.project.project_spring;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
	
	@Autowired
	DatabaseOperations databaseOperations;
	static Scanner sc = new Scanner(System.in);
	
	public boolean isValidUser(long accNumber, int pin) {
		String userName  = databaseOperations.getUser(accNumber, pin);
		if(userName.equals(null)){
			return false;
		}
		return true;
	}

	public boolean isValidPin(int pin) {
		if(pin>=1000 && pin<=9999)
			return true;
		return false;
	}
	
	public void withdrawMoney(long acc_Number) {
		System.out.println("Enter the amount you want to withdraw");
		double money = sc.nextDouble();
		System.out.println("Enter your pin: ");
		int pin = sc.nextInt();
		
		if(isValidUser(acc_Number, pin)) {
			if(isValidTransaction(acc_Number, pin, money)) {
				databaseOperations.withdrawMoneyByAccountNumberAndPin(acc_Number, pin, money);
				databaseOperations.updateTransactionsTable(acc_Number, money, "withdraw");
				System.out.println("Succesfully withdrawn amount " +money);
				double balance = databaseOperations.fetchBalance(acc_Number, pin);
				System.out.println("Your current balance is: " +balance);
			}
			else {
				System.out.println("Insufficient balance");
			}
		}
		else {
			System.out.println("Incorrect pin!");
		}
	}
	
	public boolean isValidTransaction(long acc_Number, int pin, double money) {
		double balance = databaseOperations.fetchBalance(acc_Number, pin);
		if(balance>=money) {
			return true;
		}
		return false;
	}

	public void depositMoney(long acc_Number) {

		System.out.println("Enter the amount you want to deposit: ");
		double money = sc.nextDouble();
		System.out.println("Enter your pin: ");
		int pin = sc.nextInt();
		
		if(isValidUser(acc_Number, pin)) {
			databaseOperations.depositMoneyByAccountNumberAndPin(acc_Number, pin, money);
			databaseOperations.updateTransactionsTable(acc_Number, money, "deposit");
			System.out.println("Succesfully deposited amount " +money);
			double balance = databaseOperations.fetchBalance(acc_Number, pin);
			System.out.println("Your current balance is: " +balance);
		}
		else {
			System.out.println("Incorrect pin!");
		}
	}

	public void checkBalance(long acc_Number) {
		System.out.println("Enter your pin: ");
		int pin = sc.nextInt();
		if(isValidUser(acc_Number, pin)) {
			double balance = databaseOperations.fetchBalance(acc_Number, pin);
			System.out.println("Your current balance is: " + balance);
		}
		else {
			System.out.println("Incorrect pin!!");
		}
	}

	public void fundTransfer(long acc_Number) {
		
		System.out.println("Enter the account number to which you want to transfer: ");
		long new_acc_number = sc.nextLong();
		System.out.println("Enter the amount you want to transfer: ");
		double money = sc.nextDouble();
		System.out.println("Enter your pin: ");
		int pin = sc.nextInt();
		if(isValidUser(acc_Number, pin)) {
			if(isValidTransaction(acc_Number, pin, money)) {
				databaseOperations.withdrawMoneyByAccountNumberAndPin(acc_Number, pin, money);
				databaseOperations.depositMoneyByAccountNumberAndPin(new_acc_number, pin, money);
				databaseOperations.updateTransactionsTable(acc_Number, money, "withdraw");
				databaseOperations.updateTransactionsTable(new_acc_number, money, "deposit");
				System.out.println("Succesfully transferred amount " +money);
				double balance = databaseOperations.fetchBalance(acc_Number, pin);
				System.out.println("Your current balance is: " +balance);
			}
			else {
				System.out.println("Insufficient balance");
			}
		}
		else {
			System.out.println("Incorrect pin!");
		}
		
	}

	public void printTransactions(long acc_Number) {
		List<TransactionDetails> list = databaseOperations.fetchAllTransaction(acc_Number);
		list.forEach(System.out::println);
	}
	
	 void createANewAccount() {

		System.out.println("Enter your name: ");
		String name = sc.nextLine();
		System.out.println("Enter your Pan Card Number: ");
		String pan_number = sc.nextLine();
		System.out.println("Enter your gender M/F: ");
		String gender = sc.nextLine();
		System.out.println("Enter your address: ");
		String address = sc.nextLine();
		int pin;
		do {
			System.out.println("Generate your 4 digit pin number: ");
			pin = sc.nextInt();
			if(isValidPin(pin)) {
				break;
			}else {
				System.out.println("Please enter a 4 digit pin");
			}
		}while(true);
		
		long accountNumber = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		double balance = 0.0;
		databaseOperations.insertNewUser(accountNumber, pin, name, pan_number, gender, address, balance);
		
		System.out.println("Account creation was successful");
		System.out.println("Your Account Number is " + accountNumber + ". Please note it down");
	}
	
}
