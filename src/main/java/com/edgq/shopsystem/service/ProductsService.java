package com.edgq.shopsystem.service;

import com.edgq.shopsystem.entity.Product;
import com.edgq.system.GenericPersistence;
import java.util.List;
import javax.ejb.Stateless;

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
            return em.createQuery("SELECT p FROM Product p WHERE(p.active = true)", Product.class)
                .getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
    * Metodo para eleiminar productos, de forma virtual, 
    * modificando el atributo active de true a false.
    * @param Product p
    * @return boolean
    */
    public boolean deleteProduct(Product p){
        try {
            em.createNativeQuery("UPDATE product SET active=0 WHERE(id = " + p.getId() +");")
                    .executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
}