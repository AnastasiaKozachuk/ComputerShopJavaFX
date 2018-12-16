package compShop.service;

import java.util.List;
import compShop.model.OrderStatus;

public interface OrderStatusService {

	void saveNewOrderStatus(OrderStatus order_status);
	void updateOrderStatus(OrderStatus order_status);
	void deleteOrderStatus(OrderStatus order_status);
	
	List<OrderStatus> getAllOrderStatus();
	OrderStatus getOneById(Integer id);
	
	
	Object[]  getOrderStatusByOrderCode(Integer order_code);
}
