package compShop.dao;
import java.util.List;

import compShop.model.Good;
import compShop.model.Manufacturer;

public interface ManufacturerDAO extends GenericDAO<Manufacturer, Integer>  {
	
	List<Good> getListOfGoodOfManufacturer(Integer manufact_id);
}
