package compShop.dao.impl;

import java.util.List;

import org.hibernate.Query;

import compShop.dao.ManagerDAO;
import compShop.model.Manager;
import compShop.util.HibernateUtil;

public class ManagerDAOImpl extends GenericDAOImpl<Manager, Integer> implements ManagerDAO{

	public Manager getOneByOrderCode(Integer order_code) {
		String query_hql = "select M.manager_id, M.manager_surname,M.manager_name,M.manager_patron,M.manager_phone " + 
				"from UsersOrder O inner join O.manager_fk M " + 
				"where O.order_code = :user_order_code";
		
		Query query = HibernateUtil.getSession().createQuery(query_hql).setParameter("user_order_code", order_code);
		Object[] managerRes = findOneFromDiffTables(query);
	
		Manager manager = new Manager();
		
		manager.setManager_id((Integer)managerRes[0]);
		manager.setManager_surname((String) managerRes[1]);
		manager.setManager_name((String) managerRes[2]);
		manager.setManager_patron((String) managerRes[3]);
		manager.setManager_phone((String) managerRes[4]);
		
		
		return manager;
	}

	public List<Object[]> getCountFinishedOrdersForEachManager() {

		String query_hql = "select M.manager_id,M.manager_name ,count(O.order_code) as finishOrders " + 
				"from UsersOrder O inner join O.manager_fk M " + 
				"where month(O.actual_deliv_date) = month(current_date()) " + 
				"group by M.manager_id";
		
		Query query = HibernateUtil.getSession().createQuery(query_hql);
		List<Object[]> listFinishedOrders = findManyFromDiffTables(query);
		return listFinishedOrders;
	}

}
