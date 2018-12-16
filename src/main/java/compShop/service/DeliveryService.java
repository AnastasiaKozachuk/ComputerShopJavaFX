package compShop.service;

import java.util.List;
import compShop.model.Delivery;

public interface DeliveryService {

	void saveNewDelivery(Delivery delivery);
	void deleteDelivery(Delivery delivery);
	
	List<Delivery> getAllDelivery();
	
}
