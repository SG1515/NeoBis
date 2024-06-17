package deposit;

public class domain {
	public static void main(String[] args) throws Exception {
		Bank bank = new Bank();
		
//		bank.findCustomer();
//		
//		bank.addAccount();
//		
////		bank.findCustomer();
////		
////		bank.addAccount();
//		
//		bank.deposit();
//		
////		bank.remittance();
//		
////		bank.withdraw();
		ExchangeRate e = new ExchangeRate();
		Customer c = new Customer("yerim", "010-1111-1111", "서울 강북구", "990101-01010101", "무직", "yerim@naver.com");
		Customer c1 = new Customer("yerim", "011-1111-1111", "서울 강북구", "990101-01010111", "무직", "yerim@naver.com");
		c1.addAccount(Country.USA,1234);
		c.addAccount(Country.KOR, 1234);
		c.addAccount(Country.USA, 1234);
		c.getAccounts().get(1).setBalance(100000);
		c.getAccounts().get(0).setBalance(100000);
		for(int i=0; i<c1.getAccounts().size(); i++ ) {
			System.out.println(c1.getAccounts().get(i).getAccountNumber());
		}
		
		//외화를 원화 환전
//		bank.foreignToKWRExchange(c, e);
		
		//원화를 외화로 환전
//		bank.KRWToForeignExchange(c, e);
		bank.foreignRemittance(c, c1, e); 
	}
}