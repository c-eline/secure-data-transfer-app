
//import necessary packages for XML parsing
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1,	//1 MB
		maxFileSize = 1024 * 1024 * 10,					// 10 MB
		maxRequestSize = 1024 * 1024 * 100 				//100 MB
)
/**
 * Servlet implementation class ParseXMLServlet
 */

public class ParseXMLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		try {
			 HttpSession session=request.getSession(); // get http session
			 
			 //initialize variables for use
			 Pattern pattern;
	         Matcher matcher;
	         boolean emailValid=false,phoneValid=false;
	         String email=null,phone=null;
	         
			 Part file = request.getPart("file"); //accept the file
			 InputStream filecontent = file.getInputStream(); //store file in inputstream
			
			 //begin parsing XML from stream
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(filecontent);
	         doc.getDocumentElement().normalize();
	        
	         
	         NodeList nList = doc.getElementsByTagName("employee"); // if more then one employee was in XML we can loop
	         for (int temp = 0; temp < nList.getLength(); temp++) 
	         {
	            Node nNode = nList.item(temp);
	            
	            
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               
	               
	                  email = eElement.getElementsByTagName("email").item(0).getTextContent(); //retrieves element from XML
	                  pattern = Pattern.compile("^(.+)@(.+)\\.{1}(.+)$"); //regEx for detecting @ and at least one .
	                  matcher = pattern.matcher(email); //compares the regEx to incoming string
	                  
	                  emailValid = matcher.matches(); //returns true if match/ false if not
	                  System.out.println("Valid Email: " + emailValid);
	                  
	                  phone = eElement.getElementsByTagName("phone").item(0).getTextContent(); //retrieves element from XML
	                  pattern = Pattern.compile("^\\d+$"); //regEx for detecting that there is only number
	                  matcher = pattern.matcher(phone); //compares the regEx to incoming String
	                  
	                  phoneValid = matcher.matches(); //returns true if match / false if not
	                  
	                  System.out.println("Valid Phone: " + phoneValid);
	                  
	               
	            }
	            
	         }
	         
	         if((emailValid) && (phoneValid)) //if email and phone are correct (true) then store variables and send to authentication
	         {
	        	session.setAttribute("email",email); 
	        	session.setAttribute("phone",phone); 
	        	RequestDispatcher dispatcher=request.getRequestDispatcher("/AuthServlet");
	 			dispatcher.forward(request, response);
	         }
	         else
	         {
	        	 if((!emailValid) || (!phoneValid)) //if either are not true, return to index.jsp and update message
	        	 {
	        		 request.setAttribute("message",
	                     "There was an error in the submitted XML <br> isEmailValid: " 
	                    		 + emailValid + "<br> isPhoneValid: "
	                    		 + phoneValid + "<br> Please Submit Correct XML...");
	        	 }
	        	 RequestDispatcher dispatcher=request.getRequestDispatcher("/index.jsp");
		 			dispatcher.forward(request, response);
	        	
	         }
	         
	        
			
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		
		 
	}

}
