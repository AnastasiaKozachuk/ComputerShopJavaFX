package compShop.service.impl;

import java.util.List;

import org.hibernate.HibernateException;

import compShop.dao.OrderStatusDAO;
import compShop.dao.impl.OrderStatusDAOImpl;
import compShop.model.OrderStatus;
import compShop.service.OrderStatusService;
import compShop.util.HibernateUtil;

public class OrderStatusServiceImpl implements OrderStatusService{

	OrderStatusDAO orderStatusDAO = new OrderStatusDAOImpl();
	
	public void saveNewOrderStatus(OrderStatus order_status) {
		try {
			HibernateUtil.beginTransaction();
			orderStatusDAO.save(order_status);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Saving error.");
	            HibernateUtil.rollbackTransaction();
	        }
	}

	public void updateOrderStatus(OrderStatus order_status) {
		try {
			HibernateUtil.beginTransaction();
			orderStatusDAO.update(order_status);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Updating error.");
	            HibernateUtil.rollbackTransaction();
	        }
	}

	public void deleteOrderStatus(OrderStatus order_status) {
		try {
			HibernateUtil.beginTransaction();
			orderStatusDAO.delete(order_status);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Deleting error.");
	            HibernateUtil.rollbackTransaction();
	        }
	}

	public List<OrderStatus> getAllOrderStatus() {
		List<OrderStatus> listOrdStatus=null;
		try {
			HibernateUtil.beginTransaction();
			listOrdStatus= orderStatusDAO.findAll(OrderStatus.class);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Geting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		return listOrdStatus;
	}

	public OrderStatus getOneById(Integer id) {
		OrderStatus ordStatus=null;
		try {
			HibernateUtil.beginTransaction();
			ordStatus= orderStatusDAO.findByID(OrderStatus.class, id);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Geting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		return ordStatus;
	}

	public Object[]  getOrderStatusByOrderCode(Integer order_code) {
		Object[]  listOrdStatus=null;
		try {
			HibernateUtil.beginTransaction();
			listOrdStatus= orderStatusDAO.getOrderStatusByOrderCode(order_code);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Geting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		return listOrdStatus;
	}

}
