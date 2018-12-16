package compShop.dao.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;

import org.hibernate.Session;

import compShop.dao.GenericDAO;
import compShop.util.HibernateUtil;

@SuppressWarnings({"unchecked","rawtypes"})
public abstract class GenericDAOImpl <T, Id extends Serializable> implements GenericDAO<T, Id> {

	protected Session getSession() {
        return HibernateUtil.getSession();
    }
	
	public void save(T entity) {
		Session hibernateSession = this.getSession();
        hibernateSession.save(entity);
	}


	public Id saveWithReturn(T entity){
		Session hibernateSession = this.getSession();
		return  (Id) hibernateSession.save(entity);
	}

	public void update(T entity) {
		Session hibernateSession = this.getSession();
        hibernateSession.update(entity);
	}

	public void delete(T entity) {
		Session hibernateSession = this.getSession();
        hibernateSession.delete(entity);		
	}

	public List<T> findMany(Query query) {	
		List<T> res = (List<T>) query.list();
		return res;
	}
	
	public List<Object[]> findManyFromDiffTables(Query query) {	
		List<Object[]> res = (List<Object[]>) query.list();
		return res;
	}
	
	
	public Object[] findOneFromDiffTables(Query query) {	
		Object[] res = (Object[]) query.uniqueResult();
		return res;
	}
	
	public BigDecimal countBigDecimalFromDiffTables(Query query) {	
		BigDecimal res = (BigDecimal) query.uniqueResult();
		return res;
	}
	
	public Long countInteger(Query query) {	
		Long res = (Long) query.uniqueResult();
		return res;
	}
	
	public List<Integer> findIntegerList(Query query) {	
		List<Integer>  res = (List<Integer>) query.list();
		return res;
	}
	
	public T findOne(Query query) {
		T res = (T) query.uniqueResult();
		return res;
	}

	
	public List<T> findAll(Class className) {
		Session hibernateSession = this.getSession();
        Query query = hibernateSession.createQuery("from " + className.getName());
        List T = query.list();
        return T;
	}

	public T findByID(Class className, Serializable id) {
		 Session hibernateSession = this.getSession();
	     T t = (T) hibernateSession.get(className, id);
	     return t;
	}

}
