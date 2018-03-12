package com.revature.repository;

import com.revature.model.BankModel;

public interface BankRepositoryDAO {
	public BankModel getUserCredintialDAO(String uname, String pass);
	public boolean registerUserDAO(BankModel bmodel);
	public BankModel getUserModelDAO(BankModel bmodel);
	public boolean depositToBnakDAO(BankModel bmodel);
	public boolean withdrawFromBankDAO(BankModel bmodel);

}
