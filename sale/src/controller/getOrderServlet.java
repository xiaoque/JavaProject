package controller;

import java.io.IOException;
import java.util.List;

import javabean.OrderBean;
import javabean.ProductBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Order;
import model.Product;

public class getOrderServlet extends Servlet {

	public getOrderServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = null;
		String type = request.getParameter("order");
		if (type.equals("order")) {
			session = request.getSession();
			name = (String) session.getAttribute("username");
			int id = Integer.parseInt(request.getParameter("productID"));

			em = factory.createEntityManager();
			em.getTransaction().begin();
			Product dd = em.find(Product.class, id);
			Order order = new Order(name);
			order.setProduct(dd);
			em.persist(order);
			em.getTransaction().commit();
			em.close();
			List<OrderBean> orderlist = this.getListOfOrder(name);
			request.setAttribute("orders", orderlist);
			this.getServletContext().getRequestDispatcher("/showorder.jsp")
					.forward(request, response);
		} else {
			List<ProductBean> list = this.getListOfProducts();
			request.setAttribute("products", list);
			this.getServletContext().getRequestDispatcher("/confirmation.jsp")
					.forward(request, response);
		}

	}

}
