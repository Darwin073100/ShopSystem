/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edgq.shopsystem.service;

import com.edgq.shopsystem.entity.Ticket;
import com.edgq.system.GenericPersistence;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author edwin
 */
@Stateless
public class TicketService extends GenericPersistence<Ticket>{
    public TicketService(){
        super(Ticket.class);
    }

    @Override
    public List<Ticket> findAll() {
        try {
            return em.createQuery("SELECT c FROM Ticket c", Ticket.class)
                .getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    
}
