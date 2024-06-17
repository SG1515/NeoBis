package deposit;

public class domain {
	public static void main(String[] args) throws Exception {
		Bank bank = new Bank();
		
		bank.findCustomer();
		
		bank.addAccount();
		
		bank.findCustomer();
		
		bank.addAccount();
		
		bank.deposit();
		
		bank.remittance();
		
//		bank.withdraw();
	}
}