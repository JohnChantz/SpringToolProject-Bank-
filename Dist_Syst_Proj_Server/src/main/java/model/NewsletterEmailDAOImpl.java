package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

public class NewsletterEmailDAOImpl implements NewsletterEmailDAO{
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource=dataSource;
	}

	@Override
	public int addEmailToList(String email) {		//gets a String as an input (email) and stores it in newsletter email database
		String query = "insert into newsletter_emails (email_address) values(?)";
		Connection con = null;
		PreparedStatement ps = null;
		int result=0;
		try{
			con = dataSource.getConnection();
			ps=con.prepareStatement(query);
			ps.setString(1,email);
			result = ps.executeUpdate();
			if(result != 0){		//if query is successfull print message
				System.out.println("::New email added::");
			}else{
				System.err.println("::Adding new email FAILED::");
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

}
