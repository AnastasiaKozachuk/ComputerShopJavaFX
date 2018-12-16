package compShop.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;

@SuppressWarnings("rawtypes")
public interface GenericDAO <T, Id extends Serializable> {

    void save(T entity);
    Id saveWithReturn(T entity);
    void update(T entity);
    void delete(T entity);
    List<T> findMany(Query query);
    T findOne(Query query);
    List<T> findAll( Class className);
    T findByID(Class className, Id id);
    List<Object[]> findManyFromDiffTables(Query query);
    Object[] findOneFromDiffTables(Query query);
	
}
