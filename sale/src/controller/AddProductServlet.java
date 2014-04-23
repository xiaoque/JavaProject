package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javabean.LoginDB;
import javabean.ProductBean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Order;
import model.Product;

public class AddProductServlet extends Servlet {

	public AddProductServlet() {
		super();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		String name = request.getParameter("productname");
		float price = Float.parseFloat(request.getParameter("price"));
		String des = request.getParameter("descrip");
		String us = (String) request.getSession().getAttribute("username");
		
		LoginDB log = new LoginDB();
		log.connectDB();
		try {
			if(log.ProductExist(name)){
				this.getServletContext().getRequestDispatcher("/addProducts.jsp")
				.forward(request, response);
			}
			else{
				em = factory.createEntityManager();
				em.getTransaction().begin();
				Product dd = new Product();
				dd.setName(name);
				dd.setPrice(price);
				dd.setDescription(des);
				em.persist(dd);
				em.getTransaction().commit();
				em.close();
				List<ProductBean> list = this.getListOfProducts();
				request.setAttribute("products", list);
				this.getServletContext().getRequestDispatcher("/confirmation.jsp")
						.forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
