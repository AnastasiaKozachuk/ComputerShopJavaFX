package compShop.service.impl;

import java.util.List;
import org.hibernate.HibernateException;
import compShop.dao.DeliveryTypeDAO;
import compShop.dao.impl.DeliveryTypeDAOImpl;
import compShop.model.DeliveryType;
import compShop.service.DeliveryTypeService;
import compShop.util.HibernateUtil;

public class DeliveryTypeServiceImpl implements DeliveryTypeService {

	DeliveryTypeDAO delTypeDAO = new DeliveryTypeDAOImpl();
	
	public void saveNewDeliveryType(DeliveryType deliveryType) {
		try {
		HibernateUtil.beginTransaction();
		delTypeDAO.save(deliveryType);
		HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
            System.out.println("Saving error.");
            HibernateUtil.rollbackTransaction();
        }
		
	}

	public void updateDeliveryType(DeliveryType deliveryType) {
		try {
			HibernateUtil.beginTransaction();
			delTypeDAO.update(deliveryType);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Updating error.");
	            HibernateUtil.rollbackTransaction();
	        }
		
	}

	public void deleteDeliveryType(DeliveryType deliveryType) {
		try {
			HibernateUtil.beginTransaction();
			delTypeDAO.delete(deliveryType);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Deliting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		
	}

	public List<DeliveryType> getAllDeliveryType() {
		List<DeliveryType>  listDelType = null;
		try {
			HibernateUtil.beginTransaction();
			listDelType = delTypeDAO.findAll(DeliveryType.class);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Getting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		return listDelType;
	}

	public DeliveryType getOneById(Integer id) {
		DeliveryType delType = null;
		try {
			HibernateUtil.beginTransaction();
			delType = delTypeDAO.findByID(DeliveryType.class, id);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Getting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		return delType;
	}


	
}
