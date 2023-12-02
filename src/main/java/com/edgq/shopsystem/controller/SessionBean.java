package com.edgq.shopsystem.controller;

import com.edgq.shopsystem.entity.Employee;
import com.edgq.shopsystem.entity.User;
import com.edgq.shopsystem.service.UserService;
import com.edgq.shopsystem.tools.FacesUtils;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
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
    private Employee userInSession = null;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String email;
    
    @Inject
    private UserService service;
    
    public String login(){
        Employee e = null;
        try {
            e = service.findEmployeeWithUserByEmail(email);
            if(e != null){
                if(password.equals(e.getUser().getUserPassword())){
                    System.out.println("SessionBean.......");
                    userInSession = e;
                    return "/pages/home.xhtml?faces-redirect=true";
                } else {
                    System.out.println("Contraseña incorrecta");
                    FacesUtils.messageError(INCORRECTS_DATA, null);
                }
            } else {
                System.out.println("El usuario es null");
                FacesUtils.messageError(INCORRECTS_DATA, null);
            }
        }catch (Exception ex) {
            System.out.println("Error--bean--");
            System.out.println(ex.getMessage());
            System.out.println(ex.getClass().getName());
        }
        return null;
    }
    
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }
    
    public boolean isLogged(){
        return userInSession != null ? true: false;
    }
    
}
