package compShop.dao.impl;

import java.util.List;

import org.hibernate.Query;

import compShop.dao.ManufacturerDAO;
import compShop.model.Good;
import compShop.model.Manufacturer;
import compShop.util.HibernateUtil;

public class ManufacturerDAOImpl extends GenericDAOImpl<Manufacturer, Integer> implements ManufacturerDAO {
	
	public List<Good> getListOfGoodOfManufacturer(Integer manufact_id) {
		String hql_query = "from Manufacturer "
				+ "where manufact_id=:user_manufact_id";
		
		Query query = HibernateUtil.getSession().createQuery(hql_query).setParameter("user_manufact_id", manufact_id);			
		Manufacturer manufact= findOne(query);
		
		return manufact.getGoodsList();
	}

}
