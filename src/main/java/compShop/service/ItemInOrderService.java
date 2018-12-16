package compShop.service;

import java.util.List;
import compShop.model.ItemInOrder;

public interface ItemInOrderService {
	
	void saveNewItemInOrder(ItemInOrder itemInOrder);
	void deleteItemInOrder(ItemInOrder itemInOrder);
	
	List<ItemInOrder> getAllItemInOrder();	
	Object[] getInfoOfOrder(Integer order_code);
}
