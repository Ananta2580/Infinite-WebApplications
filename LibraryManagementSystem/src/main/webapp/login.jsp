<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="login.jsp" method="post">
		<center>
			<h2>Login Account</h2>
			User Name :
			<input type="text" name="username" required><br><br>

			Password :
			<input type="password" name="password" required><br><br>

			<input type="submit" value="Login">
		</center>
	</form>
	
	<%
	if (request.getParameter("username")!=null && 
		request.getParameter("password")!=null) {
		String user = request.getParameter("username");
		String pwd = request.getParameter("password");
%>
	<jsp:useBean id="libUsers" class="com.java.lib.model.LibUsers" />
	<jsp:setProperty property="*" name="libUsers"/>
	<jsp:useBean id="libraryDao" class="com.java.lib.dao.LibraryDaoImpl" />
	
<%
	int count = libraryDao.login(libUsers);
	if (count==1) {
		session.setAttribute("user", request.getParameter("username"));
%>
	<jsp:forward page="Menu.jsp" />
<%
	} else {
		out.println("Invalid Credentials...");
	}
	}
%>
	
</body>
</html>