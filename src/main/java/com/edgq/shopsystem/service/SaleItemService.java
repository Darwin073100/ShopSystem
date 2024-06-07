package com.edgq.shopsystem.service;

import com.edgq.shopsystem.entity.SaleItem;
import com.edgq.system.GenericPersistence;

/**
 *
 * @author edwin
 */
public class SaleItemService extends GenericPersistence<SaleItem> {
    public SaleItemService(){
        super(SaleItem.class);
    } 
}
