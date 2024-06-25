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

    public SaleItemService() {
        super(SaleItem.class);
    }

    public int saveNativeSql(int saleId, int productId, double productPrice) {
        System.out.println("Entra:::::::saveNativeSql");
        int quantity = 1;
        int saleItemId = 0;
//        String insertQuery = "INSERT INTO sale_item(id, sale_id, product_id, quantity, total) "
//                + "VALUES(DEFAULT," + saleId + "," + productId + "," + quantity + "," + productPrice + ");";
        String insertQuery = "INSERT INTO sale_item(sale_id, product_id, quantity, total) VALUES(?,?,?,?);";

        try {
            em.createNativeQuery(insertQuery)
                    .setParameter(1, saleId)
                    .setParameter(2, productId)
                    .setParameter(3, quantity)
                    .setParameter(4, productPrice)
                    .executeUpdate();
            
            saleItemId = ((Number) em.createNativeQuery("SELECT LAST_INSERT_ID();")
                    .getSingleResult()).intValue();
        } catch (Exception e) {
            System.err.println(e);
        }
        return saleItemId;
    }

    // Buscar un item de algun producto por su codigo de barra
    public SaleItem searchItemWithProductByBarCode(String barCode, int saleId) {
        try {
            return (SaleItem) em.createQuery("SELECT si FROM SaleItem si "
                    + "WHERE (si.product.varCode = :barCode AND si.sale.id = :saleId) ", SaleItem.class)
                    .setParameter("barCode", barCode)
                    .setParameter("saleId", saleId)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean modifiedQuantity(int saleItemId) {
        try {
            em.createQuery("UPDATE SaleItem si SET si.quantity=+1 WHERE(si.id= :saleItemId)", SaleItem.class)
                    .setParameter("saleItemId", saleItemId).executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Buscar todos los items de una venta en espesifica, por el ID de la venta
    public List<SaleItem> searchSaleItemBySaleId(int id) {
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
