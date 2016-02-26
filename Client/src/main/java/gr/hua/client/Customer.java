package gr.hua.client;

public class Customer {

	private int id;
	private String fname;
	private String lname;
	private int afm;
	private String adt;
	private String tel;
	private int paymentAccount;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public int getAfm() {
		return afm;
	}
	public void setAfm(int afm) {
		this.afm = afm;
	}
	public String getAdt() {
		return adt;
	}
	public void setAdt(String adt) {
		this.adt = adt;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getPaymentAccount() {
		return paymentAccount;
	}
	public void setPaymentAccount(int paymentAccount) {
		this.paymentAccount = paymentAccount;
	}
}
