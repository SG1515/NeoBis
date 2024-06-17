package deposit;

import java.io.*;
import java.util.*;

import deposit.DataInput;

public class Bank {
	private List<Customer> customers;
	private ExchangeRate exchangeRate;
	private AntiMoneyLaundering aml;
	private static final int PASSWORD = 1234;
	private Customer curCustomer;

	public Bank() {
		aml = new AntiMoneyLaundering();
		this.customers = new ArrayList<>();
		customers.add(new Customer("yerim", "010-1111-1111", "서울 강북구", "990101-01010101", "무직", "yerim@naver.com"));
		customers.add(new Customer("song", "010-1111-1111", "서울 강북구", "990101-01010102", "무직", "yerim@naver.com"));
	}

	
	/**
	 * 고객 찾기 : 고객 주민번호로 현재 고객 등록
	 * 
	 * @throws Exception
	 */
	public void findCustomer() throws Exception {
		System.out.print("찾는 고객의 주민번호를 입력하세요 > ");
		String registrationNumber = DataInput.readLine();

		boolean flag = false;
		for (Customer customer : customers) {
			if (registrationNumber.equals(customer.getRegistrationNumber())) {
				curCustomer = customer;
				flag = true;
			}
		}
	}

	/**
	 * 계좌 생성 : 국가 입력받아서 계좌 생성 + 비밀번호 4자리 입력 -> 계좌 번호 생성 로직 구현 X -> 랜덤 12자리로 생성해야함
	 * 
	 * @throws Exception
	 */
	public void addAccount() throws Exception {
		Country[] countrys = Country.values();
		for (int i = 0; i < countrys.length; i++) {
			System.out.println((i + 1) + ". " + countrys[i]);
		}

		System.out.print("국가 선택 > ");
		int selectCountry = Integer.parseInt(DataInput.readLine());

		String pw1 = "";
		while (true) {
			System.out.print("비밀번호 4자리 입력 > ");
			pw1 = DataInput.readLine();

			if (pw1.matches("[\\d]{4}"))
				break;
			System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
		}

		while (true) {
			System.out.print("비밀번호 4자리 입력 > ");
			String pw2 = DataInput.readLine();

			if (pw2.matches(pw1))
				break;
			System.out.println("비밀번호가 일치하지않습니다. 다시 입력해주세요.");
		}

		curCustomer.addAccount(countrys[selectCountry - 1], Integer.parseInt(pw1));

		System.out.println("========== 계좌 생성이 완료되었습니다 ==========");

		curCustomer.getAccounts().get(curCustomer.getAccounts().size() - 1).printAccount();
	}

	/**
	 * 입금 : 자금세탁방지 -> 계좌 리스트 출력 -> 계좌 선택 -> 거래 금액 입력 -> 비밀번호 입력 -> 입금 -> 완료
	 * 
	 * @throws Exception
	 */
	public void deposit() throws Exception {

		// 계좌 탐색
		curCustomer.printAccounts();

		// 계좌 선택
		System.out.print("계좌를 선택해주세요 > ");
		int idxAccount = Integer.parseInt(DataInput.readLine()) - 1;
//		curCustomer.selectCurAccount(idxAccount);

		// 거래금액 입력받기
		System.out.print("입금하실 금액을 입력해주세요 > ");
		long amount = Long.parseLong(DataInput.readLine());

		// 자금세탁방지
		if (!aml.knowYourCustomer(curCustomer, amount)) {
			System.out.println();
			return;
		}

		// 비밀번호 입력
//		curCustomer.getAccounts().get(idxAccount).checkPassword();

		// deposit
		curCustomer.getAccounts().get(idxAccount).deposit(amount);

		// 완료
		System.out.println("========== 입금이 완료되었습니다 ==========");
	}

