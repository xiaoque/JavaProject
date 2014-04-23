<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="javabean.OrderBean" import="java.util.List"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show-Order</title>


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
	<a href="addProducts.jsp">Add a product</a>
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
		else{ %>
	<a href="login.jsp">Log in</a>
	<a href="signup.jsp">Sign up</a>
	<% } %>

	<table>
		<tr>
			<th>Identifier</th>
			<th>User_name</th>
			<th>Product Name</th>
			<th>Price</th>
			<th>Description</th>
		</tr>

		<%
				List<OrderBean> orders = (List<OrderBean>) request.getAttribute("orders");
				if(!orders.isEmpty()&&orders.size()!=0){
					OrderBean order;
				for (int i = 0; i < orders.size(); i++) {
					order = orders.get(i);
			%>

		<tr>
			<td><%=order.getId()%></td>
			<td><%=order.getUsername() %></td>
			<td><%=order.getName() %></td>
			<td><%=order.getPrice()%></td>
			<td><%=order.getDescription() %></td>
		</tr>

		<%
				}
				}
			%>
	</table>


</body>
</html>