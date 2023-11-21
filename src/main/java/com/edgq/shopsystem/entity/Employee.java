package com.edgq.shopsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;
import lombok.Data;
/**
 *
 * @author edwin
 */
@Data
@Entity
@Table(name = "employee")
public class Employee {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", length = 100)
    private String name;
    @Column(name = "surname", length = 150)
    private String surname;
    @Column(name = "salary")
    private Double salary;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "age")
    private Integer age;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "address", nullable = true)
    private String address;
    @Column(name = "active")
    private Boolean active;
    
    @OneToOne
    @JoinColumn(columnDefinition = "id")
    private User user;
    
    @OneToOne(mappedBy = "employee")
    private Sale sale;
    
    @OneToOne(mappedBy = "employee")
    private Purchase purchase;
       
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.surname);
        hash = 97 * hash + Objects.hashCode(this.salary);
        hash = 97 * hash + Objects.hashCode(this.birthday);
        hash = 97 * hash + Objects.hashCode(this.age);
        hash = 97 * hash + Objects.hashCode(this.phoneNumber);
        hash = 97 * hash + Objects.hashCode(this.email);
        hash = 97 * hash + Objects.hashCode(this.address);
        hash = 97 * hash + Objects.hashCode(this.active);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.phoneNumber, other.phoneNumber)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.salary, other.salary)) {
            return false;
        }
        if (!Objects.equals(this.birthday, other.birthday)) {
            return false;
        }
        if (!Objects.equals(this.age, other.age)) {
            return false;
        }
        return Objects.equals(this.active, other.active);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", salary=" + salary + ", birthday=" + birthday + ", age=" + age + ", phoneNumber=" + phoneNumber + ", email=" + email + ", address=" + address + ", active=" + active + '}';
    }
    
}
