package compShop.service;

import java.util.List;
import compShop.model.SupplierPhone;

public interface SupplierPhoneService {
	void saveNewSupplierPhone(SupplierPhone supplierPhone);
	void updateSupplierPhone(SupplierPhone supplierPhone);
	void deleteSupplierPhone(SupplierPhone supplierPhone);
	
	List<SupplierPhone> getAllSupplierPhone();
	SupplierPhone getOneById(Integer id);
	
}
