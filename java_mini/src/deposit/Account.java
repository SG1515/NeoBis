package deposit;

import java.util.ArrayList;
import java.util.List;

public class Account {
	private String accountNumber;
	private long balance;
	private List<Trade> trades;
	private Country country;
	private int password;

	public Account() {}

	public Account(Country country, int password) {
		// XXX-XX-XXXX-XXX
		this.accountNumber = "111-1111-1111";
//		 this.accountNumber = accountNumber; -> 랜덤 숫자
		this.balance = 0;
		this.trades = new ArrayList<>();
		this.country = country;
		this.password = password;
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

	public void deposit(long amount){
		balance += amount;
		trades.add(new Trade());
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
