package com.edgq.shopsystem.controller;

import com.edgq.shopsystem.entity.Product;
import com.edgq.shopsystem.service.ProductsService;
import com.edgq.system.CrudMethods;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
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
public class ProductsBean implements Serializable {
    
    @Getter
    @Setter
    private List<Product> products;
    
    @Inject
    private ProductsService service;

    @PostConstruct
    public void init (){
        findAll();
    } 
    public void findAll() {
        products = service.findAll();
    }
    
    
}
