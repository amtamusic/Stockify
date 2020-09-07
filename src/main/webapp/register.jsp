<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
</head>
<body>
<form action="/register/submit" method="POST"> 
	<table>
		<tr>
			<td>Email:</td>
			<td><input type="text" name="email" value=""></td>
		</tr>
		<tr>
			<td>User:</td>
			<td><input type="text" name="username" value=""></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><input type="password" name="password"></td>
		</tr>
		<tr>
			<td><input type="submit" name="submit" value="register"></td>
		</tr>
	</table>
</form>
</body>
</html>