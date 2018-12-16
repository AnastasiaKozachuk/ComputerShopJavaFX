package compShop.dao.impl;

import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import compShop.dao.OrderDAO;
import compShop.model.ItemInOrder;
import compShop.model.UsersOrder;
import compShop.util.HibernateUtil;

public class OrderDAOImpl extends GenericDAOImpl<UsersOrder, Integer> implements OrderDAO {

	public List<Object[]> getOrderCodeAndCustLogByPaymType(String payment_name) {

		String hql_query = "select O.order_code, O.customer_fk.customer_login " + 
				"from UsersOrder O " + 
				"where O.paymentType_fk.paym_type_id IN (select P.paym_type_id " + 
				"                                        from PaymentType P " + 
				"                                        where P.paym_type_name=:userPaymType)";
		
		Query query = HibernateUtil.getSession().createQuery(hql_query).setParameter("userPaymType", payment_name);
		List<Object[]> orderCodeAndCustLogin = findManyFromDiffTables(query);

		return orderCodeAndCustLogin;
	}

	public List<Object[]> getOrderCodeAndCustLogByDelivType(String deliv_name) {
	
		String hql_query = "select O.order_code, O.customer_fk.customer_login " + 
				"from UsersOrder O " + 
				"where O.deliveryType_fk.delivery_type_id IN (select D.delivery_type_id " + 
				"                                             from DeliveryType D " + 
				"                                             where D.delivery_type_name=:userDelivType)";
		
		Query query = HibernateUtil.getSession().createQuery(hql_query).setParameter("userDelivType", deliv_name);
		List<Object[]> orderCodeAndCustLogin = findManyFromDiffTables(query);

		return orderCodeAndCustLogin;
	}

	public List<Object[]> getOrderCodeAndCustLogByOrdStat(String ordStat_name) {
		String hql_query = "select O.order_code, O.customer_fk.customer_login " + 
				"from UsersOrder O " + 
				"where O.orderStatus_fk.order_status_id IN (select S.order_status_id " + 
				"                                             from OrderStatus S " + 
				"                                             where S.order_status_name=:userOrderStatus)";
		
		Query query = HibernateUtil.getSession().createQuery(hql_query).setParameter("userOrderStatus", ordStat_name);
		List<Object[]> orderCodeAndCustLogin = findManyFromDiffTables(query);
		
		return orderCodeAndCustLogin;
	}

	public List<Object[]> getNewOrderForManagers() {
		
		String hql_query = "select O.order_code, O.customer_fk.customer_login " + 
				"from UsersOrder O " + 
				"where O.orderStatus_fk.order_status_id IN (select S.order_status_id "+ 
				"                                           from OrderStatus S " + 
				"                                            where S.order_status_name= 'Нове замовлення')";
		
		Query query = HibernateUtil.getSession().createQuery(hql_query);
		List<Object[]> orderCodeAndCustLogin = findManyFromDiffTables(query);
		
		return orderCodeAndCustLogin;
	}

	public List<Object[]> getNewOrderForCouriers() {

		String hql_query = "select O.order_code, C.cust_name, C.cust_surname, C.cust_patron , C.cust_phone, O.rec_city, O.rec_street, O.rec_house_num, O.rec_apart_num , D.delivery_type_name, P.paym_type_name " + 
				"from UsersOrder O inner join O.customer_fk C inner join O.deliveryType_fk D inner join O.paymentType_fk P " + 
				"where O.orderStatus_fk.order_status_id =8";
		
		Query query = HibernateUtil.getSession().createQuery(hql_query);
		List<Object[]> newOrders = findManyFromDiffTables(query);
		
		return newOrders;
	}

	public List<Object[]> getCurierOrders(Integer courier_id) {
		String hql_query = "select O.order_code, C.cust_name, C.cust_surname, C.cust_patron , C.cust_phone, O.rec_city, O.rec_street, O.rec_house_num, O.rec_apart_num , D.delivery_type_name, P.paym_type_name " + 
				"from UsersOrder O inner join O.customer_fk C inner join O.deliveryType_fk D inner join O.paymentType_fk P " + 
				"where O.courier_fk.courier_Id =:user_courier_id";
		Query query = HibernateUtil.getSession().createQuery(hql_query).setParameter("user_courier_id", courier_id);;
		List<Object[]> newOrders = findManyFromDiffTables(query);
		
		return newOrders;
	}

	public List<Object[]> getCustomerOrders(String login) {
		String hql_query = "select O.order_code, O.rec_city, O.rec_street, O.rec_house_num, O.rec_apart_num , D.delivery_type_name, P.paym_type_name , (select sum(LO.order_quant_prod*G.price) " + 
				"				                                                                                                                 from ItemInOrder LO inner join LO.order_fk Ord inner join LO.good_fk G " + 
				"				                                                                                                                 where  O.order_code= Ord.order_code ) AS Summa " +
				"from UsersOrder O inner join O.customer_fk C inner join O.deliveryType_fk D inner join O.paymentType_fk P " + 
				"WHERE C.customer_login =:userLogin";
		Query query = HibernateUtil.getSession().createQuery(hql_query).setParameter("userLogin", login);
		List<Object[]> customOrders = findManyFromDiffTables(query);
		
		return customOrders;
	}

	public Long getCountOfFinishedOrderPerMonth() {
		String hql_query ="select count(*) as finishOrders " + 
				"from UsersOrder O " + 
				"where month(O.actual_deliv_date) = month(current_date()) ";
		Query query = HibernateUtil.getSession().createQuery(hql_query);
		Long counted = countInteger(query); 
		
		return counted;
	}

	public BigDecimal getEarnedMoneyPerMonth() {
		String hql_query = "select sum(LO.order_quant_prod*G.price) " + 
				"from ItemInOrder LO inner join LO.order_fk Ord inner join LO.good_fk G " + 
				"where month(Ord.actual_deliv_date) = month(current_date()) ";
		
		Query query = HibernateUtil.getSession().createQuery(hql_query);
		BigDecimal counted = countBigDecimalFromDiffTables(query); 
		
		return counted;
	}

	public List<ItemInOrder> getAllItemOfOneOrder(Integer order_code) {
		UsersOrder order = findByID(UsersOrder.class, order_code);	
		
		return order.getGoodsItemsList();
	}

}
