
//Customer Bean used to create customer bean objects & store customer info from customer.sql
public class CustomerBean implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int customerNumber;
	private String customerName;
	private String phone;
	
	public CustomerBean() {
		
	}
	
	//getters & setters
	public int getCustomerNumber() {
		return customerNumber;
	}
	
	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