	/**
	 * 출금 : 자금세탁방지 -> 계좌 리스트 출력 -> 계좌 선택 -> 거래 금액 입력 -> 비밀번호 입력 -> 출금 -> 완료
	 * 
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public void withdraw() throws Exception {
		// 자금세탁방지

		// 계좌 탐색
		curCustomer.printAccounts();

		// 계좌 선택
		System.out.print("계좌를 선택해주세요 > ");
		int idxAccount = Integer.parseInt(DataInput.readLine()) - 1;
//		curCustomer.selectCurAccount(idxAccount);

		// 거래금액 입력받기
		System.out.print("출금하실 금액을 입력해주세요 > ");
		long amount = Long.parseLong(DataInput.readLine());

		// 자금세탁방지
		if (!aml.knowYourCustomer(curCustomer, amount)) {
			System.out.println();
			return;
		}

		// 비밀번호 입력
		curCustomer.getAccounts().get(idxAccount).checkPassword();

		// withdraw
		if (curCustomer.getAccounts().get(idxAccount).withdraw(amount)) {
			// 완료
			System.out.println("========== 출금이 완료되었습니다 ==========");
		} else {
			// 실패
			System.out.println("=========== 잔액이 부족합니다 ===========");
		}

	}

	/**
	 * 송금 : 자금세탁방지 -> 계좌 리스트 출력 -> 계좌 선택 -> 받는 분 계좌번호 입력 -> 받는 분 일치하는 계좌 찾기 -> 거래 금액
	 * 입력 -> 비밀번호 입력 -> 출금 -> 입금 -> 완료
	 * 
	 * @throws Exception
	 */
	public void remittance() throws Exception {

		// 계좌 탐색
		curCustomer.printAccounts();

		// 계좌 선택
		System.out.print("송금 계좌를 선택해주세요 > ");
		int idxAccount = Integer.parseInt(DataInput.readLine()) - 1;
//		curCustomer.selectCurAccount(idxAccount);

		Customer recipient = null;
		int idxRecipientAccount = -1;

		while (true) {
			// 받는 분 계좌번호 입력
			System.out.print("받는 분 계좌번호 입력 > ");
			String recipientAccountNumber = DataInput.readLine();

			// 일치하는 계좌 찾기
			for (int i = 0; i < customers.size(); i++) {
				idxRecipientAccount = customers.get(i).findAccountByAccountNumber(recipientAccountNumber,
						curCustomer.getAccounts().get(idxAccount).getCountry());

				if (idxRecipientAccount != -1) {
					recipient = customers.get(i);
					break;
				}
			}

			if (idxRecipientAccount == -1) {
				System.out.print("============== 해당 계좌는 존재하지 않습니다. 다시 입력해주세요. ==============");
			} else {
				break;
			}
		}

		// 거래금액 입력받기
		System.out.print("송금하실 금액을 입력해주세요 > ");
		long amount = Long.parseLong(DataInput.readLine());

		// 자금세탁방지
		if (!aml.knowYourCustomer(curCustomer, amount)) {
			System.out.println();
			return;
		}

		// 비밀번호 입력
		curCustomer.getAccounts().get(idxAccount).checkPassword();

		// withdraw
		if (curCustomer.getAccounts().get(idxAccount).withdraw(amount)) {
			// 입금
			recipient.getAccounts().get(idxRecipientAccount).deposit(amount);

			// 완료
			System.out.println("========== 송금이 완료되었습니다 ==========");

		} else {
			// 실패
			System.out.println("=========== 잔액이 부족합니다 ===========");
		}
	}

	public void foreignToKWRExchange(Customer depositor, ExchangeRate exchangeRate) throws IOException {
		System.out.println("고객님의 계좌목록입니다.");
		depositor.printAccounts();
		System.out.println();

		System.out.println("1.KOR 2.USA 3.CHI 4.JAP 5.UK");
		System.out.print("계좌를 선택해주세요. > ");

		int func = Integer.parseInt(DataInput.readLine());

		if (func == 1) {
			System.out.println("계좌 선택을 다시 해주세요.");
			return;
		}

		Country[] arr = Country.values();
		Country choiceCountry = arr[func - 1];
		String choice = choiceCountry.name();

		System.out.print("환전할 금액을 입력해주세요. > ");
		long changeMoney = Long.parseLong(DataInput.readLine());
		long balance = 0;
		long printBalance = 0;
		
		// 고객의 계좌에서 돈을 꺼낼 계좌를 가져오기.
		for (int i = 0; i < depositor.getAccounts().size(); i++) {
			if (choice.equals(depositor.getAccounts().get(i).getCountry().name())) {
				balance = depositor.getAccounts().get(i).getBalance() - changeMoney;
				if (balance >= 0) {
					depositor.getAccounts().get(i).setBalance(balance);
					printBalance = depositor.getAccounts().get(i).getBalance();
				} else {
					System.out.println("잔액이 부족합니다.");
					return;
				}
			}
		}
		

		double changed =  ( ((double)changeMoney) * ExchangeRate.getExchanges().get(choiceCountry));
		// 자금세탁방지
		if (!aml.knowYourCustomer(depositor, (long)changed)) {
			System.out.println();
			return;
		}
		System.out.println("환전된 금액은 > " + Math.round(changed) + exchangeRate.getCall().get(3));
		System.out.println("해당계좌 잔액은 > " + printBalance);

	}

