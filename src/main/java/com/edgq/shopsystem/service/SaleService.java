package com.edgq.shopsystem.service;

import com.edgq.shopsystem.entity.Sale;
import com.edgq.system.GenericPersistence;
import javax.ejb.Stateless;

/**
 *
 * @author edwin
 */
@Stateless
public class SaleService extends GenericPersistence<Sale>{
    public SaleService(){
        super(Sale.class);
    }
    
    public boolean createInitialSaleCar(){
        try {
            em.createNativeQuery("CALL created_sale_car();");
            return true;
        } catch (Exception e) {
            System.err.println("PROCEDURE: " + e.getMessage());
            return false;
        }
    }
}
