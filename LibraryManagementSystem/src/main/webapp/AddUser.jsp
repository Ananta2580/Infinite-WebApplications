<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Account</title>
</head>
<body>
	<form action="AddUser.jsp" method="post">
		<center>
			<h2>Create Account</h2>
			User Name :
			<input type="text" name="username" required><br><br>

			Password :
			<input type="password" name="password" required><br><br>

			Re-Type Password :
			<input type="password" name="retypePassword" required><br><br>

			<input type="submit" value="Create Account">
		</center>
	</form>

	<%
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String retypePassword = request.getParameter("retypePassword");

		if(userName != null && password != null && retypePassword != null){
			if(password.equals(retypePassword)){
	%>
				<jsp:useBean id="libuserBean" class="com.java.lib.model.LibUsers" />
				<jsp:setProperty property="*" name="libuserBean"/>

				<jsp:useBean id="libuserDao" class="com.java.lib.dao.LibraryDaoImpl" />
				<p style="color:green;">
					<%= libuserDao.createUser(libuserBean) %>
				</p>
	<%
			} else {
	%>
				<p style="color:red; text-align: center;">Passwords do not match. Please try again.</p>
	<%
			}
		}
	%>
</body>
</html>
