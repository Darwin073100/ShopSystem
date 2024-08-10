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
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 *
 * @author edwin
 */
@Data
@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Employee {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "branch_id", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
    private Branch branch; 
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    @Column(name = "surname", length = 150, nullable = false)
    private String surname;
    @Column(name = "salary", nullable = false)
    private Double salary;
    @Column(name = "birthday", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Column(name = "age", nullable = true)
    private Integer age;
    @Column(name = "phone_number", nullable = true, length = 20)
    private String phoneNumber;
    @Column(name = "email", unique = true, nullable = true)
    private String email;
    @Column(name = "address", nullable = true)
    private String address;
    @Column(name = "active", nullable = false)
    private Boolean active;
    
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true, insertable = false, updatable = false)
    private User user;
    
    @OneToOne(mappedBy = "employee")
    private Sale sale;
    
    @OneToOne(mappedBy = "employee")
    private Purchase purchase;
    
}
