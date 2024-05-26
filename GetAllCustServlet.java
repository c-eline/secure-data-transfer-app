

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.sql.*;

public class GetAllCustServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/empdatabase?useSSL=false";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "x-^*2587";
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	try {	
		Class.forName("com.mysql.cj.jdbc.Driver");

			
		//Database connection & execute SQL query
		
		
		Connection con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD); 	// connection to SQL

		java.sql.Statement stmt = con.createStatement();
		
		String query = "SELECT customerNumber,customerName,phone FROM customers;";
		ResultSet rs = stmt.executeQuery(query);
		
		if (!rs.isBeforeFirst()) {
		    // if no customers are found update message and redirect to index.jsp
			request.setAttribute("message", "No Customers Found, Please Try Again");
			RequestDispatcher dispatcher=request.getRequestDispatcher("/index.jsp");

			dispatcher.forward(request, response);
					
		}
		else
		{
			//create an array list of customers and populate
			ArrayList<CustomerBean> cbList = new ArrayList<CustomerBean>();
			
			while(rs.next())
			{
				CustomerBean cb = new CustomerBean();
				cb.setCustomerName(rs.getString("customerName"));
				cb.setCustomerNumber(rs.getInt("customerNumber"));
				cb.setPhone(rs.getString("phone"));
				cbList.add(cb);
				
			}
			request.setAttribute("allCustomers", cbList); //set a request attribute and dispatch
			RequestDispatcher dispatcher=request.getRequestDispatcher("/allCustomers.jsp");
			
			dispatcher.forward(request, response);

			
		}

		con.close(); //close sql connection

		}
		catch (Exception e){ 
			e.printStackTrace();
		}
	}

}