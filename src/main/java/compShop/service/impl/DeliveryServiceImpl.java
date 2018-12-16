package compShop.service.impl;

import java.util.List;

import org.hibernate.HibernateException;

import compShop.dao.DeliveryDAO;
import compShop.dao.impl.DeliveryDAOImpl;
import compShop.model.Delivery;
import compShop.service.DeliveryService;
import compShop.util.HibernateUtil;

public class DeliveryServiceImpl implements DeliveryService{

	DeliveryDAO deliveryDAO = new DeliveryDAOImpl();
	
	public void saveNewDelivery(Delivery delivery) {
		
		try {
			HibernateUtil.beginTransaction();
			deliveryDAO.save(delivery);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Saving error.");
	            HibernateUtil.rollbackTransaction();
	        }
	}

	public void deleteDelivery(Delivery delivery) {
		
		try {
			HibernateUtil.beginTransaction();
			deliveryDAO.delete(delivery);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Deleting error.");
	            HibernateUtil.rollbackTransaction();
	        }
	}

	public List<Delivery> getAllDelivery() {
		List<Delivery> deliveryList = null;
		try {
			HibernateUtil.beginTransaction();
			deliveryList = deliveryDAO.findAll(Delivery.class);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Deleting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		
		return deliveryList;
	}

}
