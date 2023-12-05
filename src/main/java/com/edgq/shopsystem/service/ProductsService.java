package com.edgq.shopsystem.service;

import com.edgq.shopsystem.entity.Product;
import com.edgq.system.GenericPersistence;
import java.util.List;
import javax.ejb.Stateless;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author edwin
 */
@Stateless
public class ProductsService extends GenericPersistence<Product> {  
    
    public ProductsService() {
        super(Product.class);
    }  
    
    @Override
    public List<Product> findAll(){
        try {
            return em.createQuery("SELECT p FROM Product p", Product.class)
                .getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
}