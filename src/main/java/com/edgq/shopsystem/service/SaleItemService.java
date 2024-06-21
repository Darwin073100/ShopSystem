package com.edgq.shopsystem.service;

import com.edgq.shopsystem.entity.SaleItem;
import com.edgq.system.GenericPersistence;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author edwin
 */
@Stateless
public class SaleItemService extends GenericPersistence<SaleItem> {
    public SaleItemService(){
        super(SaleItem.class);
    } 
    
    
    // Buscar un item de algun producto por su codigo de barra
    public SaleItem searchItemWithProductByBarCode(String barCode){
        try {
            return (SaleItem) em.createQuery("SELECT si FROM SaleItem si "
                    + "WHERE (si.product.varCode = :barCode) ", SaleItem.class)
                    .setParameter("barCode", barCode)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public Boolean modifiedQuantity(int saleItemId){
        try{
            em.createQuery("UPDATE SaleItem si SET si.quantity=+1 WHERE(si.id= :saleItemId)",SaleItem.class)
                    .setParameter("saleItemId", saleItemId);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    // Buscar todos los items de una venta en espesifica, por el ID de la venta
    public List<SaleItem> searchSaleItemBySaleId(int id){
        try {
            return em.createQuery("SELECT si FROM SaleItem si "
                    + "WHERE(si.sale.id = :saleId)", SaleItem.class)
                    .setParameter("saleId", id)
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }    
    
}
