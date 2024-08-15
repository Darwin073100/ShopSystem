package com.edgq.shopsystem.controller;

import com.edgq.shopsystem.entity.Branch;
import com.edgq.shopsystem.entity.Employee;
import com.edgq.shopsystem.service.BranchService;
import com.edgq.shopsystem.service.EmployeeService;
import com.edgq.shopsystem.service.UserService;
import com.edgq.shopsystem.tools.FacesUtils;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Edwin
 */
@Named
@ViewScoped
public class UserBean implements Serializable {
    @Getter
    @Setter
    private List<Employee> employees;
    @Getter
    @Setter
    private Employee selectedEmployee;
    @Getter
    @Setter
    private Employee newEmployee;
    @Getter
    @Setter
    private List<Employee> selectedEmployes;
    @Getter
    @Setter
    private Branch selectedBranch;
    @Getter
    @Setter
    private int selectedBranchId;
    @Getter
    private List<Branch> branches;
    
    @Getter
    @Setter
    private String passwordCreated;
    
    @Inject
    private EmployeeService employeeService;
    @Inject
    private UserService userService;
    @Inject
    private BranchService branchService;
    
    @PostConstruct
    public void init(){
        this.employees = employeeService.findAll();
        this.selectedEmployes = new ArrayList<>();
        this.selectedBranch = new Branch();
        this.newEmployee = new Employee();
        setBranches();
    }
    
    public void savedEmployee(){
        try {
            selectedBranch = (branchService.findById(selectedBranchId));
            newEmployee.setBranch(selectedBranch);
            newEmployee.setActive(true);
            employeeService.saveNativeSql(newEmployee);
            employees.add(newEmployee);
            FacesUtils.messageInfo("Correcto", "Empleado registrado");
            newEmployee = new Employee();
        } catch (Exception e) {
            FacesUtils.messageError("Error", "Ha ocurrido un error en el registro");
        }
    }
    
    public void delete (){
        System.out.println("Delete bean 1");
        try {
            boolean isDeleteEmployee = employeeService.deleteQuery(selectedEmployee);
            System.out.println("Delete bean 2");
            if(isDeleteEmployee){
                System.out.println("Delete bean 3");
                this.employees = employeeService.findAll();
                FacesUtils.messageInfo("Correcto", "Empleado eliminado");
            } else {
                System.out.println("Delete bean 4");
                FacesUtils.messageError("Error", "Ourrió un error");
            }
        } catch (Exception e) {
            System.out.println("Delete bean 5");
            FacesUtils.messageError("Error", "Ourrió un error");
        }
    }
    
    public void selectionEmployee(Employee employee){
        selectedEmployee = employee;
    }
    
    public void openNew(){
        this.selectedEmployee = new Employee();
    }
    
    public boolean hasSelectedEmployee(){
        return this.selectedEmployes != null && !this.selectedEmployes.isEmpty();
    }
    
    public int currentYear(){
        return LocalDate.now().getYear();
    }
    
    public void setBranches(){
        try {
            branches = branchService.findAll();
        } catch (Exception e) {
        }
    }
}
