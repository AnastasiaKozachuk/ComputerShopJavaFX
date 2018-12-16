package compShop.dao;
import java.math.BigDecimal;

import compShop.model.Customer;

public interface CustomerDAO  extends GenericDAO<Customer, String>  {
	
	BigDecimal countFullSumOfBoughtGoods(String login);	
	
}
