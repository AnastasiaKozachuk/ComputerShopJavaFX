package compShop.dao;

import java.math.BigDecimal;
import java.util.List;

import compShop.model.ItemInOrder;
import compShop.model.UsersOrder;


public interface OrderDAO extends GenericDAO<UsersOrder, Integer> {

	List<Object[]> getOrderCodeAndCustLogByPaymType(String payment_name);
	List<Object[]> getOrderCodeAndCustLogByDelivType(String deliv_name);
	List<Object[]> getOrderCodeAndCustLogByOrdStat(String ordStat_name);
	List<Object[]> getNewOrderForManagers();
	List<Object[]> getNewOrderForCouriers();
	List<Object[]> getCurierOrders(Integer courier_id);
	List<Object[]> getCustomerOrders(String login);
	Long getCountOfFinishedOrderPerMonth();
	BigDecimal getEarnedMoneyPerMonth();
	
	List<ItemInOrder> getAllItemOfOneOrder(Integer order_code);
}
