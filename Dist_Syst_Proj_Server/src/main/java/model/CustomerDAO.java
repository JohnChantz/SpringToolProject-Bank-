package model;

public interface CustomerDAO {

	public void newCustomer(Customer customer);
	public Customer getCustomerByAdt(String adt);
	public Customer getCustomerById(int id);
	public Customer getCustomerByAfm(int afm);
}
