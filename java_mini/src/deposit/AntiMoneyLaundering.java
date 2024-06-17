package deposit;

import java.util.ArrayList;
import java.util.List;

public class AntiMoneyLaundering {
	private long limit;
	private List<Customer> criticalCustomer;

	public AntiMoneyLaundering() {
		this.limit = 10000000;
		criticalCustomer = new ArrayList<>();
	}

	public boolean customerInformationCheck(Customer customer) {
		if (watchListFiltering(customer)) { // 필터링에 걸렸으면 다음 검사 진행
			if (suspiciousTransactionReport(customer)) {
				if (currencyTransactionReport(customer)) {

				}
			}
		}
		return false;
	}

	public boolean watchListFiltering(Customer customer) {
		for(Customer c : criticalCustomer) {
			if(c.getName().equals(customer.getName()) && (c.getRegistrationNumber() == customer.getRegistrationNumber())){
				return true;
			}
		}
		return false;
	}

	public boolean suspiciousTransactionReport(Customer customer) {
        System.out.println("의심 거래 발견");
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
}