	public void KRWToForeignExchange(Customer depositor, ExchangeRate exchangeRate) throws IOException {

		System.out.println("1.USA 2.CHI 3.JAP 4.UK");
		System.out.print("환전하고 싶은 통화를 선택해주세요. >");
		int choiceAccount = Integer.parseInt(DataInput.readLine());

		Country[] arr = Country.values();
		Country choiceCountry = arr[choiceAccount];
		String choiceCountryStr = choiceCountry.name();
		
		long changeMoney = 0;
		while(true) {
			System.out.print("환전할 금액을 입력해주세요. > ");
			changeMoney = Long.parseLong(DataInput.readLine());
			double tmpExchange = exchangeRate.getExchanges().get(choiceCountry);
			if (changeMoney >= tmpExchange) break;
		}
		
		// 자금세탁방지
		if (!aml.knowYourCustomer(depositor, changeMoney)) {
			System.out.println();
			return;
		}
		
		long balance = 0;
		long printBalance = 0;

		// 고객의 계좌에서 돈을 꺼낼 계좌를 가져오기.
		for (int i = 0; i < depositor.getAccounts().size(); i++) {
			if (choiceCountryStr.equals(depositor.getAccounts().get(i).getCountry().name())) {
				balance = depositor.getAccounts().get(choiceAccount).getBalance() - changeMoney;
				if (balance >= 0) {
					depositor.getAccounts().get(choiceAccount).setBalance(balance);
					printBalance = depositor.getAccounts().get(choiceAccount).getBalance();
					break;
				} else {
					System.out.println("잔액이 부족합니다.");
					return;
				}
			}
		}

		//해당통화 이름가져오기
		int idx = 0;
		String moneyName = "";
		TreeMap<Country, Double> sortedMap = new TreeMap<>(ExchangeRate.getExchanges());
		for (Map.Entry<Country, Double> entry : sortedMap.entrySet()) {
			if (entry.getKey().name().equals(choiceCountryStr)) {
				moneyName = exchangeRate.getCall().get(idx);
			}
			idx++;
		}
		double changed =  ( ((double)changeMoney) / ExchangeRate.getExchanges().get(choiceCountry));
		System.out.println("환전된 금액은 > " + Math.round(changed) + moneyName);
		System.out.println("해당계좌 잔액은 > " + printBalance);
	}


	public void foreignRemittance(Customer depositor,Customer recipient, ExchangeRate exchangeRate) throws Exception {
		
		System.out.println("1.USA 2.CHI 3.JAP 4.UK");
		System.out.print("송금할 통화의 계좌를 선택해주세요. >");
		int choiceAccount = Integer.parseInt(DataInput.readLine());

		Country[] arr = Country.values();
		Country choiceCountry = arr[choiceAccount];
		String choiceCountryStr = choiceCountry.name();
		
		int idxRecipientAccount = -1;
		while (true) {
			// 받는 분 계좌번호 입력
			System.out.print("받는 분 계좌번호 입력 > ");
			String recipientAccountNumber = DataInput.readLine();

			// 일치하는 계좌 찾기
			for (int i = 0; i < customers.size(); i++) {
				idxRecipientAccount = customers.get(i).findAccountByAccountNumber(recipientAccountNumber,
						choiceCountry);

				if (idxRecipientAccount != -1) {
					recipient = customers.get(i);
					break;
				}
			}

			if (idxRecipientAccount == -1) {
				System.out.println("============== 해당 계좌는 존재하지 않습니다. 다시 입력해주세요. ==============");
			} else {
				break;
			}
		}

		long changeMoney = 0;
		while(true) {
			System.out.print("송금할 금액을 입력해주세요. > ");
			changeMoney = Long.parseLong(DataInput.readLine());
			double tmpExchange = exchangeRate.getExchanges().get(choiceCountry);
			if (changeMoney >= tmpExchange) break;
		}
		
		// 자금세탁방지
		if (!aml.knowYourCustomer(depositor, changeMoney)) {
			System.out.println();
			return;
		}
		
		// 비밀번호 입력
		curCustomer.getAccounts().get(choiceAccount).checkPassword();

		// withdraw
		if (curCustomer.getAccounts().get(choiceAccount).withdraw(changeMoney)) {
			// 입금
			recipient.getAccounts().get(idxRecipientAccount).deposit(changeMoney);

			// 완료
			System.out.println("========== 송금이 완료되었습니다 ==========");

		} else {
			// 실패
			System.out.println("=========== 잔액이 부족합니다 ===========");
		}
		
	}

}
