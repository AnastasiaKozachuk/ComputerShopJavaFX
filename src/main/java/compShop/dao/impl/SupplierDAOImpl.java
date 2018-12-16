package compShop.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import compShop.dao.SupplierDAO;
import compShop.model.Good;
import compShop.model.Supplier;
import compShop.model.SupplierPhone;
import compShop.util.HibernateUtil;

public class SupplierDAOImpl extends GenericDAOImpl<Supplier, Integer> implements SupplierDAO {

	public List<Supplier> findSuppliersOfAllProducts() {
		String hql_query = "from Supplier Sup " +
				"where not exists ( select D.prod_article_fk " +
				"                   from Delivery D " +
				"                   where D.prod_article_fk not in (select D2.prod_article_fk " +
				"                                                   from Delivery D2 " +
				"                                                   where D2.edrpou_fk = Sup.edrpou ))";
		
		
		Query query = HibernateUtil.getSession().createQuery(hql_query);
		List<Supplier> listSuppl = findMany(query);
		
		return listSuppl;
	}

	public List<Supplier> findSuppliersMoreThanOneProd() {

		String hql_query ="select D.edrpou_fk "
				+ "from Delivery D " + 
				"group by D.edrpou_fk " +
				"having count(D.prod_article_fk)>1";
		
		Query query = HibernateUtil.getSession().createQuery(hql_query);
		List<Integer> listSupplEdrpou = findIntegerList(query);

		List<Supplier> supplierList = new ArrayList<Supplier>();
		
		for(Integer edrpou: listSupplEdrpou) {
			String hql_query_edrpou = "from Supplier S "
					+ "where S.edrpou=:one_edrpou";
			Query query_one_sup = HibernateUtil.getSession().createQuery(hql_query_edrpou).setParameter("one_edrpou", edrpou);
			supplierList.add(findOne(query_one_sup));	
		}
		
		return supplierList;
	}

	public List<Supplier> findSuppliersOfZeroProd() {
		
		String hql_query = "from Supplier Sup " +
				"where Sup.edrpou not in (select distinct  D.edrpou_fk " +
				"                         from Delivery D )";
		
		Query query = HibernateUtil.getSession().createQuery(hql_query);
		List<Supplier> listSuppl = findMany(query);
		return listSuppl;
	}
	
	public List<SupplierPhone> getAllSupplierPhonesById(Integer edrpou) {
		String hql_query ="from Supplier S "
				+ "where S.edrpou=:user_edrpou";
		
		Query query = HibernateUtil.getSession().createQuery(hql_query).setParameter("user_edrpou", edrpou);
		Supplier suppl = findOne(query);
		return suppl.getSupPhones();
	}

	public List<Good> getDeliveryProductOfSupplier(Integer edrpou) {
		
		Supplier suppl = findByID(Supplier.class, edrpou);	
		
		return suppl.getDeliveredGoods();	
	}

}
