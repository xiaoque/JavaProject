<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Log in</title>
<script language="javascript">
	function checkInfo(f) {
		if (f.username.value == "") {
			alert('please enter username!');
			f.username.focus();
			return false;
		}
		if (f.password.value == "") {
			alert('Please enter password!');
			f.password.focus();
			return false;
		}
		return true;
	}
</script>
</head>
<body>



	<br>
	<form method="post" action="Login" onsubmit='return checkInfo(this);'>
		Name: <input type="text" name="username" /> Password:<input
			type="password" name="password" /> <input type="Submit"
			value="Log in" />
	</form>


</body>
</html>