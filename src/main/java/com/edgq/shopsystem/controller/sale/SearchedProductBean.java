package com.edgq.shopsystem.controller.sale;

import com.edgq.shopsystem.entity.Product;
import com.edgq.shopsystem.service.ProductsService;
import com.edgq.shopsystem.service.SaleItemService;
import java.io.Serializable;
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
 * @author lnx-edwin
 */
@Named
@ViewScoped
public class SearchedProductBean implements Serializable{
    @Inject
    private ProductsService productsService;
    
    @Getter
    @Setter
    private String productInputToSearch;

    @Getter
    @Setter
    private List<Product> productsInDialog;
    
    @PostConstruct
    public void init(){

    }
    
    public void filterProducts(){
        try {
            productsInDialog = productsService.findProductsByNameInApp(productInputToSearch);
            for(Product p: productsInDialog){
                System.out.println(p);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    // Captura en tiempo real el value del input para 
    // buscar un producto por su codigo de barra
    public void setInputVarCode(ValueChangeEvent event) {
        productInputToSearch = event.getNewValue().toString();
        filterProducts();
        System.err.println(productInputToSearch);
    }
}
