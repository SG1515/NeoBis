package deposit;

import java.io.*;
import java.util.*;

public class Bank {
	private List<Customer> customers;
	private ExchangeRate exchangeRate;
	private AntiMoneyLaundering aml;
	private static final int PASSWORD = 1234;
	private Customer curCustomer;
	
	public Bank() {
		this.customers = new ArrayList<>();
		customers.add(new Customer("yerim", "010-1111-1111", "서울 강북구", "990101-01010101", "무직", "yerim@naver.com"));
	}
	
	/**
	 * 고객 찾기
	 * : 고객 주민번호로 현재 고객 등록
	 * @throws Exception
	 */
	public void findCustomer() throws Exception {
		System.out.print("찾는 고객의 주민번호를 입력하세요 > ");
		String registrationNumber = DataInput.readLine();
		
		for (Customer customer : customers) {
			if(registrationNumber.equals(customer.getRegistrationNumber())) {
				curCustomer = customer;
			}
		}
	}
	
	/**
	 * 계좌 생성
	 * : 국가 입력받아서 계좌 생성 + 비밀번호 4자리 입력
	 * -> 계좌 번호 생성 로직 구현 X -> 랜덤 12자리로 생성해야함
	 * @throws Exception
	 */
	public void addAccount() throws Exception {
		Country[] countrys = Country.values();
		for (int i = 0; i < countrys.length; i++) {
			System.out.println((i+1) + ". " + countrys[i]);
		}
		
		System.out.print("국가 선택 > ");
		int selectCountry = Integer.parseInt(DataInput.readLine());
		
		String pw1 = "";
		while(true) {
			System.out.print("비밀번호 4자리 입력 > ");
			pw1 = DataInput.readLine();
			
			if(pw1.matches("[\\d]{4}")) break;
			System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
		}
		
		while(true) {
			System.out.print("비밀번호 4자리 입력 > ");
			String pw2 = DataInput.readLine();
			
			if(pw2.matches(pw1)) break;
			System.out.println("비밀번호가 일치하지않습니다. 다시 입력해주세요.");
		}
		
		
		curCustomer.addAccount(countrys[selectCountry-1], Integer.parseInt(pw1));
		
		System.out.println("========== 계좌 생성이 완료되었습니다 ==========");
	}
	
	/**
	 * 입금
	 * : 자금세탁방지 -> 계좌 리스트 출력 -> 계좌 선택 -> 거래 금액 입력 -> 비밀번호 입력 -> 입금 -> 완료
	 * @throws Exception
	 */
	public void deposit() throws Exception {
		// 자금세탁방지
		
		
		// 계좌 탐색
		curCustomer.printAccounts();
		
		// 계좌 선택
		System.out.print("계좌를 선택해주세요 > ");
		int idxAccount = Integer.parseInt(DataInput.readLine()) - 1;
		curCustomer.selectCurAccount(idxAccount);
		
		// 거래금액 입력받기
		System.out.print("입금하실 금액을 입력해주세요 > ");
		long amount = Long.parseLong(DataInput.readLine());
		
		// 비밀번호 입력
		curCustomer.getAccounts().get(idxAccount).checkPassword();
		
		// deposit
		curCustomer.getAccounts().get(idxAccount).deposit(amount);
		
		// 완료
		System.out.println("========== 입금이 완료되었습니다 ==========");
	}
	
	/**
	 * 출금
	 * : 자금세탁방지 -> 계좌 리스트 출력 -> 계좌 선택 -> 거래 금액 입력 -> 비밀번호 입력 -> 출금 -> 완료
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
		curCustomer.selectCurAccount(idxAccount);

		// 거래금액 입력받기
		System.out.print("출금하실 금액을 입력해주세요 > ");
		long amount = Long.parseLong(DataInput.readLine());
		
		// 비밀번호 입력
		curCustomer.getAccounts().get(idxAccount).checkPassword();

		// withdraw
		if(curCustomer.getAccounts().get(idxAccount).withdraw(amount)) {
			// 완료
			System.out.println("========== 출금이 완료되었습니다 ==========");
		} else {
			// 실패
			System.out.println("=========== 잔액이 부족합니다 ===========");
		}
		
	}
}
