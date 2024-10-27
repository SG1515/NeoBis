# NeoBis

## WLF(watchListFiltering)

- 위험 고객 리스트에서 탐색

```java
	public boolean watchListFiltering(Customer customer) {
		for(Customer c : criticalCustomer) {
			if(c.getName().equals(customer.getName()) && (c.getRegistrationNumber() == customer.getRegistrationNumber())){
				return true;
			}
		}
		return false;
	}
```

## STR(suspiciousTransactionReport)

(가) 보고대상

- 금융거래와 관련하여 수수한 재산이 불법재산이라고 의심되는 합당한 근거가 있는 거래
- 거래 상대방이 자금세탁행위나 공중협박자금조달행위를 하고 있다고 의심되는 합당한 근거가 있는 거래

<예시>

- 직업 및 사업내용 등이 파악되지 않는 고객이 다수의 타인명의의 계좌를 소지하고, 거액의 금융거래를 하는 경우
    - 직업이 null이거나, 계좌의 갯수가 2개 이상이면서 천만원 이상 거래하는 경우
    
    ```java
    if(customer.getJop() == null && customer.getAccounts().size() > 2 && amount >= limit)) {
    			flag = false;
    		}
    ```
    
- 미성년자 명의로 개설된 계좌에 거액의 금융거래가 발생하는 경우
    - 고객의 나이가 미성년자이고, 천만원 이상 거래 시
    
    ```java
    else if(isTeenage(customer.getRegistrationNumber()) && amount >= limit) {
    			flag = false;
    		}
    ```
    
- 평소 평이한 금융거래형태를 유지하고 있는 급여생활자, 개인사업자, 중소기업 등의 계좌에서 거액의 금융거래가 발생하고, 자금의 실제당사자 여부를 확인하는 과정에서 의문점이 있는 경우
    - WLF에 걸리지 않은 고객이지만 owner가 false인 경우
- 금융거래자의 요청에 의하여 현금거래로 처리하는 거래 등 평소 금융거래 형태와 상이한 거액의 금융거래 또는 분할거래를 하고 있다는 의심이 있는 경우 등
    - 현금거래 처리 감지하려면 거래에 현금인지 아닌지 추가해야함 → 구현 X
    - 분할거래만 구현(하루동안 거래가 3회 이상인 경우)
    
    ```java
    public List<Trade> getTradesForToday(Customer customer) {
    		LocalDate today = LocalDate.now();
    		return customer.getAccounts().get(0).getTrades().stream()
    				.filter(trade -> trade.getTime().toLocalDate().equals(today)).collect(Collectors.toList());
    	}
    ```
    
    ---
    

## CTR(currencyTransactionReport)

- 보고 기준금액 : 원화 1천만원 (법개정후 원화 2천만원 -> 원화 1천만원)
    - 보고만 가능하도록 작성
    
    ```java
    public void currencyTransactionReport(long amonut) {
    		if (amonut >= limit)
    			System.out.println("원화 1천만원 이상 거래 발견!! 금융정보분석원으로 보고가 완료되었습니다.");
    	}
    ```
    
    ---
    
    ## CDD(customerDueDiligence)
    
    휴대폰 번호 입력하세요
    
    주소 서울시 종로구 혜화동 → 동까지 입력
    
    회원정보 → 이름. 실 소유자, 주민번호 앞자리
    
    기타 정보 → 직업
    
    이메일 인증
