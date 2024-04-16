package ATM.ATM;

public class request {
private Accountdata at;
private Transactions t;
@Override
public String toString() {
	return "request [at=" + at + ", t=" + t + "]";
}
public Accountdata getAt() {
	return at;
}
public void setAt(Accountdata at) {
	this.at = at;
}
public Transactions getT() {
	return t;
}
public void setT(Transactions t) {
	this.t = t;
}
public request(Accountdata at, Transactions t) {
	super();
	this.at = at;
	this.t = t;
}
public request() {
	super();
	// TODO Auto-generated constructor stub
}

}
