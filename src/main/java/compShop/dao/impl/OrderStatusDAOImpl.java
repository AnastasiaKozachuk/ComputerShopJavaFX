package compShop.dao.impl;

import org.hibernate.Query;

import compShop.dao.OrderStatusDAO;
import compShop.model.OrderStatus;
import compShop.util.HibernateUtil;

public class OrderStatusDAOImpl extends GenericDAOImpl<OrderStatus, Integer> implements OrderStatusDAO {

	public Object[] getOrderStatusByOrderCode(Integer order_code) {
		String hql_query = "SELECT O.order_code, St.order_status_id, St.order_status_name "
				+ "from UsersOrder O inner join O.orderStatus_fk St "
				+ "where O.order_code=:user_order_code";

		Query query = HibernateUtil.getSession().createQuery(hql_query).setParameter("user_order_code", order_code);
		
		Object[] statusInfoForOrder = findOneFromDiffTables(query); 
		return statusInfoForOrder;
	}

}
