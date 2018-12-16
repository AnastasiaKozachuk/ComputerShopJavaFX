package compShop.service;

import java.util.List;

import compShop.model.Category;

public interface CategoryService {
	
	void saveNewCategory(Category category);
	void updateCategory(Category category);
	void deleteCategory(Category category);
	
	List<Category> getAllCategory();
	Category getOneById(Integer id);
	
	
}
