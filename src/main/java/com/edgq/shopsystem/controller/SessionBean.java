package com.edgq.shopsystem.controller;

import com.edgq.shopsystem.entity.User;
import com.edgq.shopsystem.service.UserService;
import com.edgq.shopsystem.tools.FacesUtils;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
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
    private static final String INCORRECTS_DATA = "¡Email o contraseña incorrecta!";
    @Getter
    private User userInSession = null;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String email;
    
    @Inject
    private UserService service;
    
    public String login(){
        User u = null;
        try {
            u = service.findByEmail(email);
            if(u != null){
                if(email.equals(u.getUserName()) && password.equals(u.getUserPassword())){
                    userInSession = u;
                    return "/pages/home.xhtml?faces-redirect=true";
                } else {
                    System.out.println("Contraseña incorrecta");
                    FacesUtils.messageError(INCORRECTS_DATA, null);
                }
            } else {
                System.out.println("El usuario es null");
                FacesUtils.messageError(INCORRECTS_DATA, null);
            }
        }catch (Exception e) {
            System.out.println("Error--bean--");
            System.out.println(e.getMessage());
            System.out.println(e.getClass().getName());
        }
        return null;
    }
    
}
