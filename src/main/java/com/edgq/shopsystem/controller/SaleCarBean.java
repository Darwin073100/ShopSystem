package com.edgq.shopsystem.controller;

import com.edgq.shopsystem.entity.Customer;
import com.edgq.shopsystem.entity.PayMethod;
import com.edgq.shopsystem.entity.Product;
import com.edgq.shopsystem.entity.Sale;
import com.edgq.shopsystem.entity.SaleItem;
import com.edgq.shopsystem.entity.Ticket;
import com.edgq.shopsystem.service.CustomerService;
import com.edgq.shopsystem.service.PayMethodService;
import com.edgq.shopsystem.service.ProductsService;
import com.edgq.shopsystem.service.SaleItemService;
import com.edgq.shopsystem.service.SaleService;
import com.edgq.shopsystem.service.TicketService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author edwin
 */
@Named
@ViewScoped
public class SaleCarBean implements Serializable {

    private final int INITIAL_PRODUCT_QUANTITY = 1;

    @Inject
    private CustomerService customerService;
    @Inject
    private ProductsService productService;
    @Inject
    private SaleService saleService;
    @Inject
    private SaleItemService saleItemService;
    @Inject
    private SessionBean session;
    @Inject
    private TicketService ticketService;
    @Inject
    private PayMethodService payMethodService;

    @Getter
    @Setter
    private Sale saleCarActive;
    
    private double payQuantity = 0.0;

    @Getter
    @Setter
    private List<Product> saleCar = new ArrayList<>();
    @Getter
    @Setter
    private String varCodeInput;

    @Getter
    @Setter
    private List<Customer> customers;
    @Getter
    @Setter
    private Customer customerSelected = new Customer();
    @Getter
    @Setter
    private List<Ticket> tickets;
    @Getter
    @Setter
    private Ticket ticketSelected = new Ticket();
    @Getter
    @Setter
    private List<PayMethod> payMethods;
    @Getter
    @Setter
    private PayMethod payMethodSelected = new PayMethod();

    @Getter
    @Setter
    private int customerIdSelected;

    @Getter
    @Setter
    private int payMethodIdSelected;

    @Getter
    @Setter
    private int ticketIdSelected;

    @Setter
    @Getter
    private List<SaleItem> saleItems;

    @PostConstruct
    public void init() {
        findAllCustomer();
        findAllTickets();
        findAllPayMethods();
        saleCarActive = new Sale(0.0, 0.0, 0.0, 0.0, 0.0);
    }

    public void findAllCustomer() {
        try {
            customers = customerService.findAll();
            if (customers != null && !customers.isEmpty()) {
                customerIdSelected = customers.get(0).getId(); // Selecciona el primer cliente por defecto, saleItemSearched es necesario
            }
        } catch (Exception e) {
            customers = null;
        }
    }

    public void findAllTickets() {
        try {
            tickets = ticketService.findAll();
            if (tickets != null && !tickets.isEmpty()) {
                ticketIdSelected = tickets.get(0).getId();
            }
        } catch (Exception e) {
            tickets = null;
        }
    }

    public void findAllPayMethods() {
        try {
            payMethods = payMethodService.findAll();
            if (payMethods != null && !payMethods.isEmpty()) {
                payMethodIdSelected = payMethods.get(0).getId();
            }
        } catch (Exception e) {
            payMethods = null;
        }
    }

    // Captura en tiempo real el value del input para 
    // buscar un producto por su codigo de barra
    public void setInputVarCode(ValueChangeEvent event) {
        varCodeInput = event.getNewValue().toString();
        System.err.println(varCodeInput);
    }
    
    public void setInputPayQuantity(ValueChangeEvent event){
        double payQuantity = Double.parseDouble(event.getNewValue().toString());
        System.out.println(payQuantity);
        double changeAmount = 0;
        changeAmount = saleService.calculateChange(saleCarActive.getTotal(), payQuantity);
        saleCarActive.setPayQuantity(payQuantity);
        saleCarActive.setChangeAmount(changeAmount);
        System.out.println(saleCarActive);
    } 

    public void findProductByVarCode() {
        Product productSearched = null;
        SaleItem saleItemSearched = null;
        
        // Busca un producto y se asigna a una referencia (productSearched)
        productSearched = productService.findProductByVarCode(varCodeInput);

        if (productSearched != null) {
            
            // Si hay una venta se agrega el item a esa venta, caso contrario se crea la venta y se agrega el item
            if (saleCarActive.getId() != null) {
                addItemToSale(saleItemSearched, productSearched);
            } else {
                createSaleCarInitial();
                addItemToSale(saleItemSearched, productSearched);
            }
        }
    }

    public void addItemToSale(SaleItem saleItemSearched, Product productSearched) {
        // Busca un item que tenga el producto antes ya consultado
        saleItemSearched = saleItemService.searchItemWithProductByBarCode(varCodeInput, saleCarActive.getId());
        if (saleItemSearched != null) {
            int beforeQuantity = saleItemSearched.getQuantity();
            int afterQuantity = beforeQuantity + 1;
            saleItemSearched.setQuantity(afterQuantity);
            saleItemService.update(saleItemSearched);
            saleItems = saleItemService.searchSaleItemBySaleId(saleCarActive.getId());
            saleCarActive = saleService.recalculateSale(saleCarActive, saleItems);
        } else {
            saleItemService.saveNativeSql(saleCarActive.getId(), productSearched.getId(), productSearched.getPrice());
            saleItems = saleItemService.searchSaleItemBySaleId(saleCarActive.getId());
            saleCarActive = saleService.recalculateSale(saleCarActive, saleItems);
        }
    }

    public void createSaleCarInitial() {
        if (customerIdSelected > 0 && ticketIdSelected > 0 && payMethodIdSelected > 0) {
            customerSelected.setId(customerIdSelected);
            ticketSelected.setId(ticketIdSelected);
            payMethodSelected.setId(payMethodIdSelected);
        } else {
            System.err.println("No se ha seleccionado ningun Customer");
        }
        saleCarActive = saleService.save(new Sale(0, 0.0, 0.0, 0.0, 0.0, 0.0, new Date(), session.getUserInSession(), customerSelected, ticketSelected, payMethodSelected, null));
        System.out.println("Venta generada: " + saleCarActive);
    }
    
    public String finishSale(){
        try {
            saleService.updateQueryNative(saleCarActive);
            return "/pages/SaleFinish.xhtml?faces-redirect=true";
        } catch (Exception e) {
            return null;
        }
    }

    public void trashSaleCar() {
        System.out.println("------------CLEAN SALE CAR-------------");

        System.out.println("---------------------------------------");
    }

}
