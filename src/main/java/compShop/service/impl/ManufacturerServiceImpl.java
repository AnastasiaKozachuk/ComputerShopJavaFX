package compShop.service.impl;

import java.util.List;

import org.hibernate.HibernateException;

import compShop.dao.ManufacturerDAO;
import compShop.dao.impl.ManufacturerDAOImpl;
import compShop.model.Good;
import compShop.model.Manufacturer;
import compShop.service.ManufacturerService;
import compShop.util.HibernateUtil;

public class ManufacturerServiceImpl implements ManufacturerService{

	ManufacturerDAO manufactDAO = new ManufacturerDAOImpl();
	
	public void saveNewManufacturer(Manufacturer manufacturer) {
		try {
			HibernateUtil.beginTransaction();
			manufactDAO.save(manufacturer);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Saving error.");
	            HibernateUtil.rollbackTransaction();
	        }
	}

	public void updateManufacturer(Manufacturer manufacturer) {
		try {
			HibernateUtil.beginTransaction();
			manufactDAO.update(manufacturer);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Updating error.");
	            HibernateUtil.rollbackTransaction();
	        }
	}

	public void deleteManufacturer(Manufacturer manufacturer) {
		try {
			HibernateUtil.beginTransaction();
			manufactDAO.delete(manufacturer);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Deleting error.");
	            HibernateUtil.rollbackTransaction();
	        }
	}

	public List<Manufacturer> getAllManufacturer() {
		List<Manufacturer> manufactList = null;
		try {
			HibernateUtil.beginTransaction();
			manufactList = manufactDAO.findAll(Manufacturer.class);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Getting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		return manufactList;
	}

	public Manufacturer getOneById(Integer id) {
		Manufacturer manufact= null;
		try {
			HibernateUtil.beginTransaction();
			manufact = manufactDAO.findByID(Manufacturer.class, id);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Getting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		return manufact;
	}

	public List<Good> getListOfGoodOfManufacturer(Integer manufact_id) {
		List<Good> manufactGoodsList = null;
		try {
			HibernateUtil.beginTransaction();
			manufactGoodsList = manufactDAO.getListOfGoodOfManufacturer(manufact_id);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Getting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		return manufactGoodsList;
	}

}
