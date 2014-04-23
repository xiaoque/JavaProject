package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javabean.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class LoginServlet extends Servlet {

	public LoginServlet() {
		super();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("username");
		String pass = request.getParameter("password");

		LoginDB logdb = new LoginDB();
		logdb.connectDB();
		session = request.getSession(true);
		int password = 0;
		try {
			password = logdb.login(name, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("value", String.valueOf(password));
		session.setAttribute("username", name);
		session.setAttribute("type", "login");
		if (password == 3) {
			List<ProductBean> list = this.getListOfProducts();
			request.setAttribute("products", list);
			this.getServletContext().getRequestDispatcher("/confirmation.jsp")
					.forward(request, response);
		} else {
			session.setAttribute("username", null);
			if (password == 2) {
				this.getServletContext().getRequestDispatcher("/login.jsp")
						.forward(request, response);
			} else {
				if (password == 1)
					this.getServletContext()
							.getRequestDispatcher("/signup.jsp")
							.forward(request, response);
			}
		}

	}
}
