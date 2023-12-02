package com.edgq.shopsystem.service;

import com.edgq.shopsystem.entity.Employee;
import com.edgq.system.GenericPersistence;
import javax.ejb.Stateless;

/**
 *
 * @author edwin
 */

@Stateless
public class EmployeeService extends GenericPersistence<Employee>{
    public EmployeeService(){
        super(Employee.class);
    }
    
    public Employee findByEmail(String email) throws Exception{
        Employee employee;
        try {
            employee = (Employee) em.createQuery("SELECT E FROM Employee E INNER JOIN User u ON(e.email=:email)")
            .setParameter("email", email)
            .getResultList()
            .get(0);
            System.out.println(employee);
        } catch (Exception e) {
            employee = null;
        }
        return employee;
    }
}