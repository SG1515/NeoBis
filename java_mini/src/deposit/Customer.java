package deposit;

import java.util.*;

public class Customer {
	private String name;
	private String phone;
	private String address;
	private boolean owner;
	private String registrationNumber;
	private String job;
	private String email;

	private List<Account> accounts;

	public Customer() {}
	
	public Customer(String name, String phone, String address, String registrationNumber, String job, String email) {
		super();
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.owner = true;
		this.registrationNumber = registrationNumber;
		this.job = job;
		this.accounts = new ArrayList<>();
		this.email = email;
	}
	
	public void printCustomer(){
		System.out.println("이름 : " + name);
		System.out.println("전화번호 : " + phone);
		System.out.println("주소 : " + address);
		// System.out.println("owner");
	}

	public void printAccounts(){
		for (int i = 0; i < accounts.size(); i++) {
			System.out.println("==============" + (i+1) + "==============");
			accounts.get(i).printAccount();
			System.out.println("===============================");
		}
	}
	
	public int findAccountByAccountNumber(String accountNumber, Country country) {
		for (int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).getAccountNumber().equals(accountNumber)) {
				if(accounts.get(i).getCountry().equals(country)) {
					return i;
				} else {
					return -2;
				}
			}
		}
		return -1;
	}
	
//	public void selectCurAccount(int idxAccount) {
//		curAccount = accounts.get(idxAccount);
//	}
	
	public void addAccount(Country country, int password) {
		accounts.add(new Account(country, password));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isOwner() {
		return owner;
	}

	public void setOwner(boolean owner) {
		this.owner = owner;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	public Account getCurAccount() {
//		return curAccount;
//	}
//
//	public void setCurAccount(Account curAccount) {
//		this.curAccount = curAccount;
//	}
}
