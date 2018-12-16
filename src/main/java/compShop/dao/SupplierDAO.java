package compShop.dao;

import java.util.List;
import compShop.model.Good;
import compShop.model.Supplier;
import compShop.model.SupplierPhone;

public interface SupplierDAO extends GenericDAO<Supplier, Integer>  {

	List<Supplier> findSuppliersOfAllProducts();
	List<Supplier> findSuppliersMoreThanOneProd();
	List<Supplier> findSuppliersOfZeroProd();
	List<SupplierPhone> getAllSupplierPhonesById(Integer edrpou);
	
	List<Good>getDeliveryProductOfSupplier(Integer edrpou);
}
