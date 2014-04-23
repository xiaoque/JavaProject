<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add products</title>
<script>
	function checkInfo(f) {
		if (f.productname.value == "") {
			alert('please enter productname!');
			f.productname.focus();
			return false;
		}
		if (f.price.value == "") {
			alert('Please enter price!');
			f.price.focus();
			return false;
		}
		if(isNaN(f.price.value)){
			alert('Please enter float');
			f.price.focus();
			return false;
		}
		return true;
	}
</script>
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
	<a href="confirmation.jsp">Make an order</a>
	<%
		}
				else{ %>
	<a href="login.jsp">Log in</a>
	<a href="signup.jsp">Sign up</a>
	<%	}
			}
			else{
				%>
	<a href="login.jsp">Log in</a>
	<a href="signup.jsp">Sign up</a>
	<%	
			}
		}
		else{
			%>
	<a href="login.jsp">Log in</a>
	<a href="signup.jsp">Sign up</a>
	<%	
		}
	%>
	<form action="addProduct" method="post"
		onsubmit='return checkInfo(this);'>
		<p>
			Name: <input type="text" name="productname" />
		</p>
		<p>
			Price: <input type="text" name="price" />
		</p>
		<p>
			Description:
			<textarea name="descrip" style="width: 360px; height: 130px;"></textarea>
		</p>
		<input type="submit" value="Add">
	</form>
</body>