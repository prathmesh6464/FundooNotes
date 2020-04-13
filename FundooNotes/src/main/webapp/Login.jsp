<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>
	<center>
		<h3>Login Page</h3>
		<form action="isValidUser" method="">
			<table>
				<tr>
					<td><br> <label for="userName">User Name &nbsp; :
							&nbsp; &nbsp;</label></td>
					<td><br> <input type="text" id="userName" name="userName"
						placeholder="Enter User Name" required></td>
				</tr>
				<tr>
					<td><br> <label for="password">Password &nbsp;
							&nbsp; &nbsp; : &nbsp; &nbsp;</label></td>
					<td><br> <input type="password" id="password"
						name="password" placeholder="Enter Password" required></td>
				</tr>
				<tr>
					<td></td>
					<td><br> <input type="submit" value="Login">
						&nbsp; &nbsp; <a
						href="http://localhost:8081/WebApplicationUserRegistration/UserRegistrationForm.jsp">
							<input type="button" value="Sign Up">
					</a>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>