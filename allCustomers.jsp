<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>	<!-- import list package -->
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>All Customers</title>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<!-- styling for table of customers -->
	<style>
	        body {
	            font-family: Arial, sans-serif;
	            margin: 0;
	            background-color: #f6e5ff; /* light purple */
	        }
			
			table {
	            width: 100%;
	            border-collapse: collapse;
	       	}
	
	        h2, th, td {
	            color: #9b59b6; /* purple */
	        }
	        
	</style>
</head>
<body>
	<h2>All Customers</h2>
 
	 <table border="1">

	<tr>
	        <!-- table headings -->
		<th>Customer Number</th>
		<th>Customer Name</th>
		<th>Phone</th>
		<th>More Details</th>
	</tr>

<c:forEach var="customer" items="${allCustomers}">
	<tr>
		<td>${customer.customerNumber}</td>
		<td>${customer.customerName}</td>
		<td>${customer.phone}</td>
		<td><button onclick="window.location.href='/Assignment4/GetOneCustServlet?custid=${customer.customerNumber}';">More Details</button></td>
		
	</tr>
</c:forEach>
</table>
</body>
</html>