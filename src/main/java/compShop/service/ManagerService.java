package compShop.service;

import java.util.List;
import compShop.model.Manager;

public interface ManagerService {

	void saveNewManager(Manager manager);
	void updateManager(Manager manager);
	void deleteManager(Manager manager);
	
	List<Manager> getAllManager();
	Manager getOneById(Integer id);
	
	Manager getOneByOrderCode(Integer order_code);
	List<Object[]>  getCountFinishedOrdersForEachManager();
}
