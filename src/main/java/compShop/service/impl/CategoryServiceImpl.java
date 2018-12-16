package compShop.service.impl;

import java.util.List;

import org.hibernate.HibernateException;

import compShop.dao.CategoryDAO;
import compShop.dao.impl.CategoryDAOImpl;
import compShop.model.Category;
import compShop.service.CategoryService;
import compShop.util.HibernateUtil;

public class CategoryServiceImpl implements CategoryService{
	private CategoryDAO categoryDAO = new CategoryDAOImpl();

	public void saveNewCategory(Category category) {
		try {
		HibernateUtil.beginTransaction();
		categoryDAO.save(category);
		HibernateUtil.commitTransaction();
		} catch (HibernateException ex) {
            System.out.println("Saving error.");
            HibernateUtil.rollbackTransaction();
        }
	}

	public void updateCategory(Category category) {
		try {
			HibernateUtil.beginTransaction();
			categoryDAO.update(category);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Updating error.");
	            HibernateUtil.rollbackTransaction();
	        }
		
	}

	public void deleteCategory(Category category) {
		try {
			HibernateUtil.beginTransaction();
			categoryDAO.delete(category);
			HibernateUtil.commitTransaction();
			} catch (HibernateException ex) {
	            System.out.println("Deleting error.");
	            HibernateUtil.rollbackTransaction();
	        }
		
	}

	public List<Category> getAllCategory() {	
		List<Category>  allCategories= null;
        try {
            HibernateUtil.beginTransaction();
            allCategories = categoryDAO.findAll(Category.class);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Getting error.");
        }
        return allCategories;
	}

	public Category getOneById(Integer id) {
		Category category= null;
        try {
            HibernateUtil.beginTransaction();
            category = (Category) categoryDAO.findByID(Category.class,id);
            HibernateUtil.commitTransaction();
        } catch (HibernateException ex) {
            System.out.println("Getting error.");
        }   
        return category;
	}
}
