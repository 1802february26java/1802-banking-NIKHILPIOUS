package com.revature.controller;

import java.awt.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.exception.AuthenticationException;
import com.revature.exception.InsufficientAmountException;
import com.revature.model.BankModel;
import com.revature.service.BankServiceBO;


public class BankController {
	private Scanner in;
	private BankModel bmodel;
	static int attempt;
	private static Logger logger = Logger.getLogger(BankController.class);

	public BankController() {
		super();
		this.in = new Scanner(System.in);
		welcomePage();

	}

	public void welcomePage() {
		Scanner in = new Scanner(System.in);
		System.out.println("Hello, Welcome to ABC bank!\n Press 1 for Login ,press 2 for Register, press 3 for Exit");
		int userchoice = in.nextInt();
		if (userchoice == 1) {
			loginPage();
		} else if (userchoice == 2) {
			registerPage();
		} 
		else if(userchoice == 3){
			logoutPage();
		}else {
			welcomePage();
		}
	}

	public void loginPage() {// 1
		System.out.println("Please eneter your username and password");
		System.out.println("Username:");
		String userName = in.nextLine();
		System.out.println("Password:");
		String password = in.nextLine();
		BankServiceBO bnkbo = new BankServiceBO();
		if (checkCredintial(1, userName, password)) {
			optionPage(bmodel);
		} else {
			if(attempt==3){
				logoutPage();
			}
			else{
			welcomePage();
			}
		}

	}

	public void registerPage() {
		BankModel bmodelLoc = new BankModel();
		System.out.println("Please eneter your username and password\n");
		System.out.println("Username:");
		String userName = in.nextLine();
		bmodelLoc.setUname(userName);
		System.out.println("Password:");
		String password = in.nextLine();
		bmodelLoc.setPassw(password);
		System.out.println("");
		if (checkCredintial(2, userName, password)) {
			this.bmodel = bmodelLoc;
			BankServiceBO bnkbo = new BankServiceBO();
			if (bnkbo.registerUserBO(bmodel)) {
				optionPage(bmodel);
			} else {
				
			}

		} else {
			welcomePage();
		}

	}

	public boolean checkCredintial(int identity, String uname, String passw) {
		BankServiceBO bnkbo = new BankServiceBO();
		
		if (identity == 1) {
			BankModel bmodelRetrun = bnkbo.getUserCredintialBO(uname, passw);
			if (bmodelRetrun == null
					|| !(uname.equalsIgnoreCase(bmodelRetrun.getUname()) && passw.equals(bmodelRetrun.getPassw()))) {
				System.out.println("Incorrect credential\n");
				++attempt;
				if(attempt==3){
					String msg="Too many attempts, your account has been locked.";
					try {
						throw new AuthenticationException();
					} catch (AuthenticationException e) {
						logger.error(msg,e);
					
					}
				}
				return false;
				
			} else if (uname.equalsIgnoreCase(bmodelRetrun.getUname()) && passw.equals(bmodelRetrun.getPassw())) {
				this.bmodel = bmodelRetrun;
				attempt=0;
				return true;
			}
		}

		if (identity == 2) {
			BankModel bmodelRetrun = bnkbo.getUserCredintialBO(uname.toLowerCase(), passw);//double check--not efficient, may be create
		
			if (bmodelRetrun == null || !(uname.equalsIgnoreCase(bmodelRetrun.getUname()))) {
				return true;
			} else {
				if (uname.equalsIgnoreCase(bmodelRetrun.getUname())) {
					System.out.println("Username already exist! Please register again\n");
				} else {
					System.out.println("Incorrect credential, please register again\n");
				}
				return false;
			}
		}
		return false;

	}

	public void optionPage(BankModel bmodel) {
		BankServiceBO bnkbo = new BankServiceBO();
		Double deposit = null;
		Double withDraw = null;
	
		System.out.println("Plese choose one of the below options:\n 1.BALANCE 2. WITHDRAW 3. DEPOSIT 4.LogOut\n");
		int userChoice=in.nextInt();
		if (userChoice == 1) {
			System.out.println("Your current balance is:\n $"+bmodel.getBalance());
			successPage(bmodel);
			
		} else if (userChoice == 2) {
			System.out.println("Please eneter the amount to withdraw:\n");
			withDraw = in.nextDouble();
			double balance = bmodel.getBalance();
			if (balance - withDraw >= 0) {
				bmodel.setWithdraw(withDraw);
				bmodel.setBalance(balance - withDraw);
				if (bnkbo.withdrawFromBankBO(bmodel)) {
					System.out.println(
							"Amount $" + withDraw + " withdrawned, current balance is: $" + bmodel.getBalance() + "\n\n");
					successPage(bmodel);
				} else {
					System.out.println("Something went wrong, please statrt over agains.\n");
					welcomePage();
				}
			} else {
				String msg = "Insufficient amount. Your currrent balance is: $" + balance+"\n";
				try {
					throw new InsufficientAmountException(msg);
				} catch (InsufficientAmountException e) {
					
					optionPage(bmodel);
					
				}
				
			
			}
		} else if (userChoice == 3) {
			System.out.println("Please eneter amount you want to deposit:");
			deposit = in.nextDouble();
			double balance = bmodel.getBalance() + deposit;
			bmodel.setDeposit(deposit);
			bmodel.setBalance(balance);
			if (bnkbo.depositToBnakBO(bmodel)) {
				System.out.println(
						"Amount $" + deposit + " deposited, current balance is: $" + bmodel.getBalance() + "\n\n");
				successPage(bmodel);
			}

			else {
				optionPage(bmodel);
			}
		}
		else if(userChoice==4){
			logoutPage();
		}
		else {
			logoutPage();

		}
	}

	public void successPage(BankModel bmodel) {
		System.out.println("Press 1 to see other option, press 2 to logout:\n1.OPTIONS\n2.LOGOUT\n");
		int userInput=in.nextInt();
		if (userInput == 1) {
			optionPage(bmodel);
		} else if (userInput == 2) {
			logoutPage();
		} else {
			logoutPage();
		}
	}

	public void logoutPage() {
		System.out.println("Thank you for visiting us!See you soon.");
	}

	public BankModel getBmodel() {
		return bmodel;
	}

	public void setBmodel(BankModel bmodel) {
		this.bmodel = bmodel;
	}

}
