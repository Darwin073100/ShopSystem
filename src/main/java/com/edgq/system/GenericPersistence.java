package com.edgq.system;

import com.edgq.utils.Constants;
import com.edgq.utils.JPAUtil;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @param <T>
 * @author edwin
 */
public abstract class GenericPersistence<T> implements CrudMethods<T>{
    
    @PersistenceContext(unitName = Constants.PERSISTENCE_UNIT)
    protected EntityManager em;
//    protected EntityManager em;
    
    private final Class<T> entityClass;
    private String TABLE_NAME = "";
    
    protected GenericPersistence(Class<T> entityClass) {
        this.entityClass = entityClass;
//        em = JPAUtil.getEntityManagerFactory().createEntityManager();
    }
    
    @Override
    public void save(T entity){
        em.persist(entity);
    }
    
    @Override
    public void update(T entity){
        em.merge(entity);
    }
    
    @Override
    public void delete(T entity){
        em.remove(entity);
    }
    
    @Override
    public List<T> findAll(){
        return em.createNamedQuery("SELECT T FROM " + entityClass + " T")
                .getResultList();
    }
    
    @Override
    public T findById(int id){
        return em.find(entityClass, id);
    }
    
}
