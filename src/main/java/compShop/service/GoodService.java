package compShop.service;

import java.util.List;


import compShop.model.Good;
import compShop.model.ItemInOrder;
import compShop.model.Supplier;

public interface GoodService {

	void saveNewGood(Good good);
	void updateGood(Good good);
	void deleteGood(Good good);
	
	List<Good> getAllGood();
	Good getOneById(String id);
	
	List<Good> getGoodsWithCategory(Integer category_id);	
	List<Good> getGoodsOfManufacturer(Integer manufact_id);	
	List<Good> getGoodByDeliveryDisc(Boolean deliver_disk);
	Object[] getProductInfo(String product_article);
	List<Object[]> getGoodsForOrder(Integer order_code);
	List<Supplier> getSupplierOfProd(String prod_article);
	List<ItemInOrder> getAllOrderWithItem(String prod_article);
}
