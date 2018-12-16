package compShop.dao;

import java.util.List;

import compShop.model.Manager;

public interface ManagerDAO  extends GenericDAO<Manager, Integer> {
	
	Manager getOneByOrderCode(Integer order_code);
	List<Object[]>  getCountFinishedOrdersForEachManager();
}
