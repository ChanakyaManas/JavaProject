package ATM.ATM;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Transactions {
private int tno;
private Double deposit;
private Double withdrawl;
private Double balance;
private Timestamp ts;
private int fkacid;


public int getFkacid() {
	return fkacid;
}
public void setFkacid(int fkacid) {
	this.fkacid = fkacid;
}
public Transactions() {
	super();
	// TODO Auto-generated constructor stub
}
public int getTno() {
	return tno;
}
public int setTno(int tno) {
	return this.tno = tno;
}
public Double getDeposit() {
	return deposit;
}
public void setDeposit(Double deposit) {
	this.deposit = deposit;
}
public Double getWithdrawl() {
	return withdrawl;
}
public void setWithdrawl(Double withdrawl) {
	this.withdrawl = withdrawl;
}
public Double getBalance() {
	return balance;
}
public void setBalance(Double balance) {
	this.balance = balance;
}
public Timestamp getTs() {
	return ts;
}
public void setTs(Timestamp ts) {
	this.ts = ts;
}

public Transactions(int tno, Double deposit, Double withdrawl, Double balance, Timestamp ts, int fkacid) {
	super();
	this.tno = tno;
	this.deposit = deposit;
	this.withdrawl = withdrawl;
	this.balance = balance;
	this.ts = ts;
	this.fkacid = fkacid;
}
@Override
public String toString() {
	return "Transactions [tno=" + tno + ", deposit=" + deposit + ", withdrawl=" + withdrawl + ", balance=" + balance
			+ ", ts=" + ts + ", fkacid=" + fkacid + "]";
}



}
