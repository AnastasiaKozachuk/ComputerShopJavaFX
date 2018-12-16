package compShop.service.impl;

import java.util.List;

import org.hibernate.HibernateException;

import compShop.dao.GoodDAO;
import compShop.dao.impl.GoodDAOImpl;
import compShop.model.Good;
import compShop.model.ItemInOrder;
import compShop.model.Supplier;
import compShop.service.GoodService;
import compShop.util.HibernateUtil;

public class GoodServiceImpl implements GoodService{

	GoodDAO goodDAO = new GoodDAOImpl();
	
	public void saveNewGood(Good good) {
		try {
			HibernateUtil.beginTransaction();
			goodDAO.save(good);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Saving error.");
	            HibernateUtil.rollbackTransaction();
	        }
		
	}

	public void updateGood(Good good) {
		try {
			HibernateUtil.beginTransaction();
			goodDAO.update(good);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Updating error.");
	            HibernateUtil.rollbackTransaction();
	        }
		
	}

	public void deleteGood(Good good) {
		try {
			HibernateUtil.beginTransaction();
			goodDAO.delete(good);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Deleting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		
	}

	public List<Good> getAllGood() {
		List<Good> goodList = null;
		try {
			HibernateUtil.beginTransaction();
			goodList = goodDAO.findAll(Good.class);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Getting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		return goodList;
	}

	public Good getOneById(String id) {
		Good good = null;
		try {
			HibernateUtil.beginTransaction();
			good = goodDAO.findByID(Good.class, id);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Getting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		return good;
	}

	public List<Good> getGoodsWithCategory(Integer category_id) {
		List<Good> goodList = null;
		try {
			HibernateUtil.beginTransaction();
			goodList = goodDAO.getGoodsWithCategory(category_id);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Getting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		return goodList;
	}

	public List<Good> getGoodsOfManufacturer(Integer manufact_id) {
		List<Good> goodList = null;
		try {
			HibernateUtil.beginTransaction();
			goodList = goodDAO.getGoodsOfManufacturer(manufact_id);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Getting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		return goodList;
	}

	public List<Good> getGoodByDeliveryDisc(Boolean deliver_disk) {
		List<Good> goodList = null;
		try {
			HibernateUtil.beginTransaction();
			goodList = goodDAO.getGoodByDeliveryDisc(deliver_disk);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Getting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		return goodList;
	}

	public Object[] getProductInfo(String product_article) {
		Object[] goodInfo = null;
		try {
			HibernateUtil.beginTransaction();
			goodInfo = goodDAO.getProductInfo(product_article);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Getting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		return goodInfo;
	}

	public List<Object[]> getGoodsForOrder(Integer order_code) {
		List<Object[]> goodList = null;
		try {
			HibernateUtil.beginTransaction();
			goodList = goodDAO.getGoodsForOrder(order_code);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Getting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		return goodList;
	}

	public List<Supplier> getSupplierOfProd(String prod_article) {
		List<Supplier> supplierList = null;
		try {
			HibernateUtil.beginTransaction();
			supplierList = goodDAO.getSupplierOfProd(prod_article);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Getting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		return supplierList;
	}

	public List<ItemInOrder> getAllOrderWithItem(String prod_article) {
		List<ItemInOrder> itemsList = null;
		try {
			HibernateUtil.beginTransaction();
			itemsList = goodDAO.getAllOrderWithItem(prod_article);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Getting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		return itemsList;
	}

}
