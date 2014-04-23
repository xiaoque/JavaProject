package controller;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javabean.*;
import model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;


public class Servlet extends HttpServlet{
	private static final String PERSISTENCE_UNIT_NAME = "manager1";
	protected EntityManagerFactory factory;
	protected EntityManager em;
	protected HttpSession session;

	public Servlet() {
		super();
		factory = Persistence
				.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}

	public EntityManagerFactory getFactory() {
		return factory;
	}

	public void setFactory(EntityManagerFactory factory) {
		this.factory = factory;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public  List<ProductBean> getListOfProducts() {
		em = factory.createEntityManager();
		List liste = em.createQuery( "SELECT p FROM Product p" ).getResultList();
		List<ProductBean> products = new ArrayList<ProductBean>();
		Iterator iterator = liste.iterator();
		Product temp;
		ProductBean product;
		while(iterator.hasNext()){
			temp = (Product)iterator.next();
			product = new ProductBean(temp.getProductid(), temp.getName(), temp.getPrice());
			products.add(product);
		}
		em.close();
		return products;
	}
	
	public boolean productExist(String name){
		em = factory.createEntityManager();
		List liste = em.createQuery("SELECT c FROM Product c WHERE c.productname ='"+name+"'").getResultList();
		Iterator iterator = liste.iterator();
		if(iterator.hasNext()){
			em.close();
			return true;
		}
		em.close();
		return false;
	}
	
	
	public List<OrderBean> getListOfOrder(String name){
		em = factory.createEntityManager();
		List liste = em.createQuery( "SELECT p FROM Order p" ).getResultList();
		List<OrderBean> orders = new ArrayList<OrderBean>();
		Iterator iterator = liste.iterator();
		Order ordertemp;
		OrderBean order;
		while(iterator.hasNext()){
			ordertemp = (Order)iterator.next();
			if(ordertemp.getUsername().equals(name)){
			Product temp= ordertemp.getProduct();
			order = new OrderBean(temp.getPrice(), temp.getName(), temp.getDescription(),name,ordertemp.getOrderid());
			orders.add(order);
			}
		}
		em.close();
		return orders;
		}

}
