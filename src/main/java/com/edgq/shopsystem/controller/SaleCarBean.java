package com.edgq.shopsystem.controller;

import com.edgq.shopsystem.entity.Customer;
import com.edgq.shopsystem.entity.Employee;
import com.edgq.shopsystem.entity.Product;
import com.edgq.shopsystem.entity.Sale;
import com.edgq.shopsystem.enums.PayMethod;
import com.edgq.shopsystem.enums.Ticket;
import com.edgq.shopsystem.service.CustomerService;
import com.edgq.shopsystem.service.ProductsService;
import com.edgq.shopsystem.service.SaleItemService;
import com.edgq.shopsystem.service.SaleService;
import com.edgq.shopsystem.tools.PayMethodDetail;
import com.edgq.shopsystem.tools.TicketDetail;

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

    @Inject
    private CustomerService customerService;
    @Inject
    private ProductsService productService;
    @Inject
    private SaleService saleService;
    @Inject
    private SessionBean session;
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
    private String paymethodINput;
    @Getter
    @Setter
    private List<Customer> customers;
    @Getter
    @Setter
    private Customer customerSelected = new Customer();
    @Getter
    private List<PayMethodDetail> payMethodDetails = new ArrayList<>();
    @Getter
    private List<TicketDetail> ticketDetails = new ArrayList<>();
    @Setter
    @Getter
    private PayMethodDetail payMethodSelected;
    @Getter
    @Setter
    private TicketDetail ticketDetail;

    @PostConstruct
    public void init() {
        findAllCustomer();
        payMethodDetails.add(new PayMethodDetail(1, PayMethod.E, "pi-wallet", "Efectivo"));
        payMethodDetails.add(new PayMethodDetail(2, PayMethod.D, "pi-money-bill", "Débito"));
        payMethodDetails.add(new PayMethodDetail(3, PayMethod.C, "pi-money-bill", "Crédito"));

        ticketDetails.add(new TicketDetail(1, Ticket.S, "pi-file-excel", "S/C"));
        ticketDetails.add(new TicketDetail(2, Ticket.I, "pi-print", "Impreso"));
        ticketDetails.add(new TicketDetail(3, Ticket.E, "pi-envelope", "Correo"));
    }

    public void findAllCustomer() {
        try {
            customers = customerService.findAll();
            if (customers != null && !customers.isEmpty()) {
                customerSelected = customers.get(0); // Selecciona el primer cliente por defecto, si es necesario
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
        //System.out.println(productService.findProductByVarCode(varCodeInput));
    }

    public void createSaleCarInitial() {
        if (customerSelected != null) {
            System.out.println(customerSelected.toString());
        } else {
            System.err.println("No se ha seleccionado ningun Customer");
        }
        //saleService.save(new Sale(0, 0.0, PayMethod.E, new Date(), session.getUserInSession(), customerSelected, null));
    }
//    public void findProductByVarCode(){
//        Product p;
//        p = productService.findProductByVarCode(varCodeInput);
//        if(!p.equals(null)){
//            saleCar.add(p);
//        }
//        productService.productExtractIds(saleCar);
//        //System.out.println(productService.findProductByVarCode(varCodeInput));
//    }

    public void trashSaleCar() {
        System.out.println("------------CLEAN SALE CAR-------------");
        saleCar = new ArrayList<>();
        varCodeInput = "";
        System.out.println("---------------------------------------");
    }

}
