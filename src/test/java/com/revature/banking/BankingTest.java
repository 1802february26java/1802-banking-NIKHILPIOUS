package com.revature.banking;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.revature.model.BankModel;
import com.revature.service.BankServiceBO;


public class BankingTest {
	private static Logger logger = Logger.getLogger(BankingTest.class);
	private BankServiceBO bnkbo;
	private BankModel bnkmdl;
	
	
	@Before
	public void BeforeTest(){
		bnkbo=new BankServiceBO();
		bnkmdl=new BankModel();
	
		
	}

	@Test
	public void authenticationTest() {
		logger.trace("Testing usercredintial with valid username and password.");
		String expected="NIKHIL";
		//assertThat(expected)
		assertEquals(expected,bnkbo.getUserCredintialBO("NIKHIL", "PIOUS").getUname());
	}
	
	@Test
	public void authenticationTest2() {
		logger.trace("Testing usercredintial with invalid username and password.");
		Assert.assertNull(bnkbo.getUserCredintialBO("newUser","password"));
		
	}
	@Test@Ignore
	public void userRegistrationTest(){
		logger.trace("Testing new user registration option.");
		bnkmdl= new BankModel("NIK2","PASS2",0.0,0.0,0.0);
		assertTrue(bnkbo.registerUserBO(bnkmdl));
		
	}
	@Test @Ignore
	public void successfulDepositTest(){
		logger.trace("Testing balance after succesful depsoit.");
		double expectedBalance=6150D;
		double deposit=99.0;
		bnkmdl=bnkbo.getUserCredintialBO("NIKHIL", "PIOUS");
		bnkmdl.setDeposit(deposit);
		bnkmdl.setBalance(bnkmdl.getBalance()+bnkmdl.getDeposit());
		bnkbo.depositToBnakBO(bnkmdl);
		assertEquals(expectedBalance,bnkbo.getUserCredintialBO("NIKHIL", "PIOUS").getBalance(),0.0);
	}

}
