package com.revature.model;

public class BankModel {


	private String uname;
	private String passw;
	private double balance;
	private double deposit;
	private double withdraw;
	public BankModel(String uname, String passw,double balance,double deposit,double withdraw){
		this.uname=uname;
		this.passw=passw;
		this.balance=balance;
		this.deposit=deposit;
		this.withdraw=withdraw;
	}
	
	public BankModel(){
		super();
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPassw() {
		return passw;
	}

	public void setPassw(String passw) {
		this.passw = passw;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public double getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(double withdraw) {
		this.withdraw = withdraw;
	}
	
	@Override
	public String toString() {
		return "BankModel [uname=" + uname + ", passw=" + passw + ", balance=" + balance + ", deposit=" + deposit
				+ ", withdraw=" + withdraw + "]";
	}

}
