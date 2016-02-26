package gr.hua.client;

public class Account {

	private int accountId;
	private String username;
	private String password;
	private String email;
	private int customerId;
	
	public Account(){}
	
	public Account(String username,String password){
		this.username = username;
		this.password = password;
	}
	
	public Account(int accountId,String username,String password, String email,int customerId){
		this.accountId=accountId;
		this.username=username;
		this.password=password;
		this.email=email;
		this.customerId=customerId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
}
