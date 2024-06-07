package com.edgq.shopsystem.service;

import com.edgq.shopsystem.entity.Product;
import com.edgq.system.GenericPersistence;
import java.util.ArrayList;
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
    
    public Product findProductByVarCode(String varCode){
        Product product = null;
        try{
            return em.createQuery("SELECT p FROM Product p WHERE p.varCode = " + varCode, Product.class)
                    .getResultList()
                    .get(0);
        } catch (Exception ex){
            return product;
        }
    }
    
    /**
    * Metodo para eleiminar productos, de forma virtual, 
    * modificando el atributo active de true a false.
     * @param: Product p
     * @return: boolean
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
    
    // Contar los productos que estan en el carrito
    public int productCounterAll(List<Product> products){
        return products.size();
    }
    
    // Llena una lista de IDs diferentes en el carrito
    public List<Integer> productExtractIds(List<Product> products){
        List<Integer> ids = new ArrayList<>();
        
        for(Product item: products){
            if(item.getId() != 0){
                if(ids.isEmpty()){
                    ids.add(item.getId());
                }
                else{
                    for(Integer id: ids){
                        if(!(item.getId().intValue() == id.intValue())){
                            ids.add(item.getId());
                        }
                    }
                }
            }
        }
        return ids;
    }
    
    
}