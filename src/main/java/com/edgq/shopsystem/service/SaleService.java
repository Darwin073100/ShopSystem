package com.edgq.shopsystem.service;

import com.edgq.shopsystem.entity.Sale;
import com.edgq.shopsystem.entity.SaleItem;
import com.edgq.system.GenericPersistence;
import com.edgq.utils.Constants;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author edwin
 */
@Stateless
public class SaleService extends GenericPersistence<Sale> {

    public SaleService() {
        super(Sale.class);
    }

    public boolean createInitialSaleCar() {
        try {
            em.createNativeQuery("CALL created_sale_car();");
            return true;
        } catch (Exception e) {
            System.err.println("PROCEDURE: " + e.getMessage());
            return false;
        }
    }

    public Sale recalculateSale(Sale sale, List<SaleItem> saleItems) {
        try {
            double total = 0;
            double subTotal = 0;
            double iva = 0;
            double payQuantity = sale.getPayQuantity();
            double change = 0;

            for (SaleItem s : saleItems) {
                total += s.getTotal();
            }

            iva = calculatePayIva(total);
            subTotal = calculatePaySubTotal(iva, total);
            change = calculatePaySubTotal(total, payQuantity);

            sale.setTotal(total);
            sale.setSubTotal(subTotal);
            sale.setIva(iva);
            sale.setChangeAmount(change);

            return sale;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private double calculatePayIva(double total) {
        return total * Constants.IVA;
    }

    private double calculatePaySubTotal(double payIva, double total) {
        return total - payIva;
    }

    /**
     * @param double total;
     * @param double payQuantity;
     * @return double
     * <p>
     * Este metodo ayuda a calcular el cambio que se le regresará al
     * cliente.</p>
     */
    public double calculateChange(double total, double payQuantity) {
        if (payQuantity == 0) {
            return 0;
        } else {
            return payQuantity - total;
        }
    }

    // Buscar un item de algun producto por su codigo de barra
    public int updateQueryNative(Sale sale) {
        try {
            return em.createNativeQuery("UPDATE sale SET "
                    + "sub_total = ?, iva = ?, pay_quantity = ?, change_amount = ?, total = ?, "
                    + "customer_id = ?, paymethod_id = ?, ticket_id = ?, date=CURRENT_TIMESTAMP "
                    + "WHERE(id = ?)")
                    .setParameter(1, sale.getSubTotal())
                    .setParameter(2, sale.getIva())
                    .setParameter(3, sale.getPayQuantity()) // pay_quantity parameter
                    .setParameter(4, sale.getChangeAmount())
                    .setParameter(5, sale.getTotal())
                    .setParameter(6, sale.getCustomer().getId())
                    .setParameter(7, sale.getPayMethod().getId())
                    .setParameter(8, sale.getTicket().getId())
                    .setParameter(9, sale.getId())
                    .executeUpdate();
        } catch (Exception e) {
            return 0;
        }
    }
}
