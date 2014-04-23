package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javabean.LoginDB;
import javabean.ProductBean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import model.*;

public class AddPersonServlet extends Servlet {


	public AddPersonServlet() {
		super();
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("username");
		String pass = request.getParameter("password");
		
		LoginDB logdb = new LoginDB();
		logdb.connectDB();
		session = request.getSession(true);
		int value = 0;
		try {
			value = logdb.login(name, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("value", String.valueOf(value));
		session.setAttribute("username", name);
		session.setAttribute("type", "add");
		if (value == 1) {
			List<ProductBean> list = this.getListOfProducts();
			request.setAttribute("products", list);
			em = factory.createEntityManager();

			em.getTransaction().begin();
			User dd = new User();
			dd.setUsername(name);
			dd.setPassword(pass);
			
			em.persist(dd);
			em.getTransaction().commit();
			em.close();
			session.setAttribute("type", "login");
			this.getServletContext().getRequestDispatcher("/confirmation.jsp")
					.forward(request, response);
		}
		else{
			session.setAttribute("username", null);
			this.getServletContext().getRequestDispatcher("/signup.jsp")
			.forward(request, response);
		}


	}

}
