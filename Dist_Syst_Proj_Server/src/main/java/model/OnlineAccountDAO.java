package model;

import java.util.ArrayList;

public interface OnlineAccountDAO {

	public void newAccount(OnlineAccount account);
	public OnlineAccount getAccountById(int id);
	public OnlineAccount getAccountByCustomerId(int customerId);
	public OnlineAccount checkAccount(String username,String password);
	public ArrayList<OnlineAccount> getAllAccounts();
}
