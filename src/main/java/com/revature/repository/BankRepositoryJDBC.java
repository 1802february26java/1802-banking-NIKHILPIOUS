package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.model.BankModel;
import com.revature.util.ConnectionUtilOrcl;

public class BankRepositoryJDBC implements BankRepositoryDAO {
	private static Logger logger= Logger.getLogger(BankRepositoryJDBC.class);

	@Override
	public BankModel getUserCredintialDAO(String uname, String pass) {
		logger.trace("Checking user credintial by username and password.");
		try(Connection connection = ConnectionUtilOrcl.getConnection()) {
			String sql="SELECT * FROM BANKING WHERE LOWER(A_USERNAME)=LOWER(?) AND A_PASSWORD=?";
			int parameterIndex=0;
			PreparedStatement prepstmt = connection.prepareStatement(sql);
			prepstmt.setString(++parameterIndex, uname);
			prepstmt.setString(++parameterIndex, pass);
			ResultSet rs=prepstmt.executeQuery();
			if(rs.next()) {
				BankModel bmodel = new BankModel(rs.getString("A_USERNAME"),rs.getString("A_PASSWORD"),rs.getDouble("A_BALANCE"),rs.getDouble(4),rs.getDouble("A_DEPOSIT"));
				logger.trace("Valid username and password.");
				return bmodel;
			}
			
		} catch (SQLException e) {
			logger.error("Invalid  username and password",e);
		}
		return null;
	}

	@Override
	public boolean registerUserDAO(BankModel bmodel) {
		logger.trace("Registering new user...");
		try(Connection connection= ConnectionUtilOrcl.getConnection()){
			String uname= bmodel.getUname();
			String pass=bmodel.getPassw();
			int parameterIndex=0;
			String sql= "INSERT INTO BANKING (A_USERNAME,A_PASSWORD) VALUES(?,?)";
			PreparedStatement prepstmt = connection.prepareStatement(sql);
			prepstmt.setString(++parameterIndex, uname);
			prepstmt.setString(++parameterIndex, pass);
			
			if(prepstmt.executeUpdate()>0){
				logger.trace("User registered successfully.");
				return true;
			}
			
		} catch (SQLException e) {
				logger.error("Database connection error, please checkabck soon.",e);
		}

		return false;
	}

	@Override
	public BankModel getUserModelDAO(BankModel bmodel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean depositToBnakDAO(BankModel bmodel) {
		logger.trace("Amount depositing");
		try(Connection connection = ConnectionUtilOrcl.getConnection()){
			int parameterIndex=0;
			String sql= "UPDATE BANKING SET A_DEPOSIT=?, A_BALANCE=? WHERE A_USERNAME=?";
			PreparedStatement prepstmt= connection.prepareStatement(sql);
			prepstmt.setDouble(++parameterIndex, bmodel.getDeposit());
			prepstmt.setDouble(++parameterIndex, bmodel.getBalance());
			prepstmt.setString(++parameterIndex,bmodel.getUname());
			if(prepstmt.executeUpdate()>0){
				logger.trace("Amount deposited successfully.");
				return true;
			}
		} catch (SQLException e) {
			logger.error("Database connection error, please checkabck soon.",e);
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean withdrawFromBankDAO(BankModel bmodel) {
		// TODO Auto-generated method stub
		logger.trace("Amount withdrawing");
		try(Connection connection = ConnectionUtilOrcl.getConnection()){
			int parameterIndex=0;
			String sql= "UPDATE BANKING SET A_WITHDRAW=?, A_BALANCE=? WHERE A_USERNAME=?";
			PreparedStatement prepstmt= connection.prepareStatement(sql);
			prepstmt.setDouble(++parameterIndex, bmodel.getWithdraw());
			prepstmt.setDouble(++parameterIndex, bmodel.getBalance());
			prepstmt.setString(++parameterIndex,bmodel.getUname());
			if(prepstmt.executeUpdate()>0){
				logger.trace("Amount withdrawned successfully.");
				return true;
			}
		} catch (SQLException e) {
			logger.error("Database connection error, please checkabck soon.",e);
		}
		// TODO Auto-generated method stub
		return false;
	}

}
