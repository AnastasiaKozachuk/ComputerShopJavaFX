package compShop.dao.impl;

import java.io.Serializable;
import org.hibernate.Query;
import compShop.dao.ItemInOrderDAO;
import compShop.model.ItemInOrder;
import compShop.util.HibernateUtil;

public class ItemInOrderDAOImpl  extends GenericDAOImpl<ItemInOrder, Serializable> implements ItemInOrderDAO{


	public Object[] getInfoOfOrder(Integer order_code) {
		
		String hql_query = "select O.order_code, C.cust_name, C.cust_surname, C.cust_patron , C.cust_phone, O.rec_city, O.rec_street, O.rec_house_num, O.rec_apart_num , D.delivery_type_name, P.paym_type_name, (select sum(LO.order_quant_prod*G.price) "
				+ "                                                                                                                                                                                               from ItemInOrder LO inner join LO.order_fk Ord inner join LO.good_fk G " + 
				"                                                                                                                                                                                                  where  O.order_code= Ord.order_code ) AS Summa " + 
				"from UsersOrder O inner join O.customer_fk C inner join O.deliveryType_fk D inner join O.paymentType_fk P " + 
				"where O.order_code =:userOrderCode";
		Query query = HibernateUtil.getSession().createQuery(hql_query).setParameter("userOrderCode", order_code);
		
		Object[] prod_info =findOneFromDiffTables(query);

		return prod_info;
	}

}
