<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Sign up</title>
<script language="javascript">
	function checkInfo(f) {
		if (f.username.value == "") {
			alert('please enter username!');
			f.username.focus();
			return false;
		}
		if (f.password.value == "" || f.password1.value == "") {
			alert('Please enter password!');
			f.password.focus();
			return false;
		}
		if (f.password.value != f.password1.value) {
			alert("Please confirm your password");
			f.password.focus();
			return false;
		}
		return true;
	}
</script>
</head>
<body>

	<br>
	<%
		HttpSession ss = request.getSession(true);
		if (ss != null) {
			String type = (String) ss.getAttribute("type");
			if (type != null && type.equals("add")) {
				if (ss.getAttribute("value") != "1") {
	%>
	<script type="text/javascript">
		alert("username already exist");
	</script>
	<%
		}
			}
		}
	%>
	<form action="addAPerson" method="post"
		onsubmit='return checkInfo(this); '>
		Name: <input type="text" name="username"><br /> Password: <input
			type="password" name="password"><br /> Confirm Password:<input
			type="password" name="password1"><br /> <input type="submit"
			value="Sign up">
	</form>


</body>
</html>