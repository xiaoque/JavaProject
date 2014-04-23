<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="javabean.ProductBean" import="java.util.List"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order</title>

<script>
  function chk(input)
  {
    for(var i=0;i<document.form.productID.length;i++)
    {
      document.form.productID[i].checked = false;
    }
    input.checked = true;
    return true;
  }
  
  function changePage(){
	  alert("Please log in");
      top.location='welcome.jsp';
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
	<a href="addProducts.jsp">add a product</a>
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
	<form name="form" action="getOrder" method="post">

		<table>
			<tr>
				<th>Identifier</th>
				<th>Name</th>
				<th>Price</th>
				<th>Select</th>
			</tr>

			<%
				List<ProductBean> products = (List<ProductBean>) request.getAttribute("products");
				if(!products.isEmpty()&&products.size()!=0){
				ProductBean product;
				for (int i = 0; i < products.size(); i++) {
					product = products.get(i);
			%>

			<tr>
				<td><%=product.getId()%></td>
				<td><%=product.getName() %></td>
				<td><%=product.getPrice()%></td>
				<td><input type="checkbox" name="productID"
					value="<%=product.getId()%>" onclick="return chk(this);"></td>
			</tr>

			<%
				}
				}
			%>
		</table>

		<input type="submit" name="order" value="order"> <input
			type="submit" name="order" value="cancel">

	</form>
</body>
</html>