package compShop.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.HibernateException;

import compShop.dao.CustomerDAO;
import compShop.dao.impl.CustomerDAOImpl;
import compShop.model.Customer;
import compShop.service.CustomerService;
import compShop.util.HibernateUtil;

public class CustomerServiceImpl implements CustomerService {

	CustomerDAO customerDAO = new CustomerDAOImpl();
	
	public void saveNewCustomer(Customer customer) {
		try {
			HibernateUtil.beginTransaction();
			customerDAO.save(customer);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
	        System.out.println("Saving error.");
	        HibernateUtil.rollbackTransaction();
	    }
		
	}

	public void updateCustomer(Customer customer) {
		try {
			HibernateUtil.beginTransaction();
			customerDAO.update(customer);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
	        System.out.println("Updating error.");
	        HibernateUtil.rollbackTransaction();
	    }
	}

	public void deleteCustomer(Customer customer) {
		try {
			HibernateUtil.beginTransaction();
			customerDAO.delete(customer);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
	        System.out.println("Deleting error.");
	        HibernateUtil.rollbackTransaction();
	    }
		
	}

	public List<Customer> getAllCustomer() {
		List<Customer> allCustomers = null;		
		try {
			HibernateUtil.beginTransaction();
			allCustomers = customerDAO.findAll(Customer.class);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
	        System.out.println("Getting error.");
	        HibernateUtil.rollbackTransaction();
	    }
		
		return allCustomers;
	}

	public Customer getOneByLogin(String login) {
		Customer customer = null;		
		try {
			HibernateUtil.beginTransaction();
			customer = customerDAO.findByID(Customer.class, login);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
	        System.out.println("Getting error.");
	        HibernateUtil.rollbackTransaction();
	    }
		
		return customer;
	}

	public BigDecimal countFullSumOfBoughtGoods(String login) {
		BigDecimal sumPrice = new BigDecimal("0");		
		try {
			HibernateUtil.beginTransaction();
			sumPrice = customerDAO.countFullSumOfBoughtGoods(login);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
	        System.out.println("Getting error.");
	        HibernateUtil.rollbackTransaction();
	    }		
		return sumPrice;
	}

}
