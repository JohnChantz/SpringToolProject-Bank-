package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class CustomerDAOImpl implements CustomerDAO{
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource datasource){
		this.dataSource = datasource;
	}

	@Override
	public Customer getCustomerByAdt(String adt) {		//get customer from db with matching adt
		String query = "select * from customers where adt = ? ";
		Customer cust = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = dataSource.getConnection();
			ps=con.prepareStatement(query);
			ps.setString(1,adt);
			rs = ps.executeQuery();
			if(rs.next()){
				cust = new Customer();
				cust.setId(rs.getInt("id"));
				cust.setFname(rs.getString("fname"));
				cust.setLname(rs.getString("lname"));
				cust.setAfm(rs.getInt("afm"));
				cust.setAdt(adt);
				cust.setTel(rs.getString("tel"));
				cust.setPaymentAccount(rs.getInt("paymentAccount"));
				System.out.println("Customer found::ID--> " + cust.getId());		//logging message
			}
		}catch(SQLException ex){
			ex.printStackTrace();		//if no user found return null
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
		return cust;
	}

	@Override
	public void newCustomer(Customer customer) {		//add new customer
		String query = "insert into employees (fname,lname,afm,adt,tel,paymentAccount) "
				+ "values(?,?,?,?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		try{
			con = dataSource.getConnection();
			ps=con.prepareStatement(query);
			ps.setString(1, customer.getFname());
			ps.setString(2, customer.getLname());
			ps.setInt(3, customer.getAfm());
			ps.setString(4, customer.getAdt());
			ps.setString(5, customer.getTel());
			ps.setInt(6, customer.getPaymentAccount());
			int psResult = ps.executeUpdate();
			if(psResult != 0){		//logging message
				System.out.println("New employee added, id= " + customer.getId());
			}else{
				System.err.println("Employee insert FAILED, id= " + customer.getId());
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
	public Customer getCustomerById(int id) {		//get a customer by his id
		String query = "select id,fname,lname,afm,adt,tel,paymentAccount from customers where id = ? ";
		Customer cust = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = dataSource.getConnection();
			ps=con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				cust = new Customer();
				cust.setId(id);
				cust.setFname(rs.getString("fname"));
				cust.setLname(rs.getString("lname"));
				cust.setAfm(rs.getInt("afm"));
				cust.setAdt(rs.getString("adt"));
				cust.setTel(rs.getString("tel"));
				cust.setPaymentAccount(rs.getInt("paymentAccount"));
				System.out.println("Employee found:: " + cust.getId());		//logging message
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
		return cust;
	}

	@Override
	public Customer getCustomerByAfm(int afm) {
		String query = "select id,fname,lname,afm,adt,tel,paymentAccount from customers where afm = ? ";
		Customer cust = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = dataSource.getConnection();
			ps=con.prepareStatement(query);
			ps.setInt(1, afm);
			rs = ps.executeQuery();
			if(rs.next()){
				cust = new Customer();
				cust.setId(rs.getInt("id"));
				cust.setFname(rs.getString("fname"));
				cust.setLname(rs.getString("lname"));
				cust.setAfm(afm);
				cust.setAdt(rs.getString("adt"));
				cust.setTel(rs.getString("tel"));
				cust.setPaymentAccount(rs.getInt("paymentAccount"));
				System.out.println("Employee found:: " + cust.getId());		//logging message
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
		return cust;
	}

}
