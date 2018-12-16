package compShop.service.impl;

import java.util.List;

import org.hibernate.HibernateException;

import compShop.dao.ItemInOrderDAO;
import compShop.dao.impl.ItemInOrderDAOImpl;
import compShop.model.ItemInOrder;
import compShop.service.ItemInOrderService;
import compShop.util.HibernateUtil;

public class ItemInOrderServiceImpl implements ItemInOrderService{

	ItemInOrderDAO itemInOrderDAO = new ItemInOrderDAOImpl();


	public List<ItemInOrder> getAllItemInOrder() {
		List<ItemInOrder> listItems = null;
		try {
			HibernateUtil.beginTransaction();
			listItems = itemInOrderDAO.findAll(ItemInOrder.class);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Getting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		
		return listItems;
	}


	public Object[] getInfoOfOrder(Integer order_code) {
		Object[] orderInfo = null;
		try {
			HibernateUtil.beginTransaction();
			orderInfo = itemInOrderDAO.getInfoOfOrder(order_code);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Getting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		
		return orderInfo;
	}


	public void saveNewItemInOrder(ItemInOrder itemInOrder) {
		try {
			HibernateUtil.beginTransaction();
			itemInOrderDAO.save(itemInOrder);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Saving error.");
	            HibernateUtil.rollbackTransaction();
	        }
		
	}


	public void deleteItemInOrder(ItemInOrder itemInOrder) {
		try {
			HibernateUtil.beginTransaction();
			itemInOrderDAO.delete(itemInOrder);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Deleting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		
	}

}
