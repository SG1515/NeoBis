package deposit;

public class ExchangeRate {
	private static Map<Country, Double> exchanges;
	
	static  {
		exchanges.put(Country.USA, 1200.0);
		exchanges.put(Country.CHI, 1200.0);
		exchanges.put(Country.JAP, 1200.0);
		exchanges.put(Country.KOR, 1200.0);
		exchanges.put(Country.UK, 1200.0);
	}
	
	public void printExchanges() {
		int idx = 1;
		for(Map.Entry<Country, Double> entry : exchanges.entrySet()) {
			System.out.println(idx + "번째 국가 이름은 : " + entry.getKey());
			System.out.println("현재 환율 : " + entry.getValue());
			idx++;
		}
	} 
	
	 // 환율 계산 메소드
    public static long calExchange(Country country, long exchangeAmount) {
        Double rate = exchanges.get(country);
        if (rate == null) {
            throw new IllegalArgumentException("환율 정보가 없습니다: " + country);
        }
        return Math.round(exchangeAmount * rate); // 환율에 따라 금액 변환
    }
 
}
