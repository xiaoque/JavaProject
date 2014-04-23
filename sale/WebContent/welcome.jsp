<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>welcome</title>
</head>
<body>
	<%
		HttpSession sss = request.getSession(true);
		if (sss != null) {
			String type = (String) sss.getAttribute("type");
			if (type != null && type.equals("login")) {
				if (sss.getAttribute("value") != "3") {
	%>
	<a href="logout.jsp">Log out</a>
	<br />
	<a href="addProducts.jsp">add a product</a>
	<%
		}
				else{ %>
	<a href="login.jsp">Log in</a>
	<br />
	<a href="signup.jsp">Sign up</a>
	<br />
	<%		}
			}
			else{
				%>
	<a href="login.jsp">Log in</a>
	<br />
	<a href="signup.jsp">Sign up</a>
	<br />
	<%	
			}
		}
		else{
			%>
	<a href="login.jsp">Log in</a>
	<br />
	<a href="signup.jsp">Sign up</a>
	<br />
	<%	
		}
	%>

</body>
</html>