package com.edgq.shopsystem.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Edwin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "branch")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "administration_id", referencedColumnName = "id",nullable = false, insertable = false, updatable = false)
    private Administration administration;
    @Column(name = "capital_mount", nullable = false)
    private Double capitalMount;
    @Column(name = "phone_number", nullable = true, length = 50)
    private String phoneNumber;
    @Column(name = "email", unique = true, length = 200, nullable = true)
    private String email;
    @Column(name = "state", nullable = false, length = 100)
    private String state;
    @Column(name = "city", nullable = false, length = 250)
    private String city;
    @Column(name = "municipality", nullable = false, length = 100)
    private String municipality;
    @Column(name = "street", nullable = true)
    private String street;
    @Column(name = "out_number", nullable = true, length = 10)
    private String outNumber;
    @Column(name = "in_number", nullable = true, length = 10)
    private String inNumber;
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    @OneToMany(mappedBy = "branch")
    private List<Purchase> purchases;
        
    @OneToMany(mappedBy = "branch")
    private List<Product> products;
    
    @OneToMany(mappedBy = "branch")
    private List<Register> registers;
    
    @OneToMany(mappedBy = "branch")
    private List<Employee> employees;
    
    @OneToMany(mappedBy = "branch")
    private List<Customer> customers;
}
