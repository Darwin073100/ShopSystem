package com.edgq.shopsystem.design;

import lombok.Data;

/**
 *
 * @author edwin
 */
@Data
public class MenuItem {
    private String value;
    private String icon;
    private String action;

    public MenuItem(String value, String icon, String action) {
        this.value = value;
        this.icon = icon;
        this.action = action;
    }
    
    
}
