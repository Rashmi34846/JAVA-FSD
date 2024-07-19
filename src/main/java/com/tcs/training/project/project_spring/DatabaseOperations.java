package com.tcs.training.project.project_spring;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("dbOperate")
public class DatabaseOperations {
	
	private JdbcTemplate jdbcTemplate;
	//private DataSource dataSource;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public String getUser(long accNumber, int pin) {
		String query = "select userName from userDetails where accountNumber = ? and pinumber = ?";
		return jdbcTemplate.queryForObject(query, String.class, accNumber, pin);
	}
	
	public void withdrawMoneyByAccountNumberAndPin(long acc_number, int pin, double amount) {
		String query = "update userDetails set balance = balance - ? where accountNumber = ? and pinNumber = ?";
		jdbcTemplate.update(query, amount, acc_number, pin);
	}
	
	public void depositMoneyByAccountNumberAndPin(long acc_number, int pin, double amount) {
		String query = "update userDetails set balance = balance + ? where accountNumber = ? and pinNumber = ?";
		jdbcTemplate.update(query, amount, acc_number, pin);
	}
	
	public double fetchBalance(long acc_number, int pin) {
		String query = "select balance from userDetails where accountNumber = ? and pinNumber = ?";
		return jdbcTemplate.queryForObject(query, Double.class, acc_number, pin);
	}
	
	public void updateTransactionsTable(long acc_number, double money, String type) {
		String query = "insert into transactions(accountNumber, amount, transactionType) values(?, ?, ?)";
		jdbcTemplate.update(query, acc_number,  money, type);
	}
	
	public List<TransactionDetails> fetchAllTransaction(long acc_number) {
		String query = "select * from transactions where accountNumber = ?";
		return jdbcTemplate.query(query, new BeanPropertyRowMapper<TransactionDetails>(TransactionDetails.class), acc_number);
	}
	
	public void insertNewUser(long acc_number, int pin, String name, String pan, String gender, String address, double balance) {
		String query = "insert into userDetails values(?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(query, acc_number, pin, name, pan, gender, address, balance);
	}
}
