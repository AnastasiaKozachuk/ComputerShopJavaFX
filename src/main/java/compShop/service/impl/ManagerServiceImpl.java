package compShop.service.impl;

import java.util.List;

import org.hibernate.HibernateException;

import compShop.dao.ManagerDAO;
import compShop.dao.impl.ManagerDAOImpl;
import compShop.model.Manager;
import compShop.service.ManagerService;
import compShop.util.HibernateUtil;

public class ManagerServiceImpl implements ManagerService{

	ManagerDAO managerDAO = new ManagerDAOImpl();
	
	public void saveNewManager(Manager manager) {
		try {
		HibernateUtil.beginTransaction();
		managerDAO.save(manager);
		HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
            System.out.println("Saving error.");
            HibernateUtil.rollbackTransaction();
        }
	}

	public void updateManager(Manager manager) {
		try {
			HibernateUtil.beginTransaction();
			managerDAO.update(manager);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Updating error.");
	            HibernateUtil.rollbackTransaction();
	        }
	}

	public void deleteManager(Manager manager) {
		try {
			HibernateUtil.beginTransaction();
			managerDAO.delete(manager);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Deleting error.");
	            HibernateUtil.rollbackTransaction();
	        }
	}

	public List<Manager> getAllManager() {
		List<Manager> listManagers = null;
		try {
			HibernateUtil.beginTransaction();
			listManagers = managerDAO.findAll(Manager.class);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Geting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		
		return listManagers;
	}

	public Manager getOneById(Integer id) {
		Manager manager = null;
		try {
			HibernateUtil.beginTransaction();
			manager = managerDAO.findByID(Manager.class,id);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Geting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		return manager;
		
	}

	public Manager getOneByOrderCode(Integer order_code) {
		Manager manager = null;
		try {
			HibernateUtil.beginTransaction();
			manager = managerDAO.getOneByOrderCode(order_code);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Geting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		return manager;
	}

	public List<Object[]> getCountFinishedOrdersForEachManager() {
		List<Object[]> listFinishedOrders = null;
		try {
			HibernateUtil.beginTransaction();
			listFinishedOrders = managerDAO.getCountFinishedOrdersForEachManager();
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Geting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		return listFinishedOrders;
	}

}
