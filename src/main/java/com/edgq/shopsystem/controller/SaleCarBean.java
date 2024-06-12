package com.edgq.shopsystem.controller;

import com.edgq.shopsystem.entity.Customer;
import com.edgq.shopsystem.entity.PayMethod;
import com.edgq.shopsystem.entity.Product;
import com.edgq.shopsystem.entity.Sale;
import com.edgq.shopsystem.entity.Ticket;
import com.edgq.shopsystem.service.CustomerService;
import com.edgq.shopsystem.service.PayMethodService;
import com.edgq.shopsystem.service.ProductsService;
import com.edgq.shopsystem.service.SaleService;
import com.edgq.shopsystem.service.TicketService;

import java.io.Serializable;
import java.util.ArrayList;
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

    @Inject
    private CustomerService customerService;
    @Inject
    private ProductsService productService;
    @Inject
    private SaleService saleService;
    @Inject
    private SessionBean session;
    @Inject
    private TicketService ticketService;
    @Inject
    private PayMethodService payMethodService;
    
    @Getter
    @Setter
    private Sale saleCarDB;
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
    
    @PostConstruct
    public void init() {
        findAllCustomer();
        tickets = ticketService.findAll();
        payMethods = payMethodService.findAll();
    }

    public void findAllCustomer() {
        try {
            customers = customerService.findAll();
            if (customers != null && !customers.isEmpty()) {
                customerIdSelected = customers.get(0).getId(); // Selecciona el primer cliente por defecto, si es necesario
            }
        } catch (Exception e) {
            customers = null;
        }
    }

    // Captura en tiempo real el value del input para 
    // buscar un producto por su codigo de barra
    public void setInputVarCode(ValueChangeEvent event) {
        varCodeInput = event.getNewValue().toString();
        System.err.println(varCodeInput);
    }

    public void findProductByVarCode() {
        Product p;
        p = productService.findProductByVarCode(varCodeInput);
        if (!p.equals(null)) {
            saleCar.add(p);
        }
        productService.productExtractIds(saleCar);
    }

    
    public void createSaleCarInitial() {
        if (customerIdSelected > 0 && ticketIdSelected > 0 && payMethodIdSelected > 0) {
            customerSelected.setId(customerIdSelected);
            ticketSelected.setId(ticketIdSelected);
            payMethodSelected.setId(payMethodIdSelected);
            System.out.println("Usuario con id: "+customerIdSelected);
            System.out.println("Ticket con id: "+ticketIdSelected);
            System.out.println("PayMethod con id: "+payMethodIdSelected);
        } else {
            System.err.println("No se ha seleccionado ningun Customer");
        }
        //saleService.save(new Sale(0, 0.0, PayMethod.E, new Date(), session.getUserInSession(), customerSelected, null));
    }


    public void trashSaleCar() {
        System.out.println("------------CLEAN SALE CAR-------------");
        saleCar = new ArrayList<>();
        varCodeInput = "";
        System.out.println("---------------------------------------");
    }

}
