package ATM.ATM;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;

public class Accountdata {

private int acid;
private String acnum;
private String acholder;
private String mno;
private Double balance;
private int pin;
private int rand;

public Accountdata(int acid, String acnum, String acholder, String mno, Double balance, int pin, int rand) {
	super();
	this.acid = acid;
	this.acnum = acnum;
	this.acholder = acholder;
	this.mno = mno;
	this.balance = balance;
	this.pin = pin;
	this.rand = rand;
}
public int getRand() {
	return rand;
}
public void setRand(int rand) {
	this.rand = rand;
}
public Accountdata() {
	super();
	// TODO Auto-generated constructor stub
}
public int getAcid() {
	return acid;
}
public void setAcid(int acid) {
	this.acid = acid;
}
public String getAcnum() {
	return acnum;
}
public void setAcnum(String acnum) {
	this.acnum = acnum;
}
public String getAcholder() {
	return acholder;
}
public void setAcholder(String acholder) {
	this.acholder = acholder;
}
public String getMno() {
	return mno;
}
public void setMno(String mno) {
	this.mno = mno;
}
public Double getBalance() {
	return balance;
}
public void setBalance(Double balance) {
	this.balance = balance;
}
public int getPin() {
	return pin;
}
public void setPin(int pin) {
	this.pin = pin;
}
@Override
public String toString() {
	return "Accountdata [acid=" + acid + ", acnum=" + acnum + ", acholder=" + acholder + ", mno=" + mno + ", balance="
			+ balance + ", pin=" + pin + ", rand=" + rand + "]";
}






}
