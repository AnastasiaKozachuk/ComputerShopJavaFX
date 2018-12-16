package compShop.service.impl;

import java.util.List;
import org.hibernate.HibernateException;

import compShop.dao.SupplierDAO;
import compShop.dao.impl.SupplierDAOImpl;
import compShop.model.Good;
import compShop.model.Supplier;
import compShop.model.SupplierPhone;
import compShop.service.SupplierService;
import compShop.util.HibernateUtil;

public class SupplierServiceImpl implements SupplierService{

	SupplierDAO supplierDAO = new SupplierDAOImpl();
	
	public void saveNewSupplier(Supplier supplier) {
		try {
			HibernateUtil.beginTransaction();
			supplierDAO.save(supplier);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Saving error.");
	            HibernateUtil.rollbackTransaction();
	        }
		
	}

	public void updateSupplier(Supplier supplier) {
		try {
			HibernateUtil.beginTransaction();
			supplierDAO.update(supplier);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Updating error.");
	            HibernateUtil.rollbackTransaction();
	        }
	}

	public void deleteSupplier(Supplier supplier) {
		try {
			HibernateUtil.beginTransaction();
			supplierDAO.delete(supplier);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Deleting error.");
	            HibernateUtil.rollbackTransaction();
	        }
	}

	public List<Supplier> getAllSupplier() {
		List<Supplier> supplList = null;
		try {
			HibernateUtil.beginTransaction();
			supplList = supplierDAO.findAll(Supplier.class);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Getting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		return supplList;
	}

	public Supplier getOneById(Integer id) {
		Supplier suppl = null;
		try {
			HibernateUtil.beginTransaction();
			suppl = supplierDAO.findByID(Supplier.class, id);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Getting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		return suppl;
	}

	public List<Supplier> findSuppliersOfAllProducts() {
		List<Supplier> supplList = null;
		try {
			HibernateUtil.beginTransaction();
			supplList = supplierDAO.findSuppliersOfAllProducts();
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Getting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		return supplList;
	}

	public List<Supplier> findSuppliersMoreThanOneProd() {
		List<Supplier> supplList = null;
		try {
			HibernateUtil.beginTransaction();
			supplList = supplierDAO.findSuppliersMoreThanOneProd();
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Getting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		return supplList;
	}

	public List<Supplier> findSuppliersOfZeroProd() {
		List<Supplier> supplList = null;
		try {
			HibernateUtil.beginTransaction();
			supplList = supplierDAO.findSuppliersOfZeroProd();
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Getting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		return supplList;
	}
	
	public List<SupplierPhone> getAllSupplierPhonesById(Integer edrpou){
		List<SupplierPhone>  supplPhonesList = null;
		try {
			HibernateUtil.beginTransaction();
			supplPhonesList = supplierDAO.getAllSupplierPhonesById(edrpou);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Getting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		return supplPhonesList;
	}

	public List<Good> getDeliveryProductOfSupplier(Integer edrpou) {
		List<Good>  goodList = null;
		try {
			HibernateUtil.beginTransaction();
			goodList = supplierDAO.getDeliveryProductOfSupplier(edrpou);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Getting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		return goodList;
	}

}
