package com.edgq.shopsystem.service;

import com.edgq.shopsystem.entity.User;
import com.edgq.system.GenericPersistence;
import javax.ejb.Stateless;

/**
 *
 * @author edwin
 */
@Stateless
public class UserService extends GenericPersistence<User>{
    public UserService(){
        super(User.class);
    }
    
    public User findByEmail(String email) throws Exception{
        User user;
        try {
            user = (User) em.createQuery("SELECT u FROM User u WHERE( u.userName = :email)")
            .setParameter("email", email)
            .getResultList()
            .get(0);
        } catch (Exception e) {
            user = null;
        }
        return user;
    }
}
