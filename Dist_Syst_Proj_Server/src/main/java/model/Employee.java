package model;

public class Employee {

	private int id;
	private String fname;
	private String lname;
	private String tel;
	private String acc_username;
	private String acc_password;
	private String acc_role;
	private int dept_working;
	
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAcc_username() {
		return acc_username;
	}
	public void setAcc_username(String acc_username) {
		this.acc_username = acc_username;
	}
	public String getAcc_password() {
		return acc_password;
	}
	public void setAcc_password(String acc_password) {
		this.acc_password = acc_password;
	}
	public String getAcc_role() {
		return acc_role;
	}
	public void setAcc_role(String acc_role) {
		this.acc_role = acc_role;
	}
	public int getDept_working() {
		return dept_working;
	}
	public void setDept_working(int dept_working) {
		this.dept_working = dept_working;
	}
	
	
}
