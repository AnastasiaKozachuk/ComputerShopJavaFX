package compShop.service;

import java.util.List;
import compShop.model.Good;
import compShop.model.Supplier;
import compShop.model.SupplierPhone;

public interface SupplierService {

	void saveNewSupplier(Supplier supplier);
	void updateSupplier(Supplier supplier);
	void deleteSupplier(Supplier supplier);
	
	List<Supplier> getAllSupplier();
	Supplier getOneById(Integer id);
	
	List<Supplier> findSuppliersOfAllProducts();
	List<Supplier> findSuppliersMoreThanOneProd();
	List<Supplier> findSuppliersOfZeroProd();
	List<SupplierPhone> getAllSupplierPhonesById(Integer edrpou);
	List<Good>getDeliveryProductOfSupplier(Integer edrpou);
}
