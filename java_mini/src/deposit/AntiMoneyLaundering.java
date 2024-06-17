package deposit;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jdk.internal.reflect.ReflectionFactory.GetReflectionFactoryAction;

public class AntiMoneyLaundering {
	private long limit;
	private List<Customer> criticalCustomer;

	public AntiMoneyLaundering() {
		this.limit = 10000000;
		criticalCustomer = new ArrayList<>();
	}

	public boolean customerInformationCheck(Customer customer, long amount) {
		if (watchListFiltering(customer)) { // 필터링에 걸렸으면 다음 검사 진행
			if (suspiciousTransactionReport(customer, amount)) {
				if (currencyTransactionReport(customer)) {

				}
			}
		}
		return false;
	}

	public boolean watchListFiltering(Customer customer) {
		for (Customer c : criticalCustomer) {
			if (c.getName().equals(customer.getName())
					&& (c.getRegistrationNumber() == customer.getRegistrationNumber())) {
				return true;
			}
		}
		return false;
	}

	public boolean suspiciousTransactionReport(Customer customer, long amount) {
		
		//직업이 없거나, 개좌의 갯수가 2개 이상이거나, 천 만원 이상 거래하는 경우
		if(customer.getJop() == null && customer.getAccounts().size() > 2 && amount >= limit)) {
			System.out.println("의심 거래 발견!! 금융정보분석원으로 보고가 완료되었습니다.");
			return false;
		}
		//고객이 미성년자이면서 천 만원 이상 거래하는 경우
		else if(isTeenage(customer.getRegistrationNumber()) && amount >= limit) {
			System.out.println("의심 거래 발견!! 금융정보분석원으로 보고가 완료되었습니다.");
			return false;
		}
		// 위험 고객이 아니거나, 계좌의 실 소유자가 아닌 경우
		else if(customer.getOwner()==false) {
			System.out.println("의심 거래 발견!! 금융정보분석원으로 보고가 완료되었습니다.");
			return false;
		}
		// 하루 동안 분할거래가 3회 이상인 경우
		else if(getTradesForToday(customer).size() >= 3) {
			System.out.println("의심 거래 발견!! 금융정보분석원으로 보고가 완료되었습니다.");
			return false;
		}
		
		return true;
	}

	public boolean currencyTransactionReport(Customer customer) {

		return true;
	}

	public boolean customerDueDiligence(Customer customer) {
		// 신원 확인
		if (customer.getName() == null || customer.getName().isEmpty()) {
			System.out.println("이름이 비어 있습니다.");
			return false;
		}
		if (customer.getPhone() == null || customer.getPhone().isEmpty()) {
			System.out.println("전화번호가 비어 있습니다.");
			return false;
		}
		if (customer.getAddress() == null || customer.getAddress().isEmpty()) {
			System.out.println("주소가 비어 있습니다.");
			return false;
		}
		if (customer.getRegistrationNumber() == 0) {
			System.out.println("등록 번호가 유효하지 않습니다.");
			return false;
		}

		// 위험 평가
		if (watchListFiltering(customer)) {
			System.out.println("고객이 고위험 목록에 있습니다.");
			return false;
		}

		// 거래 모니터링
		if (!suspiciousTransactionReport(customer)) {
			System.out.println("의심 거래가 발견되지 않았습니다.");
			return true;
		}

		return true;
	}

	public boolean isTeenage(String customerAge) {
		int age = Integer.parseInt(customerAge.substring(0, 2));
		if (age > 5 && age < 24) {
			return true;
		}
		return false;
	}

	public List<Trade> getTradesForToday(Customer customer) {
		LocalDate today = LocalDate.now();
		return customer.getAccounts().get(0).getTrades().stream()
				.filter(trade -> trade.getTime().toLocalDate().equals(today)).collect(Collectors.toList());
	}
}
