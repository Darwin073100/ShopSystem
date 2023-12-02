package com.edgq.shopsystem.service;

import com.edgq.shopsystem.entity.Employee;
import com.edgq.shopsystem.entity.User;
import com.edgq.system.GenericPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author edwin
 */
@Stateless
public class UserService extends GenericPersistence<User>{
    public UserService(){
        super(User.class);
    }
    
    @Inject
    private EmployeeService employeeService;
    
    public User findByUserName(String userName) throws Exception{
        User user;
        try {
            user = (User) em.createQuery("SELECT u FROM User u WHERE( u.userName = :userName)")
            .setParameter("userName", userName)
            .getResultList()
            .get(0);
        } catch (Exception e) {
            user = null;
        }
        return user;
    }
    
    public Employee findEmployeeWithUserByEmail(String email) throws Exception{
        return employeeService.findByEmail(email);
    }

}
