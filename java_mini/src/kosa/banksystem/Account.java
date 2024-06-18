package kosa.banksystem;

import java.io.*;
import java.util.*;

public class Account {
	private String accountNumber;
	private long balance;
	private List<Trade> trades;
	private Country country;
	private int password;

	public Account() {}

	public Account(Country country, int password) {
		// XXX-XX-XXXX-XXX
		this.accountNumber = makeAccountNumber();
		this.balance = 0;
		this.trades = new ArrayList<>();
		this.country = country;
		this.password = password;
	}
	
	private String makeAccountNumber() {
		String accountNumber = "";
		
		int[] num = new int[12];
		
		for (int i = 0; i < num.length; i++) {
			num[i] = (int)(Math.random() * 10);
		}
		
		for (int i = 0; i < num.length; i++) {
			accountNumber += num[i];
			if(i == 2 || i == 4 || i == 8) {
				accountNumber += "-";
			}
		}
		
		return accountNumber;
	}

	public void printTrades(){
		for (Trade tr : trades) {
			tr.printTrade();
		}
	}

	public void printAccount(){
		System.out.println("계좌번호 : " + accountNumber);
		System.out.println("잔액 : " + balance);
		// printTrades();
		System.out.println("국가 : " + country);
	}
	
	public void checkPassword() throws Exception	{
		String pw = "";
		while(true) {
			System.out.print("비밀번호 4자리 입력 > ");
			pw = DataInput.readLine();
			
			if(Integer.parseInt(pw) == this.password) break;
			System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
		}
	}

	public void deposit(long amount){
		balance += amount;
		trades.add(new Trade(trades.size() == 0 ? 1 : trades.size(), amount, true, balance));
	}
	
	public boolean withdraw(long amount) {
		if(balance >= amount) {
			balance -= amount;
			trades.add(new Trade(trades.size() == 0 ? 1 : trades.size(), amount, false, balance));
			return true;
		}
		return false;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public List<Trade> getTrades() {
		return trades;
	}

	public void setTrades(List<Trade> trades) {
		this.trades = trades;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}
