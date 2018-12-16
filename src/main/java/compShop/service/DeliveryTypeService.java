package compShop.service;

import java.util.List;
import compShop.model.DeliveryType;

public interface DeliveryTypeService {
	
	void saveNewDeliveryType(DeliveryType deliveryType);
	void updateDeliveryType(DeliveryType deliveryType);
	void deleteDeliveryType(DeliveryType deliveryType);
	
	List<DeliveryType> getAllDeliveryType();
	DeliveryType getOneById(Integer id);
	
}
