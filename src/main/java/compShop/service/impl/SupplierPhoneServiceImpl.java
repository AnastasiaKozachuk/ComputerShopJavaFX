package compShop.service.impl;

import java.util.List;

import org.hibernate.HibernateException;

import compShop.dao.SupplierPhoneDAO;
import compShop.dao.impl.SupplierPhoneDAOImpl;
import compShop.model.SupplierPhone;
import compShop.service.SupplierPhoneService;
import compShop.util.HibernateUtil;

public class SupplierPhoneServiceImpl implements SupplierPhoneService{

	SupplierPhoneDAO suppPhoneDAO = new SupplierPhoneDAOImpl();
	
	public void saveNewSupplierPhone(SupplierPhone supplierPhone) {
		try {
			HibernateUtil.beginTransaction();
			suppPhoneDAO.save(supplierPhone);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Saving error.");
	            HibernateUtil.rollbackTransaction();
	        }
	}

	public void updateSupplierPhone(SupplierPhone supplierPhone) {
		try {
			HibernateUtil.beginTransaction();
			suppPhoneDAO.update(supplierPhone);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Updating error.");
	            HibernateUtil.rollbackTransaction();
	        }
	}

	public void deleteSupplierPhone(SupplierPhone supplierPhone) {
		try {
			HibernateUtil.beginTransaction();
			suppPhoneDAO.delete(supplierPhone);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Deleting error.");
	            HibernateUtil.rollbackTransaction();
	        }
	}

	public List<SupplierPhone> getAllSupplierPhone() {
		List<SupplierPhone> suppPhoneList = null;
		try {
			HibernateUtil.beginTransaction();
			suppPhoneList = suppPhoneDAO.findAll(SupplierPhone.class);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Getting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		return suppPhoneList;
	}

	public SupplierPhone getOneById(Integer id) {
		SupplierPhone suppPhone = null;
		try {
			HibernateUtil.beginTransaction();
			suppPhone = suppPhoneDAO.findByID(SupplierPhone.class, id);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Getting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		return suppPhone;
	}

}
