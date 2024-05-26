<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Details</title>
</head>
	<!-- styling for table -->
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
	
	        h1, h2, th, td {
	            color: #9b59b6; /* purple */
	        }
	        
	</style>
<body>
	<!-- table to display -->
	<h1>Customer Details</h1>
	<table border="1">
	<tr >
		<th>Customer Number</th>
		<th>Customer Name</th>
		<th>Phone</th>
		
	</tr>


	<tr>
		<td>${customer.customerNumber}</td>
		<td>${customer.customerName}</td>
		<td>${customer.phone}</td>

		
	</tr>

</table>
<br>
<button onclick="window.location.href='/Assignment4/index.jsp';">Search Again?</button>
</body>
</html>