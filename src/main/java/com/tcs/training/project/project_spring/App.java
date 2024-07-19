package com.tcs.training.project.project_spring;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	
	static UserService service;
	
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		App app = new App();
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		 context.getBean("dbOperate", DatabaseOperations.class);
		service = context.getBean("userService", UserService.class);

		while (true) {
			System.out.println("Press 1 To Log-in to wallet");
			System.out.println("Press 2 To Create a new account");
			System.out.println("Press 3 To Exit");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				app.LogInToWallet();
				break;
			case 2:
				service.createANewAccount();
				break;
			case 3:
				return;
			default:
				System.out.println("Enter a valid choice");
				break;
			}
		}
//		context.close();
	}

	

	void LogInToWallet() {

		System.out.println("Enter your Account number: ");
		long acc_Number = sc.nextLong();
		System.out.println("enter your pin");
		int pin = sc.nextInt();
		sc.nextLine();
		if (service.isValidUser(acc_Number, pin)) {
			System.out.println("Logged In!!");
			while (true) {
				System.out.println("press 1 To withdraw money");
				System.out.println("press 2 To deposit money");
				System.out.println("Press 3 To check balance");
				System.out.println("Press 4 For Fund Transfer");
				System.out.println("Press 5 To Print Transactions");
				System.out.println("Press 6 To Logout");
				int option = sc.nextInt();
				sc.nextLine();
				switch (option) {
				case 1:
					service.withdrawMoney(acc_Number);
					break;
				case 2:
					service.depositMoney(acc_Number);
					break;
				case 3:
					service.checkBalance(acc_Number);
					break;
				case 4:
					service.fundTransfer(acc_Number);
					break;
				case 5:
					service.printTransactions(acc_Number);
					break;
				case 6:
					return;
				default:
					System.out.println("Enter a valid choice");
					break;
				}
			}

		} else {
			System.out.println("Invalid credentials!!");
		}
	}
	


}


