/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edgq.shopsystem.service;

import com.edgq.shopsystem.entity.PayMethod;
import com.edgq.system.GenericPersistence;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author edwin
 */
@Stateless
public class PayMethodService extends GenericPersistence<PayMethod> {
    public PayMethodService(){
        super(PayMethod.class);
    }

    @Override
    public List<PayMethod> findAll() {
        try {
            return em.createQuery("SELECT p FROM PayMethod p", PayMethod.class)
                .getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    
}
