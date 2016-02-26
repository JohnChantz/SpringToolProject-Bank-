package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

public class EmployeeDAOImpl implements EmployeeDAO{
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource datasource){
		this.dataSource = datasource;
	}

	@Override
	public void newEmployee(Employee employee) {		//add new employee to database
		String query = "insert into employees (fname,lname,tel,acc_username,acc_password,acc_role) "
				+ "values(?,?,?,?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		try{
			con = dataSource.getConnection();
			ps=con.prepareStatement(query);
			ps.setString(1, employee.getFname());
			ps.setString(2, employee.getLname());
			ps.setString(3, employee.getTel());
			ps.setString(4, employee.getAcc_username());
			ps.setString(5, employee.getAcc_password());
			ps.setString(6, employee.getAcc_role());
			ps.setInt(8, employee.getDept_working());
			int psResult = ps.executeUpdate();
			if(psResult != 0){		//logging message
				System.out.println("New employee added, id= " + employee.getId());
			}else{
				System.err.println("Employee insert FAILED, id= " + employee.getId());
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
	public Employee getEmployeeById(int id) {		//get employee by id
		String query = "select id,fname,lname,tel,acc_username,acc_password,acc_role from employees where id = ? ";
		Employee emp = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = dataSource.getConnection();
			ps=con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				emp = new Employee();
				emp.setId(id);
				emp.setFname(rs.getString("fname"));
				emp.setLname(rs.getString("lname"));
				emp.setTel(rs.getString("tel"));
				emp.setAcc_username(rs.getString("acc_username"));
				emp.setAcc_password(rs.getString("acc_password"));
				emp.setAcc_role(rs.getString("acc_role"));
				emp.setDept_working(rs.getInt("dept_working"));
				System.out.println("Employee found:: " + emp);
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
		return emp;
	}

	@Override
	public void deleteEmployee(int id) {		//deletes an employee from database
		String query = "delete from employees where id=?";
		int result;
		Connection con = null;
		PreparedStatement ps = null;
		try{
			con = dataSource.getConnection();
			ps=con.prepareStatement(query);
			ps.setInt(1, id);
			result = ps.executeUpdate();
			if(result != 0){
				System.out.println("Employee deleted:: ID-> " + id);
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
	public Employee getEmployeeByUsername(String username) {	//gets an employee from each username
		String query = "select id,fname,lname,tel,acc_username,acc_password,acc_role from employees where acc_username = ? ";
		Employee emp = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = dataSource.getConnection();
			ps=con.prepareStatement(query);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if(rs.next()){
				emp = new Employee();
				emp.setId(Integer.parseInt(rs.getString("id")));
				emp.setFname(rs.getString("fname"));
				emp.setLname(rs.getString("lname"));
				emp.setTel(rs.getString("tel"));
				emp.setAcc_username(username);
				emp.setAcc_password(rs.getString("acc_password"));
				emp.setAcc_role(rs.getString("acc_role"));
				System.err.println("Employee found:: " + emp);
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
				System.err.println("SQL Exception thrown!!! Line 127");
			}
		}
		return emp;
	}
	
	

}
