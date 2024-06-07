/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edgq.shopsystem.service;

import com.edgq.shopsystem.entity.Customer;
import com.edgq.system.GenericPersistence;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author edwin
 */
@Stateless
public class CustomerService extends GenericPersistence<Customer>{
    public CustomerService(){
        super(Customer.class);
    }

    @Override
    public List<Customer> findAll() {
        try {
            return em.createQuery("SELECT c FROM Customer c", Customer.class)
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    
    
}
