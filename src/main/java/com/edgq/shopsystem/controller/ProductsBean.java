package com.edgq.shopsystem.controller;

import com.edgq.shopsystem.entity.Product;
import com.edgq.shopsystem.service.ProductsService;
import com.edgq.shopsystem.tools.FacesUtils;
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
    @Getter
    @Setter
    private Product product;
    @Getter
    @Setter
    private Product productUpdate;
    @Getter
    @Setter
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
        try {
            this.product.setActive(true);
            this.service.save(product);
            products.add(product);
            FacesUtils.messageInfo("Producto registrado", "Producto registrado");
        } catch (Exception e) {
            //System.err.println(e);
            FacesUtils.messageError("No se pudo registrar el producto", "No se pudo registrar el producto");
        }
    }
    
    public void updated(){
        System.out.println(productUpdate);
        try {
            this.service.update(productUpdate);
            findAll();
            FacesUtils.messageInfo("Producto modificado", "Producto modificado");
        } catch (Exception e) {
            FacesUtils.messageError("No se pudo modificar", "No se pudo modificar");
        }
    }
    
    public void fieldsUpdate(Product p){
        productUpdate = p;
    }
    
    public void fieldsDelete(Product p){
        productDelete = p;
    }
    
    public void delete(){
        boolean isDelete = this.service.deleteProduct(productDelete);
        if(isDelete){
            this.products.remove(productDelete);
            FacesUtils.messageInfo("Eliminado", "¡El Producto se ha eliminado con exito!");
        } else {
            FacesUtils.messageError("Error", "¡No pudimos eliminar el producto!");
        }
    }
    
}
