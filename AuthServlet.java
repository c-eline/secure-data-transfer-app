

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.io.IOException;


public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	//private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/empdatabase?useSSL=false";
	
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/empdatabase?useSSL=false";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "x-^*2587";
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

try {
			/* Receive file uploaded to Servlet via HTML form */
			HttpSession session = request.getSession();
			String email =((String) session.getAttribute("email"));
			String phone =((String) session.getAttribute("phone")).trim();

			
			Class.forName("com.mysql.cj.jdbc.Driver");	//Java driver
			
						//Connection con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);		//Create connection
				
						//java.sql.Statement stmt =  con.createStatement();
				
						//ResultSet rs = stmt.executeQuery("SELECT empId FROM emp where email='"+email+ "' and phone='"+pswd+"'");
						
			Connection con = null;
			java.sql.Statement stmt = null;

			con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD); 	//connect to the database
						
			stmt = con.createStatement();
			String query = "SELECT * FROM emp WHERE email = '" + email + "' AND phone = '"+ phone + "'"; //query for the email/phone number combo
			ResultSet rs = stmt.executeQuery(query);
						
					
		
			
            if (!rs.isBeforeFirst()) {				    
            	// if no user is found, update session with message and dispatch back to start page
            	request.setAttribute("message",
                     "Authentication Failed for User: " + email + "<br> Please Enter Valid Credentials and Try again");
			RequestDispatcher dispatcher=request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);

	
            } 
            else 
            {
            	//if authenticated , dispatch the request to get all customers details servlet, the session is authenticated
				RequestDispatcher dispatcher=request.getRequestDispatcher("/GetAllCustServlet");
				dispatcher.forward(request, response);
            	

            }
            
            con.close();	//close sql db connection
	 
			
			
		} catch (Exception e) {
				e.printStackTrace();
			}
	}

}
