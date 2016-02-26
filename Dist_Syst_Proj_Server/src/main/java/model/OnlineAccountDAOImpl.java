package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class OnlineAccountDAOImpl implements OnlineAccountDAO{
	
private DataSource dataSource;
	
	public void setDataSource(DataSource datasource){
		this.dataSource = datasource;
	}

	@Override
	public void newAccount(OnlineAccount account) {		//create a new online account
		String query = "insert into online_accounts (username,password,email,customer_id) "
				+ "values(?,?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		try{
			con = dataSource.getConnection();
			ps=con.prepareStatement(query);
			ps.setString(1, account.getUsername());
			ps.setString(2, account.getPassword());
			ps.setString(3, account.getEmail());
			ps.setInt(4, account.getCustomerId());
			int psResult = ps.executeUpdate();
			if(psResult != 0){		//logging message
				System.out.println("New account created, id= " + account.getAccountId());
			}else{
				System.err.println("Account creation FAILED, id= " + account.getAccountId());
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			try{
				ps.close();
				con.close();
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
	}

	@Override
	public OnlineAccount getAccountById(int id) {		//takes as an input an online account's id and return account's data from the database
		String query = "select * from online_accounts where id = ? ";
		OnlineAccount acc = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = dataSource.getConnection();
			ps=con.prepareStatement(query);
			ps.setInt(1,id);
			rs = ps.executeQuery();
			if(rs.next()){
				acc = new OnlineAccount();
				acc.setAccountId(id);
				acc.setUsername(rs.getString("username"));
				acc.setPassword(rs.getString("password"));
				acc.setEmail(rs.getString("email"));
				acc.setCustomerId(rs.getInt("customer_id"));
				System.out.println("Account found::ID--> " + acc.getAccountId());		//logging message
			}
		}catch(SQLException ex){
			ex.printStackTrace();		//if no account found return null
			return null;
		}finally{
			try{
				rs.close();
				ps.close();
				con.close();
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		return acc;
	}

	@Override
	public OnlineAccount getAccountByCustomerId(int customerId) {		//get an online account data using customer's id
		String query = "select * from online_accounts where customer_id = ? ";
		OnlineAccount acc = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = dataSource.getConnection();
			ps=con.prepareStatement(query);
			ps.setInt(1,customerId);
			rs = ps.executeQuery();
			if(rs.next()){
				acc = new OnlineAccount();
				acc.setAccountId(rs.getInt("account_id"));
				acc.setUsername(rs.getString("username"));
				acc.setPassword(rs.getString("password"));
				acc.setEmail(rs.getString("email"));
				acc.setCustomerId(customerId);
				System.out.println("Account found::Customer ID--> " + acc.getCustomerId());		//logging message
			}
		}catch(SQLException ex){
			ex.printStackTrace();		//if no account found return null
			return null;
		}finally{
			try{
				rs.close();
				ps.close();
				con.close();
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		return acc;
	}

	@Override
	public OnlineAccount checkAccount(String username, String password) {		//check if account exist with this username and password
		String query = "select * from online_accounts where username = ? AND password = ?";
		OnlineAccount acc = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = dataSource.getConnection();
			ps=con.prepareStatement(query);
			ps.setString(1,username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()){
				acc = new OnlineAccount();
				acc.setAccountId(rs.getInt("account_id"));
				acc.setUsername(username);
				acc.setPassword(password);
				acc.setEmail(rs.getString("email"));
				acc.setCustomerId(rs.getInt("customer_id"));
				System.out.println("Account found::Customer ID--> " + acc.getAccountId());		//logging message
			}else{
				System.err.println("No account found!");
				return null;
			}
		}catch(SQLException ex){
			ex.printStackTrace();
			return null;
		}finally{
			try{
				rs.close();
				ps.close();
				con.close();
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		return acc;
	}

	@Override
	public ArrayList<OnlineAccount> getAllAccounts() {		//return all online accounts
		String query = "select * from online_accounts";
		OnlineAccount acc = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<OnlineAccount> accList = null;
		try{
			accList = new ArrayList<OnlineAccount>();
			con = dataSource.getConnection();
			ps=con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				acc = new OnlineAccount();
				acc.setAccountId(rs.getInt("account_id"));
				acc.setUsername(rs.getString("username"));
				acc.setPassword(rs.getString("password"));
				acc.setEmail(rs.getString("email"));
				acc.setCustomerId(rs.getInt("customer_id"));
				accList.add(acc);
			}
		}catch(SQLException ex){
			ex.printStackTrace();		//if no account found return null
			return null;
		}finally{
			try{
				rs.close();
				ps.close();
				con.close();
				System.out.println(accList.size() + " accounts found!");
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		return accList;
	}

}
