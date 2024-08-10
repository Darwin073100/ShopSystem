package com.edgq.shopsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
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
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "provider")
public class Provider {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name",length = 100, nullable = false)
    private String name;
    @Column(name = "surname",length = 150, nullable = false)
    private String surname;
    @Column(name = "age", nullable = true)
    private Integer age;
    @Column(name = "phone_number", length = 20, nullable = true)
    private String phoneNumber;
    @Column(name = "email",unique = true, nullable = true)
    private String email;
    @Column(name = "company", nullable = true)
    private String company;
    @Column(name = "active", nullable = false)
    private Boolean active;
    
}
