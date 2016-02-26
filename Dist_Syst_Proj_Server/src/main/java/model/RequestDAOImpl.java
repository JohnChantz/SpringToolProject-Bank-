package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class RequestDAOImpl implements RequestDAO{

	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource=dataSource;
	}
	
	@Override
	public ArrayList<Request> getAllRequests() {		//returns all available requests in database
		String query = "select * from requests";
		Request req = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Request> reqList = new ArrayList<Request>();
		try{
			con = dataSource.getConnection();
			ps=con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				req = new Request();
				req.setId(rs.getInt("id"));
				req.setCreditLimit(rs.getInt("credit_limit"));
				req.setPaymentSelection(rs.getString("payment_selection"));
				req.setEmpName(rs.getString("emp_name"));
				req.setEmpAddress(rs.getString("emp_address"));
				req.setSalary(rs.getInt("salary"));
				req.setAnnualSalary(rs.getInt("annual_salary"));
				req.setRate(rs.getString("rate"));
				req.setCustomerId(rs.getInt("customer_id"));
				req.setRequestStatus(rs.getString("request_status"));
				req.setDescription(rs.getString("description"));
				reqList.add(req);
			}
			System.err.println("Requests found!!!");
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
		return reqList;
}


	@Override
	public Request getRequestById(int id) {		//returns a request from the database where the id is matched
		String query = "select * from requests where id = ? ";
		Request req = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = dataSource.getConnection();
			ps=con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				req = new Request();
				req.setId(id);
				req.setCreditLimit(rs.getInt("credit_limit"));
				req.setPaymentSelection(rs.getString("payment_selection"));
				req.setEmpName(rs.getString("emp_name"));
				req.setEmpAddress(rs.getString("emp_address"));
				req.setSalary(rs.getInt("salary"));
				req.setAnnualSalary(rs.getInt("annual_salary"));
				req.setCustomerId(rs.getInt("customer_id"));
				req.setRequestStatus(rs.getString("request_status"));
				req.setRate(rs.getString("rate"));
				req.setDescription(rs.getString("description"));
				System.out.println("Request found:: " + req.getId());
			}
		}catch(SQLException ex){
			ex.printStackTrace();
			return null;		//if no request found return null
		}finally{
			try{
				rs.close();
				ps.close();
				con.close();
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		return req;
	}

	@Override
	public int addNewRequest(Request req) {		//add new request to database
		String query = "insert into requests(credit_limit,payment_selection,emp_name,emp_address,salary,annual_salary,rate,customer_id,description,created_by) values(?,?,?,?,?,?,?,?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		int result=0;
		try{
			con = dataSource.getConnection();
			ps=con.prepareStatement(query);
			ps.setInt(1,req.getCreditLimit());
			ps.setString(2,req.getPaymentSelection());
			ps.setString(3,req.getEmpName());
			ps.setString(4,req.getEmpAddress());
			ps.setInt(5,req.getSalary());
			ps.setInt(6,req.getAnnualSalary());
			ps.setString(7, req.getRate());
			ps.setInt(8, req.getCustomerId());
			ps.setString(9, req.getDescription());
			if(req.getCreatedBy() == null){
				ps.setString(10, "employee");
			}else{
				ps.setString(10, req.getCreatedBy());
			}
			result = ps.executeUpdate();
			if(result != 0){		//if query is successfull print message
				System.out.println("::New request added::");
			}else{
				System.err.println("::Adding new request FAILED::");
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
		return result;
	}

	@Override
	public int editRequestCreditLimit(int id,int creditLimit) {		//edit request's credit limit with matching id
		if(creditLimit == 0)
			return 0;
		String query = "update requests set credit_limit=? where id=?";
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		try{
			con = dataSource.getConnection();
			ps=con.prepareStatement(query);
			ps.setInt(1,creditLimit);
			ps.setInt(2,id);
			result = ps.executeUpdate();
			if(result != 0){		//if update in successfull print message for logging
				System.out.println("Updating request:: ID->" + id);
			}else{
				System.err.println("Updating request FAILED:: ID->" + id);
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
		return result;
	}

	@Override
	public int editRequestRate(int id,String rate) {		//edit request's rate with matching id
		if(rate.equals(" "))
			return 0;
		String query = "update requests set rate=? where id=?";
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		try{
			con = dataSource.getConnection();
			ps=con.prepareStatement(query);
			ps.setString(1,rate);
			ps.setInt(2,id);
			result = ps.executeUpdate();
			if(result != 0){		//if update successfull print message for logging
				System.out.println("Updating request:: ID->" + id);
			}else{
				System.err.println("Updating request FAILED:: ID->" + id);
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
		return result;
	}

	@Override
	public int editRequestStatus(int id, String status) {			//edit request's status with matching id
		if(status.equals(" "))
			return 0;
		String query = "update requests set request_status=? where id=?";
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		try{
			con = dataSource.getConnection();
			ps=con.prepareStatement(query);
			ps.setString(1,status);
			ps.setInt(2,id);
			result = ps.executeUpdate();
			if(result != 0){		//if update successfull print message for logging
				System.out.println("Updating request:: ID->" + id);
			}else{
				System.err.println("Updating request FAILED:: ID->" + id);
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
		return result;
	}

	@Override
	public boolean requestExistByCId(int customer_id) {			//check if a request with matching id already exists
		String query = "select * from requests where customer_id=?";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs;
		try{
			con = dataSource.getConnection();
			ps=con.prepareStatement(query);
			ps.setInt(1,customer_id);
			rs = ps.executeQuery();
			if(rs.next()){		//logging message
				System.out.println("Request exists:: ID->" + customer_id);
				return true;
			}else{
				System.err.println("Request does not exist:: ID->" + customer_id);
				return false;
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
		return false;
	}
	
	@Override
	public boolean requestExistByRId(int id) {			//check if a request with matching id already exists
		String query = "select * from requests where id=?";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs;
		try{
			con = dataSource.getConnection();
			ps=con.prepareStatement(query);
			ps.setInt(1,id);
			rs = ps.executeQuery();
			if(rs.next()){		//logging message
				System.out.println("Request exists:: ID->" + id);
				return true;
			}else{
				System.err.println("Request does not exist:: ID->" + id);
				return false;
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
		return false;
	}

	@Override
	public Request getRequestByCustomerId(int customer_id){		//get a request using customer's id 
		String query = "select * from requests where customer_id=?";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs;
		Request req = null;
		try{
			con = dataSource.getConnection();
			ps=con.prepareStatement(query);
			ps.setInt(1,customer_id);
			rs = ps.executeQuery();
			if(rs.next()){		//logging message
				req = new Request();
				req.setId(rs.getInt("id"));
				req.setCreditLimit(rs.getInt("credit_limit"));
				req.setPaymentSelection(rs.getString("payment_selection"));
				req.setEmpName(rs.getString("emp_name"));
				req.setEmpAddress(rs.getString("emp_address"));
				req.setSalary(rs.getInt("salary"));
				req.setAnnualSalary(rs.getInt("annual_salary"));
				req.setCustomerId(rs.getInt("customer_id"));
				req.setRequestStatus(rs.getString("request_status"));
				req.setRate(rs.getString("rate"));
				req.setDescription(rs.getString("description"));
				System.err.println("Request found:: customerID->" + customer_id);
			}else{
				System.err.println("Request does not exist:: customerID->" + customer_id);
				return null;
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
		return req;
	}

	@Override
	public int updateRequest(Request req) {		//update a request's fields
		String query = "update requests set credit_limit=?,payment_selection=?,emp_name=?,emp_address=?,salary=?,annual_salary=?,rate=?,description=? "
				+ "where id=?";
		Connection con = null;
		PreparedStatement ps = null;
		int result;
		try{
			con = dataSource.getConnection();
			ps=con.prepareStatement(query);
			ps.setInt(1,req.getCreditLimit());
			ps.setString(2,req.getPaymentSelection());
			ps.setString(3,req.getEmpName());
			ps.setString(4,req.getEmpAddress());
			ps.setInt(5,req.getSalary());
			ps.setInt(6,req.getAnnualSalary());
			ps.setString(7,req.getRate());
			ps.setString(8,req.getDescription());
			ps.setInt(9,req.getId());
			result = ps.executeUpdate();
			if(result != 0){		//if update successfull print message for logging
				System.out.println("Request Updated:: ID->" + req.getId());
				return 1;
			}else{
				System.err.println("Updating request FAILED:: ID->" + req.getId());
				return 0;
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
		return 0;
	}

	

	@Override
	public int editRequestDescription(int id, String description) {		//edit request's description
		if(description.equals(" "))
			return 0;
		String query = "update requests set description=? where id=?";
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		try{
			con = dataSource.getConnection();
			ps=con.prepareStatement(query);
			ps.setString(1,description);
			ps.setInt(2,id);
			result = ps.executeUpdate();
			if(result != 0){		//if update successfull print message for logging
				System.out.println("Updating request:: ID->" + id);
			}else{
				System.err.println("Updating request FAILED:: ID->" + id);
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
		return result;
	}

	@Override
	public ArrayList<Request> getPendingRequests() {		//get all requests with request_status=Pending for evaluation
		String query = "select * from requests where request_status='Pending'";
		Request req = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Request> reqList = new ArrayList<Request>();
		try{
			con = dataSource.getConnection();
			ps=con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				req = new Request();
				req.setId(rs.getInt("id"));
				req.setCreditLimit(rs.getInt("credit_limit"));
				req.setPaymentSelection(rs.getString("payment_selection"));
				req.setEmpName(rs.getString("emp_name"));
				req.setEmpAddress(rs.getString("emp_address"));
				req.setSalary(rs.getInt("salary"));
				req.setAnnualSalary(rs.getInt("annual_salary"));
				req.setRate(rs.getString("rate"));
				req.setCustomerId(rs.getInt("customer_id"));
				req.setRequestStatus(rs.getString("request_status"));
				req.setDescription(rs.getString("description"));
				reqList.add(req);
			}
			System.err.println("Requests found!!!");
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
		return reqList;
	}
	
	@Override
	public ArrayList<Request> getCustomerCreatedRequests() {		//get all requests with request_status=Pending for evaluation
		String query = "select * from requests where created_by='customer'";
		Request req = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Request> reqList = new ArrayList<Request>();
		try{
			con = dataSource.getConnection();
			ps=con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){
				req = new Request();
				req.setId(rs.getInt("id"));
				req.setCreditLimit(rs.getInt("credit_limit"));
				req.setPaymentSelection(rs.getString("payment_selection"));
				req.setEmpName(rs.getString("emp_name"));
				req.setEmpAddress(rs.getString("emp_address"));
				req.setSalary(rs.getInt("salary"));
				req.setAnnualSalary(rs.getInt("annual_salary"));
				req.setRate(rs.getString("rate"));
				req.setCustomerId(rs.getInt("customer_id"));
				req.setRequestStatus(rs.getString("request_status"));
				req.setDescription(rs.getString("description"));
				reqList.add(req);
			}
			System.err.println("Requests found!!!");
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
		return reqList;
	}
	
}
