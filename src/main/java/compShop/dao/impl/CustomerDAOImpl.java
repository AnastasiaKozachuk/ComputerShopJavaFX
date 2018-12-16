package compShop.dao.impl;

import java.math.BigDecimal;
import org.hibernate.Query;
import compShop.dao.CustomerDAO;
import compShop.model.Customer;
import compShop.util.HibernateUtil;

public class CustomerDAOImpl  extends GenericDAOImpl<Customer, String> implements CustomerDAO {

	public BigDecimal countFullSumOfBoughtGoods(String login) {
		
		String query_hql = "select sum(IO.order_quant_prod*G.price)"  
				 +"from ItemInOrder IO inner join IO.order_fk O inner join IO.good_fk G inner join O.customer_fk C "
				+ "where C.customer_login=:user_login";
		Query query = HibernateUtil.getSession().createQuery(query_hql).setParameter("user_login", login);
		BigDecimal  countSum = countBigDecimalFromDiffTables(query);
				
		return countSum;
	}

}
