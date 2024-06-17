package deposit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ExchangeRate {
	private static Map<Country, Double> exchanges = new HashMap<>();;
	private static List<String> call = new ArrayList<>();
	
	public ExchangeRate() {
		
	}
	
	public static Map<Country, Double> getExchanges() {
		return exchanges;
	}

	
	public static void setExchanges() throws IOException {
		System.out.println("1.USA 2.CHI 3.JAP 4.KOR 5.UK");
		System.out.print("갱신할 통화를 선택 > ");
		int select = Integer.parseInt(DataInput.readLine());
		
		System.out.print("갱신된 환율값을 입력 > ");
		int update = Integer.parseInt(DataInput.readLine());
		
		
		Country[] arr = Country.values();
		exchanges.put(arr[select-1], (double) update);
	
	}

	//정적 초기화 블럭
	static{
		exchanges.put(Country.USA, 1200.0);
		call.add("USD");
		exchanges.put(Country.CHI, 180.0);
		call.add("CNY");
		exchanges.put(Country.JAP, 8.26);
		call.add("JPY");
		exchanges.put(Country.KOR, 1.0);
		call.add("KRW");
		exchanges.put(Country.UK, 1400.0);
		call.add("EUR");
		
	}
	
	public static void printExchanges() {
		
		TreeMap<Country, Double> sortedMap = new TreeMap<>(exchanges);
		
		int idx = 1;
		for(Map.Entry<Country, Double> entry : sortedMap.entrySet()) {
			System.out.println("국가 이름 : " + entry.getKey());
			System.out.println("현재 환율 : " + entry.getValue() + call.get(idx-1));
			System.out.println();
			idx++;
		}
	} 
	

	public static List<String> getCall() {
		return call;
	}

	public static void setCall(List<String> call) {
		ExchangeRate.call = call;
	}

	// 환율 계산 메소드 완료
	// exchangeAmount - 원화로 들어옴 
    public static long calExchange(Country country, long exchangeAmount) {
        Double rate = exchanges.get(country);
        if (rate == null) {
            throw new IllegalArgumentException("환율 정보가 없습니다: " + country);
        }
        return Math.round(exchangeAmount / rate); // 환율에 따라 금액 변환
    }
    
    
  

	public void foreignToKWRExchange(Customer depositor, ExchangeRate exchangeRate) throws IOException{
		System.out.println("고객님의 계좌목록입니다.");
		depositor.printAccounts();
		System.out.println();
		
		System.out.println("1.USA 2.CHI 3.JAP 4.KOR 5.UK");
		System.out.print("계좌를 선택해주세요. > ");
		
		int func = Integer.parseInt(DataInput.readLine());
		
		if(func == 4) {
			System.out.println("계좌 선택을 다시 해주세요.");
			return;
		}
		
		Country[] arr = Country.values();
		Country choiceCountry = arr[func-1];
		String choice = choiceCountry.name();
		
		System.out.print("환전할 금액을 입력해주세요. > ");
		long changeMoney = Long.parseLong(DataInput.readLine());
		long balance = 0;
		long printBalance = 0;
		//고객의 계좌에서 돈을 꺼낼 계좌를 가져오기.
		for(int i=0; i<depositor.getAccounts().size(); i++) {
			if(choice.equals(depositor.getAccounts().get(i).getCountry().name())){
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
		
		long changed = (long) (changeMoney * exchangeRate.exchanges.get(choiceCountry));
		System.out.println("환전된 금액은 > " + changed + call.get(3));
		System.out.println("해당계좌 잔액은 > " + printBalance);
				
	}

	public void KRWToForeignExchange(Customer depositor, ExchangeRate exchangeRate) throws IOException {
		
		System.out.println("1.USD 2.CNY 3.JPY 4.EUR");
		System.out.print("환전하고 싶은 통화를 선택해주세요. >");
		int tmp = Integer.parseInt(DataInput.readLine());
		
		int choiceAccount = 0;
		//한국 계좌가 있어서 중간 치환해줌.
		switch(tmp) {
			case 1:
				break;
			case 2:
				choiceAccount = 1;
				break;
			case 3:
				choiceAccount = 2;
				break;
			case 4:
				choiceAccount = 4;
				break;
		}
		
		Country[] arr = Country.values();
		Country choiceCountry = arr[choiceAccount];
		String choiceCountryStr = choiceCountry.name();
		
		
		System.out.print("환전할 금액을 입력해주세요. > ");
		long changeMoney = Long.parseLong(DataInput.readLine());
		long balance = 0;
		long printBalance = 0;
		
		//고객의 계좌에서 돈을 꺼낼 계좌를 가져오기.
		for(int i=0; i<depositor.getAccounts().size(); i++) {
			if(choiceCountryStr.equals(depositor.getAccounts().get(i).getCountry().name())){
				balance = depositor.getAccounts().get(i).getBalance() - changeMoney;
				if (balance >= 0) {
					depositor.getAccounts().get(i).setBalance(balance);
					printBalance = depositor.getAccounts().get(i).getBalance();
					break;
				} else {
					System.out.println("잔액이 부족합니다.");
					return;
				}
			}
		}

		int idx =0;
		String moneyName = "";
		TreeMap<Country, Double> sortedMap = new TreeMap<>(exchanges);
		for(Map.Entry<Country, Double> entry : sortedMap.entrySet()) {
			if(entry.getKey().name().equals(choiceCountryStr)) {
				moneyName = call.get(idx);
			}
			idx++;
		}		
		long changed = (long) (changeMoney / exchangeRate.exchanges.get(choiceCountry));
		System.out.println("환전된 금액은 > " + changed +moneyName);
		System.out.println("해당계좌 잔액은 > " + printBalance);
	}
 
}
