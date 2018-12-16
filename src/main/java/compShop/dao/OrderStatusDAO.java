package compShop.dao;

import compShop.model.OrderStatus;

public interface OrderStatusDAO extends GenericDAO<OrderStatus, Integer> {
	
	Object[] getOrderStatusByOrderCode(Integer order_code);
	
}
