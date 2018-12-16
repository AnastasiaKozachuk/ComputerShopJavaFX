package compShop.dao.impl;

import java.util.List;

import org.hibernate.Query;

import compShop.dao.GoodDAO;
import compShop.model.Good;
import compShop.model.ItemInOrder;
import compShop.model.Supplier;
import compShop.util.HibernateUtil;

public class GoodDAOImpl  extends GenericDAOImpl<Good, String> implements GoodDAO{

	public List<Good> getGoodsWithCategory(Integer category_id) {
	
		String hql_query = "from Good G "
				+ "where G.category_fk.categoryId=:user_category_id";
		Query query = HibernateUtil.getSession().createQuery(hql_query).setParameter("user_category_id", category_id);
		List<Good> goodsList = findMany(query);
		
		return goodsList;
	}

	public List<Good> getGoodsOfManufacturer(Integer manufact_id) {
	
		String hql_query = "from Good G "
				+ "where G.manufact_fk.manufact_id=:user_manufact_id";
		Query query = HibernateUtil.getSession().createQuery(hql_query).setParameter("user_manufact_id", manufact_id);
		List<Good> goodsList = findMany(query);
		
		return goodsList;
	}

	public List<Good> getGoodByDeliveryDisc(Boolean deliver_disk) {
		String hql_query = "from Good G "
				+ "where G.deliver_disk=:user_deliver_disk";
		Query query = HibernateUtil.getSession().createQuery(hql_query).setParameter("user_deliver_disk", deliver_disk);
		List<Good> goodsList = findMany(query);
		
		return goodsList;
	}

	public Object[] getProductInfo(String product_article) {
		String hql_query = "select G.product_article, G.prod_full_name, G.inStock, G.min_stock, G.price, C.categoryName, M.manufact_name " + 
				"from Good G inner join G.category_fk C inner join G.manufact_fk M " + 
				"where G.product_article=:userProdArticle";
		Query query = HibernateUtil.getSession().createQuery(hql_query).setParameter("userProdArticle", product_article);
		Object[] prod_info =findOneFromDiffTables(query);
		return prod_info;
	}

	public List<Object[]> getGoodsForOrder(Integer order_code) {
		String hql_query = "SELECT G.product_article,G.prod_full_name,LO.order_quant_prod,G.price as PricePerUnit ,LO.order_quant_prod*G.price as WholePrice " + 
				"FROM ItemInOrder LO inner join LO.order_fk Ord inner join LO.good_fk G " + 
				"WHERE Ord.order_code =:userOrderCode";
		Query query = HibernateUtil.getSession().createQuery(hql_query).setParameter("userOrderCode", order_code);
		List<Object[]> prod_info =findManyFromDiffTables(query);
		return prod_info;
	}

	public List<Supplier> getSupplierOfProd(String prod_article) {
		Good good = findByID(Good.class, prod_article);	
		
		return good.getGoodSuppliers();	
	}

	public List<ItemInOrder> getAllOrderWithItem(String prod_article) {
		Good good = findByID(Good.class, prod_article);	
		
		return good.getGoodsItemsList();
	}

}
