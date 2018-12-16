package compShop.dao.impl;

import java.util.List;

import org.hibernate.Query;

import compShop.dao.CourierDAO;
import compShop.model.Courier;
import compShop.util.HibernateUtil;

public class CourierDAOIMpl extends GenericDAOImpl<Courier, Integer> implements CourierDAO {

	public Courier getOneByOrderCode(Integer order_code) {	
		String query_hql = "select C.courier_Id, C.courier_surname,C.courier_name,C.courier_patron,C.courier_phone " + 
				"from UsersOrder O inner join O.courier_fk C " + 
				"where O.order_code = :user_order_code";
		
		Query query = HibernateUtil.getSession().createQuery(query_hql).setParameter("user_order_code", order_code);
		Object[] courierRes = findOneFromDiffTables(query);
	
		Courier courier = new Courier();
		
		courier.setCourier_Id((Integer)courierRes[0]);
		courier.setCourier_surname((String) courierRes[1]);
		courier.setCourier_name((String) courierRes[2]);
		courier.setCourier_patron((String) courierRes[3]);
		courier.setCourier_phone((String) courierRes[4]);
		
		
		return courier;
	}

	public List<Object[]> getCountFinishedOrdersForEachCourier() {
		
		String query_hql = "select C.courier_Id,C.courier_name ,count(O.order_code) as finishOrders " + 
				"from UsersOrder O inner join O.courier_fk C " + 
				"where month(O.actual_deliv_date) = month(current_date()) " + 
				"group by C.courier_Id";
		
		Query query = HibernateUtil.getSession().createQuery(query_hql);
		List<Object[]> listFinishedOrders = findManyFromDiffTables(query);
		return listFinishedOrders;
	}

}
