package com.edgq.shopsystem.service;

import com.edgq.shopsystem.entity.Employee;
import com.edgq.system.GenericPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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

    @Override
    public List<Employee> findAll() {
        List<Employee> employees;
        try{
            employees = (List<Employee>) em.createQuery("SELECT e FROM Employee e", Employee.class)
                .getResultList();
        } catch(Exception e){
            employees = null;
        }
        return employees;
    }
    
        public boolean saveNativeSql(Employee employee) {
        String insertQuery = "INSERT INTO employee(`user_id`, `branch_id`, `name`, `surname`, `salary`, `birthday`, `age`, `phone_number`, `email`, `address`, `active`) VALUES(?,?,?,?,?,?,?,?,?,?,?);";
        try {
            em.createNativeQuery(insertQuery)
                    .setParameter(1, null)
                    .setParameter(2, employee.getBranch().getId())
                    .setParameter(3, employee.getName())
                    .setParameter(4, employee.getSurname())
                    .setParameter(5, employee.getSalary())
                    .setParameter(6, employee.getBirthday())
                    .setParameter(7, employee.getAge())
                    .setParameter(8, employee.getPhoneNumber())
                    .setParameter(9, employee.getEmail())
                    .setParameter(10, employee.getAddress())
                    .setParameter(11, Boolean.TRUE)
                    .executeUpdate();
            
//            saleItemId = ((Number) em.createNativeQuery("SELECT LAST_INSERT_ID();")
//                    .getSingleResult()).intValue();
             return true;
        } catch (Exception e) {
            System.err.println(e);
        }
        return false;
    } 
    
    public Employee findByEmail(String email) throws Exception{
        Employee employee;
        try {
            employee = (Employee) em.createQuery("SELECT E FROM Employee E INNER JOIN User u ON(e.email=:email)")
            .setParameter("email", email)
            .getResultList()
            .get(0);
        } catch (Exception e) {
            employee = null;
        }
        return employee;
    }
    
    public Employee findByUserName(String userName)throws Exception{
        Employee employee;
        try{
            employee = (Employee) em.createQuery("SELECT E FROM Employee E INNER JOIN User u ON(u.userName = :userName)")
                .setParameter("userName", userName)
                .getResultList()
                .get(0);
        } catch(Exception e){
            employee = null;
        }
        return employee;
    }
    
    public List<Employee> findProductsBySize(int size, List<Employee> employees){
        if(size > employees.size()){
            Random rand = new Random();
            
            List<Employee> randomList = new ArrayList<>();
            for(int i = 0; i < size; i++){
                int randomIndex = rand.nextInt(employees.size());
                randomList.add(employees.get(randomIndex));
            }
            
            return randomList;
        } else {
            return new ArrayList<>(employees.subList(0, size));
        }
    }
    
    /**
     *
     * @param entity
     * @throws Exception
     */
    public boolean deleteQuery(Employee entity){
        try {
            em.createNativeQuery("DELETE FROM employee WHERE(id = ?);")
                    .setParameter(1, entity.getId())
                    .executeUpdate();
            return true;
        } catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
}