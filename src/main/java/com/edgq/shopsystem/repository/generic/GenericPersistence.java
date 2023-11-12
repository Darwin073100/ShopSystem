package com.edgq.shopsystem.repository.generic;

import com.edgq.shopsystem.utils.Constants;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author edwin
 */
public abstract class GenericPersistence<T> {
    
    @PersistenceContext(unitName = Constants.PERSISTENCE_UNIT)
    protected EntityManager em;
    
    private final Class<T> entityClass;
    private String TABLE_NAME = "";
    
    public GenericPersistence(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    public void create(T entity){
        em.persist(entity);
    }
    
    public void update(T entity){
        em.merge(entity);
    }
    
    public void delete(T entity){
        em.remove(entity);
    }
    
    public List<T> findAll(){
        return em.createNamedQuery("SELECT T FROM " + entityClass + " T")
                .getResultList();
    }
    
    public T findById(Integer id){
        return em.find(entityClass, id);
    }
    
}
