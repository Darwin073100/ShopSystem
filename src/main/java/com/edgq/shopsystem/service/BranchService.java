/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edgq.shopsystem.service;

import com.edgq.shopsystem.entity.Branch;
import com.edgq.system.GenericPersistence;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Edwin
 */
@Stateless
public class BranchService extends GenericPersistence<Branch>{
    public BranchService(){
        super(Branch.class);
    }
    
    @Override
    public List<Branch> findAll() {
        List<Branch> branches;
        try{
            branches = (List<Branch>) em.createQuery("SELECT b FROM Branch b", Branch.class)
                .getResultList();
        } catch(Exception e){
            branches = null;
        }
        return branches;
    }
}
