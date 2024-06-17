package deposit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class domain {
	public static void main(String[] args) throws IOException {
		
//		System.out.println();
//	
//		int idx = 1;
//		System.out.println(ExchangeRate.getExchanges().size());
//		ExchangeRate.setExchanges();
//		ExchangeRate.printExchanges();
//		
//		System.out.println(ExchangeRate.calExchange(Country.USA, 120000));
		
		//ȯ�� �׽�Ʈ�ϱ�
		List<Customer> customers = new ArrayList<>();
		ExchangeRate r = new ExchangeRate();
		customers.add(new Customer("yerim", "010-1111-1111", "����", "990101-01010101", "�ǻ�", "yerim@naver.com"));
		customers.add(new Customer("seonggu", "010-1111-1112", "����", "990101-11010101", "ġ������", "seonggu@naver.com"));
		customers.get(1).addAccount(Country.KOR, 1234);
		customers.get(1).addAccount(Country.USA, 1234);
		customers.get(1).getAccounts().get(1).deposit(100000);
		customers.get(1).getAccounts().get(0).deposit(100000);
		
		
		
		System.out.println("1. 원화를 환전하기.");
		System.out.println("2. 외국돈 환전하기.");
		System.out.print("선택해주세요. > ");
		
		int choice = Integer.parseInt(DataInput.readLine());
		
		if(choice == 1) {
			r.KRWToForeignExchange(customers.get(1), r);

		} else if (choice == 2) {
			r.foreignToKWRExchange(customers.get(1), r);
		}
		
		
		
	}
}
