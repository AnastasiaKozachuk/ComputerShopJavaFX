package compShop.dao;

import java.io.Serializable;
import compShop.model.ItemInOrder;

public interface ItemInOrderDAO extends GenericDAO<ItemInOrder, Serializable>  {
	
	Object[] getInfoOfOrder(Integer order_code);
}
