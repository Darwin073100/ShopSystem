package com.edgq.system;

import java.util.List;

/**
 *
 * @author edwin
 */
public interface CrudMethods<T> {
    public T save(T t);
    public void update(T t);
    public void delete(T t);
    public List<T> findAll();
    public T findById(int id);
}
