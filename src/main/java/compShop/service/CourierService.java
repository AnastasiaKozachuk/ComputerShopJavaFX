package compShop.service;

import java.util.List;
import compShop.model.Courier;

public interface CourierService {

	void saveNewCourier(Courier courier);
	void updateCourier(Courier courier);
	void deleteCourier(Courier courier);
	
	List<Courier> getAllCourier();
	Courier getOneById(Integer id);
	
	Courier getOneByOrderCode(Integer order_code);
	List<Object[]> getCountFinishedOrdersForEachCourier();
	
}
