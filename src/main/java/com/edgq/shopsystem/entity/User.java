package com.edgq.shopsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "user_name", length = 50, nullable = false, unique = true)
    private String userName;
    @Column(name = "user_password", length = 100, nullable = false)
    private String userPassword;
    @OneToOne
    @JoinColumn(name = "user_type_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UserType userType;
    
    @OneToOne(mappedBy = "user")
    private Employee employee;

}
