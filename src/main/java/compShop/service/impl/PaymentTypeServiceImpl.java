package compShop.service.impl;

import java.util.List;

import org.hibernate.HibernateException;

import compShop.dao.PaymentTypeDAO;
import compShop.dao.impl.PaymentTypeDAOImpl;
import compShop.model.PaymentType;
import compShop.service.PaymentTypeService;
import compShop.util.HibernateUtil;

public class PaymentTypeServiceImpl implements PaymentTypeService{

	PaymentTypeDAO paymTypeDAO = new PaymentTypeDAOImpl();
	
	public void saveNewPaymentType(PaymentType paymentType) {
		try {
			HibernateUtil.beginTransaction();
			paymTypeDAO.save(paymentType);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Saving error.");
	            HibernateUtil.rollbackTransaction();
	        }
	}

	public void updatePaymentType(PaymentType paymentType) {
		try {
			HibernateUtil.beginTransaction();
			paymTypeDAO.update(paymentType);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Updating error.");
	            HibernateUtil.rollbackTransaction();
	        }
	}

	public void deletePaymentType(PaymentType paymentType) {
		try {
			HibernateUtil.beginTransaction();
			paymTypeDAO.delete(paymentType);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Deleting error.");
	            HibernateUtil.rollbackTransaction();
	        }
	}

	public List<PaymentType> getAllPaymentType() {
		List<PaymentType> listPaymType = null;
		try {
			HibernateUtil.beginTransaction();
			listPaymType = paymTypeDAO.findAll(PaymentType.class);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Getting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		return listPaymType;
	}

	public PaymentType getOneById(Integer id) {
		PaymentType paymType = null;
		try {
			HibernateUtil.beginTransaction();
			paymType = paymTypeDAO.findByID(PaymentType.class, id);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Getting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		return paymType;
	}

}
