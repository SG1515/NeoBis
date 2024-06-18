package deposit;

import java.io.IOException;

public class Main {

	public static Bank bank = new Bank();

	public static void main(String[] args) throws Exception {
		
		
		// ⇒ ▒ 🗝🔑🗝🔑⚙✅✔✅✔ 
		while(true) {
			System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒ 🔑  관리자 로그인 🔑 ▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
			System.out.print("PASSWORD 입력 ⇒ ");
			int pw = Integer.parseInt(DataInput.readLine());
			if(bank.login(pw)) {
				System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒ ✔   로그인 성공  ✔  ▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
				break;
			} else {
				System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒ ✖   로그인 실패  ✖  ▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
			}
		}
		
		while(true) {
			System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒ 🔑  관리자 로그인 🔑 ▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
			System.out.println("▒▒▒\t\t ① 개인 거래      \t\t▒▒▒");
			System.out.println("▒▒▒\t\t ② 계좌 관리      \t\t▒▒▒");
			System.out.println("▒▒▒\t\t ③ 환율 관리      \t\t▒▒▒");
			System.out.println("▒▒▒\t\t ④ 고객 관리      \t\t▒▒▒");
			System.out.println("▒▒▒\t\t ⑤ 시스템 종료       \t\t▒▒▒");
			System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
			
			System.out.print("MENU 입력 ⇒ ");
			int menu = Integer.parseInt(DataInput.readLine());
			
			switch(menu) {
			case 1:
				privateTransaction();
				break;
			case 2:
				accountManagement();
				break;
			case 3:
				exchangeRateManagement();
				break;
			case 4:
				break;
			case 5:
				return;
			}
		}
	}
	
	public static void privateTransaction() throws Exception {
		
		while(true) {
			System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒ 🔑  관리자 로그인 🔑 ▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
			System.out.println("▒▒▒\t\t ① 입금      \t\t▒▒▒");
			System.out.println("▒▒▒\t\t ② 출금      \t\t▒▒▒");
			System.out.println("▒▒▒\t\t ③ 송금      \t\t▒▒▒");
			System.out.println("▒▒▒\t\t ④ 환전      \t\t▒▒▒");
			System.out.println("▒▒▒\t\t ⑤ 외환송금       \t\t▒▒▒");
			System.out.println("▒▒▒\t\t ⑥ 뒤로가기       \t\t▒▒▒");
			System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
			
			System.out.print("MENU 입력 ⇒ ");
			int menu = Integer.parseInt(DataInput.readLine());
			
			switch(menu) {
			case 1:
				bank.findCustomer();
				bank.deposit();
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				return;
			}
		}
	}
	
	public static void accountManagement() throws Exception {
		
		while(true) {
			System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒ 🔑  계좌 관리  🔑  ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
			System.out.println("▒▒▒\t\t ① 계좌 생성      \t\t▒▒▒");
			System.out.println("▒▒▒\t\t ② 계좌 삭제      \t\t▒▒▒");
			System.out.println("▒▒▒\t\t ③ 계좌 조회      \t\t▒▒▒");
			System.out.println("▒▒▒\t\t ④ 뒤로가기       \t\t▒▒▒");
			System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
			
			System.out.print("MENU 입력 ⇒ ");
			int menu = Integer.parseInt(DataInput.readLine());
			
			switch(menu) {
			case 1:
				privateTransaction();
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				return;
			}
		}
	}
	
	public static void exchangeRateManagement() throws Exception {
		
		while(true) {
			System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒ 🔑   환율 관리  🔑 ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
			System.out.println("▒▒▒\t\t ① 환율 갱신      \t\t▒▒▒");
			System.out.println("▒▒▒\t\t ② 환율 공시      \t\t▒▒▒");
			System.out.println("▒▒▒\t\t ③ 뒤로가기     \t\t▒▒▒");
			System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
			
			System.out.print("MENU 입력 ⇒ ");
			int menu = Integer.parseInt(DataInput.readLine());
			
			switch(menu) {
			case 1:
				privateTransaction();
				break;
			case 2:
				break;
			case 3:
				return;
			}
		}
	}
	
	public static void customerManagement() throws Exception {
		
		while(true) {
			System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒ 🔑   고객 관리  🔑 ▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
			System.out.println("▒▒▒\t\t ① 고객 등록      \t\t▒▒▒");
			System.out.println("▒▒▒\t\t ② 고객 정보 조회      \t\t▒▒▒");
			System.out.println("▒▒▒\t\t ③ 거래 내역 조회      \t\t▒▒▒");
			System.out.println("▒▒▒\t\t ④ 뒤로가기       \t\t▒▒▒");
			System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
			
			System.out.print("MENU 입력 ⇒ ");
			int menu = Integer.parseInt(DataInput.readLine());
			
			switch(menu) {
			case 1:
				privateTransaction();
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				return;
			}
		}
	}
}