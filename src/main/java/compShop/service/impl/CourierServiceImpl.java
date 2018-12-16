package compShop.service.impl;

import java.util.List;

import org.hibernate.HibernateException;

import compShop.dao.CourierDAO;
import compShop.dao.impl.CourierDAOIMpl;
import compShop.model.Courier;
import compShop.service.CourierService;
import compShop.util.HibernateUtil;

public class CourierServiceImpl implements CourierService {

	CourierDAO courierDAO = new CourierDAOIMpl();
	
	public void saveNewCourier(Courier courier) {
		try {
			HibernateUtil.beginTransaction();
			courierDAO.save(courier);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
	        System.out.println("Saving error.");
	        HibernateUtil.rollbackTransaction();
	    }
	}

	public void updateCourier(Courier courier) {
		try {
			HibernateUtil.beginTransaction();
			courierDAO.update(courier);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
	        System.out.println("Updating error.");
	        HibernateUtil.rollbackTransaction();
	    }
		
	}

	public void deleteCourier(Courier courier) {
		try {
			HibernateUtil.beginTransaction();
			courierDAO.delete(courier);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
	        System.out.println("Deleting error.");
	        HibernateUtil.rollbackTransaction();
	    }
		
	}

	public List<Courier> getAllCourier() {		
		List<Courier> allCouriers = null;		
		try {
			HibernateUtil.beginTransaction();
			allCouriers = courierDAO.findAll(Courier.class);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
	        System.out.println("Getting error.");
	        HibernateUtil.rollbackTransaction();
	    }
		
		return allCouriers;
		
	}

	public Courier getOneById(Integer id) {
		Courier courier = null;
		try {
			HibernateUtil.beginTransaction();
			courier = courierDAO.findByID(Courier.class,id);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
	        System.out.println("Getting error.");
	        HibernateUtil.rollbackTransaction();
	    }
		return courier;
	}

	public Courier getOneByOrderCode(Integer order_code) {
		Courier courier = null;
		try {
			HibernateUtil.beginTransaction();
			courier = courierDAO.getOneByOrderCode(order_code);
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
	        System.out.println("Getting error.");
	        HibernateUtil.rollbackTransaction();
	    }
		return courier;
	}

	public List<Object[]> getCountFinishedOrdersForEachCourier() {
		List<Object[]>  allCouriers = null;		
		try {
			HibernateUtil.beginTransaction();
			allCouriers = courierDAO.getCountFinishedOrdersForEachCourier();
			HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
	        System.out.println("Getting errorr.");
	        HibernateUtil.rollbackTransaction();
	    }
		
		return allCouriers;
	}

}
