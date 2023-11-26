/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edgq.shopsystem.controller;

import com.edgq.shopsystem.design.MenuItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Getter;

/**
 *
 * @author edwin
 */
@Named
@ViewScoped
public class HomeBean implements Serializable {
    
    @Getter
    private List<MenuItem> menuItems = new ArrayList<>();
    
    @PostConstruct
    public void init(){
        menuItems.add(new MenuItem("Inicio","pi-home",null));
        menuItems.add(new MenuItem("Ventas","pi-shopping-cart",null));
        menuItems.add(new MenuItem("Compras","pi-shopping-bag",null));
        menuItems.add(new MenuItem("Clientes","pi-user-plus",null));
        menuItems.add(new MenuItem("Usuarios","pi-users",null));
    }
}
