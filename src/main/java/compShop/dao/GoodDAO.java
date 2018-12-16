package compShop.dao;

import java.util.List;

import compShop.model.Good;
import compShop.model.ItemInOrder;
import compShop.model.Supplier;

public interface GoodDAO  extends GenericDAO<Good, String> {
		
	List<Good> getGoodsWithCategory(Integer category_id);	
	List<Good> getGoodsOfManufacturer(Integer manufact_id);	
	List<Good> getGoodByDeliveryDisc(Boolean deliver_disk);
	Object[] getProductInfo(String product_article);
	List<Object[]> getGoodsForOrder(Integer order_code);
	
	List<Supplier> getSupplierOfProd(String prod_article);
	List<ItemInOrder> getAllOrderWithItem(String prod_article);
}
