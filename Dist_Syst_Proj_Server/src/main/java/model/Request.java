package model;

public class Request {

	private int id;
	private int creditLimit;
	private String paymentSelection;
	private String empName;
	private String empAddress;
	private int salary;
	private int annualSalary;
	private String rate;
	private int customerId;
	private String requestStatus;
	private String description;
	private String createdBy;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(int credit_limit) {
		this.creditLimit = credit_limit;
	}
	public String getPaymentSelection() {
		return paymentSelection;
	}
	public void setPaymentSelection(String payment_selection) {
		this.paymentSelection = payment_selection;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String emp_name) {
		this.empName = emp_name;
	}
	public String getEmpAddress() {
		return empAddress;
	}
	public void setEmpAddress(String emp_address) {
		this.empAddress = emp_address;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getAnnualSalary() {
		return annualSalary;
	}
	public void setAnnualSalary(int annual_salary) {
		this.annualSalary = annual_salary;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customer_id) {
		this.customerId = customer_id;
	}
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
}
