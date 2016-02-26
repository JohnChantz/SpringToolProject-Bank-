package model;

import java.util.ArrayList;

public interface RequestDAO {
	
	public ArrayList<Request>  getAllRequests();
	public Request getRequestById(int id);
	public int addNewRequest(Request req);
	public int editRequestCreditLimit(int id,int creditLimit);
	public int editRequestRate(int id,String rate);
	public int editRequestStatus(int id,String status);
	public int editRequestDescription(int id,String description);
	public boolean requestExistByCId(int customer_id);
	public boolean requestExistByRId(int customer_id);
	public Request getRequestByCustomerId(int customer_id);
	public int updateRequest(Request req);
	public ArrayList<Request> getPendingRequests();
	public ArrayList<Request> getCustomerCreatedRequests();

}
