<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<title>User Registration</title>
</head>
<body>
	<center>
		<h3>User Registration Form</h3>
		<!-- TAKING DATA FROM USER -->
		<form action="registerUser" method="post">
			<table>
				<tr>
					<td><br> <label for="name">First Name &nbsp; :
							&nbsp; &nbsp;</label></td>
					<td><br> <input type="text" id="name" name="nameOfUser"
						placeholder="Enter First Name" required></td>
				</tr>
				<tr>
					<td><br> <label for="lastName">Last Name &nbsp; :
							&nbsp; &nbsp;</label></td>
					<td><br> <input type="text" id="lastName"
						name="lastNameOfUser" placeholder="Enter Last Name" required></td>
				</tr>
				<tr>
					<td><br> <label for="userName">User Name : &nbsp;
							&nbsp;</label></td>
					<td><br> <input type="text" id="userName" name="userName"
						placeholder="Enter User Name" required></td>
				</tr>
				<tr>
					<td><br> <label for="password">Password &nbsp;
							&nbsp; : &nbsp; &nbsp;</label></td>
					<td><br> <input type="password" id="password"
						name="password" placeholder="Enter Password" required></td>
				</tr>
				<tr>
					<td></td>
					<td><br> <input type="submit" value="Register"></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>