package deposit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ExchangeRate {
	private static Map<Country, Double> exchanges = new HashMap<>();
	private static List<String> call = new ArrayList<>();
	
	
	
	public ExchangeRate() {
		
	}
	
	public static Map<Country, Double> getExchanges() {
		return exchanges;
	}


	
	public static void setExchanges() throws IOException {
		System.out.println("1.KOR 2.USA 3.CHI 4.JAP 5.UK");
		System.out.print("갱신할 통화를 선택 > ");
		int select = Integer.parseInt(DataInput.readLine());
		
		System.out.print("갱신된 환율값을 입력 > ");
		double update = Double.parseDouble(DataInput.readLine());
		
		
		Country[] arr = Country.values();
		exchanges.put(arr[select-1], update);
	
	}

	//정적 초기화 블럭
	static{
		exchanges.put(Country.KOR, 1.0);
		exchanges.put(Country.USA, 1200.0);
		exchanges.put(Country.CHI, 180.0);
		exchanges.put(Country.JAP, 8.26);
		exchanges.put(Country.UK, 1400.0);
		call.add("KRW");
		call.add("USD");
		call.add("CNY");
		call.add("JPY");
		call.add("EUR");
		
	}
	
	public static List<String> getCall() {
		return call;
	}

	public static void setCall(List<String> call) {
		ExchangeRate.call = call;
	}

	public static void setExchanges(Map<Country, Double> exchanges) {
		ExchangeRate.exchanges = exchanges;
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
	


	// 환율 계산 메소드 완료
	// exchangeAmount - 원화로 들어옴 
    public static long calExchange(Country country, long exchangeAmount) {
        Double rate = exchanges.get(country);
        if (rate == null) {
            throw new IllegalArgumentException("환율 정보가 없습니다: " + country);
        }
        return Math.round(exchangeAmount / rate); // 환율에 따라 금액 변환
    }
    
    
 
}
