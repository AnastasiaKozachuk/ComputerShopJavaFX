package compShop.dao;

import java.util.List;

import compShop.model.Courier;

public interface CourierDAO  extends GenericDAO<Courier, Integer> {
	
	Courier getOneByOrderCode(Integer order_code);
	List<Object[]> getCountFinishedOrdersForEachCourier();
	
}
