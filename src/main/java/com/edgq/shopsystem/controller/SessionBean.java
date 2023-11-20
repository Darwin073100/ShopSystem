package com.edgq.shopsystem.controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author edwin
 */
@Named
@SessionScoped
public class SessionBean implements Serializable{
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String email;
    
    public String login(){
        return "home.xhtml?faces-redirect=true";
    }
    
}
