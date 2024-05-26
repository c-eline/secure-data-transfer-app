

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GetOneCustServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/empdatabase?useSSL=false";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "x-^*2587";
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			String customerNumber = request.getParameter("custid"); //parse the customerNumber for the incoming url string
			//Database connection w/ customers table & execute SQL query w/ a selected customer number
			Class.forName("com.mysql.cj.jdbc.Driver");			
			
			Connection con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

			java.sql.Statement stmt = con.createStatement();
			String query = "SELECT * FROM customers WHERE customerNumber = '" + customerNumber + "';"; //get all customers with that customer number, should only be one
			ResultSet rs = stmt.executeQuery(query);
	        
			if (!rs.isBeforeFirst()) {
			    // handle empty set: if no customer is found update message and redirect back to index.jsp
				request.setAttribute("message", "No Customer Found MAYDAY!!! Please Try Again");
				RequestDispatcher dispatcher=request.getRequestDispatcher("/index.jsp");

				dispatcher.forward(request, response);
						
			} else {
				CustomerBean cb = new CustomerBean(); //create a customer bean if a result is found and populate
				while(rs.next())
				{
					
					cb.setCustomerName(rs.getString("customerName"));
					cb.setCustomerNumber(rs.getInt("customerNumber"));
					cb.setPhone(rs.getString("phone"));
					
					
				}
				request.setAttribute("customer", cb); //set a request attribute and send to customerDetail
				RequestDispatcher dispatcher=request.getRequestDispatcher("/customerDetail.jsp");
				
				dispatcher.forward(request, response);
				
			} 
			
			con.close(); //close sql
		}
			catch (Exception e) {
				e.printStackTrace();
		}
		
	}

	
}
