<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>

	<!-- form for XML file upload -->
	<form method="post" action="/Assignment4/ParseXMLServlet" enctype="multipart/form-data">
		<h1>Upload XML File:</h1><br>
		<input type="file" name="file"/> 
		<input type="submit" value="Upload File"/>
	</form>
	
	<br><h2>${message}</h2>
	
	
</body>
</html>