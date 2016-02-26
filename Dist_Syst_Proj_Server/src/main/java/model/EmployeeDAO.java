package model;

public interface EmployeeDAO {

	public void newEmployee(Employee employee);
	public Employee getEmployeeById(int id);
	public void deleteEmployee(int id);
	public Employee getEmployeeByUsername(String username);
	
}
