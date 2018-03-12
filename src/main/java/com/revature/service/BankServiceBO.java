package com.revature.service;

import com.revature.model.BankModel;
import com.revature.repository.BankRepositoryJDBC;

public class BankServiceBO {
	
	BankRepositoryJDBC brjdbc = new BankRepositoryJDBC();
	public BankModel getUserCredintialBO(String uname, String pass) {
		// TODO Auto-generated method stub
		return  brjdbc.getUserCredintialDAO(uname,pass);
	}
	public boolean registerUserBO(BankModel bmodel) {
		return brjdbc.registerUserDAO(bmodel);
	}
	public BankModel getUserModelBO(BankModel bmodel) {
		// TODO Auto-generated method stub
		return brjdbc.getUserModelDAO(bmodel);
	}
	public boolean depositToBnakBO(BankModel bmodel) {
		// TODO Auto-generated method stub
		return brjdbc.depositToBnakDAO(bmodel);
	}
	public boolean withdrawFromBankBO(BankModel bmodel) {
		// TODO Auto-generated method stub
		return brjdbc.withdrawFromBankDAO(bmodel);
	}

}
