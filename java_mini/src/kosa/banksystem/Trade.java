package kosa.banksystem;

import java.time.LocalDateTime;

public class Trade {
	private int id;
	private LocalDateTime time;
	private long amount;
	private boolean tradeType;
	private long nowBalance;

	public Trade() {}

	public Trade(int id, long amount, boolean tradeType, long nowBalance) {
		this.id = id;
		this.time = LocalDateTime.now();
		this.amount = amount;
		this.tradeType = tradeType;
		this.nowBalance = nowBalance;
	}

	public void printTrade(){
		System.out.println("아이디 : " + id);
		System.out.println("거래일자 : " + time);
		System.out.println("거래금액 : " + amount);
		System.out.println("거래유형 : " + (tradeType ? "입금" : "출금"));
		System.out.println("거래 후 잔액 : ");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public boolean isTradeType() {
		return tradeType;
	}

	public void setTradeType(boolean tradeType) {
		this.tradeType = tradeType;
	}

	public long getNowBalance() {
		return nowBalance;
	}

	public void setNowBalance(long nowBalance) {
		this.nowBalance = nowBalance;
	}
}
