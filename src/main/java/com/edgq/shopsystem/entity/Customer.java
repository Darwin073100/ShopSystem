package com.edgq.shopsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Customer {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "branch_id", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
    private Branch branch;
    @Column(name = "name",length = 100, nullable = false)
    private String name;
    @Column(name = "surname", length = 150, nullable = false)
    private String surname;
    @Temporal(TemporalType.DATE)
    @Column(name = "birthday", nullable = false)
    private Date birthday;
    @Column(name = "age", nullable = true)
    private Integer age;
    @Column(name = "phone_number", length = 20, nullable = true, unique = true)
    private String phoneNumber;
    @Column(name = "email", unique = true, nullable = true, length = 250 )
    private String email;
    @Column(name = "address", nullable = true)
    private String address;
    @Column(name = "no_sales", nullable = true)
    private Integer noSales;
    @Column(name = "active", nullable = false)
    private Boolean active;
    
    @OneToOne(mappedBy = "customer")
    private Sale sale;
    
}
