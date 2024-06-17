package deposit;

import java.util.List;

public class Customer {
	private String name;
	private String phone;
	private String address;
	private boolean owner;

	private List<Account> accounts;

	public Customer() {}

	public void printCustomer(){
		System.out.println("이름 : " + name);
		System.out.println("전화번호 : " + phone);
		System.out.println("주소 : " + address);
		// System.out.println("owner");
	}

	public void printAccounts(){
		for (Account ac : accounts) {
			ac.printAccount();
		}
	}

	private int findAccount(Account account){
		for (int i = 0; i < accounts.size(); i++) {
			if (account.getAccountNumber().equals(accounts.get(i).getAccountNumber())){
				return i;
			}
		}
		return -1;
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
}
