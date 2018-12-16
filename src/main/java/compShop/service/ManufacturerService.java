package compShop.service;

import java.util.List;

import compShop.model.Good;
import compShop.model.Manufacturer;

public interface ManufacturerService {

	void saveNewManufacturer(Manufacturer manufacturer);
	void updateManufacturer(Manufacturer manufacturer);
	void deleteManufacturer(Manufacturer manufacturer);
	
	List<Manufacturer> getAllManufacturer();
	Manufacturer getOneById(Integer id);
	
	List<Good> getListOfGoodOfManufacturer(Integer manufact_id);
	
}
