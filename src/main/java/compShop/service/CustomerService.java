package compShop.service;

import java.math.BigDecimal;
import java.util.List;
import compShop.model.Customer;

public interface CustomerService {

	void saveNewCustomer(Customer customer);
	void updateCustomer(Customer customer);
	void deleteCustomer(Customer customer);
	
	List<Customer> getAllCustomer();
	Customer getOneByLogin(String login);
	
	BigDecimal countFullSumOfBoughtGoods(String login);	
	
}
