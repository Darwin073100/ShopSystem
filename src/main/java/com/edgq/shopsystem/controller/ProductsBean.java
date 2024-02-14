package com.edgq.shopsystem.controller;

import com.edgq.shopsystem.entity.Product;
import com.edgq.shopsystem.service.ProductsService;
import com.edgq.shopsystem.tools.FacesUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
/**
 *
 * @author edwin
 */
@Named
@ViewScoped
public class ProductsBean implements Serializable {
    
    private List<Product> products;
    private Product product;
    private Product productUpdate;
    private Product productDelete;
    
    @Inject
    private ProductsService service;

    @PostConstruct
    public void init (){
        findAll();
        product = new Product();
    } 
    public void findAll() {
        products = service.findAll();
    }
    
    public void save() {
        product.setActive(Boolean.TRUE);
        product.setExpirationDate(new Date());
        product.setName("Product Test");
        System.out.println(product);
        
        try {
            this.service.save(product);
            FacesUtils.messageInfo(null, "Producto registrado");
        } catch (Exception e) {
            //System.err.println(e);
            FacesUtils.messageError(null, "No se pudo registrar el producto");
        }
    }
    
    public void updated(){
        System.out.println(productUpdate);
    }
    
    public void fieldsUpdate(Product p){
        productUpdate = p;
    }
    
    public void fieldsDelete(Product p){
        productDelete = p;
        System.out.println(productDelete);
    }
    
    public void delete(){
        boolean isDelete = this.service.deleteProduct(productDelete);
        if(isDelete){
            FacesUtils.messageInfo("Eliminado", "¡El Producto se ha eliminado con exito!");
        } else {
            FacesUtils.messageError("Error", "¡No pudimos eliminar el producto!");
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProductUpdate() {
        return productUpdate;
    }

    public void setProductUpdate(Product productUpdate) {
        this.productUpdate = productUpdate;
    }

    public Product getProductDelete() {
        return productDelete;
    }

    public void setProductDelete(Product productDelete) {
        this.productDelete = productDelete;
    }

    public ProductsService getService() {
        return service;
    }

    public void setService(ProductsService service) {
        this.service = service;
    }
    
    
}
