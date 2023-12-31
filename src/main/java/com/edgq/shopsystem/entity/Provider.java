package com.edgq.shopsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import lombok.Data;

/**
 *
 * @author edwin
 */
@Data
@Entity
@Table(name = "provider")
public class Provider {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name",length = 100)
    private String name;
    @Column(name = "surname",length = 150)
    private String surname;
    @Column(name = "age")
    private Integer age;
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;
    @Column(name = "email",unique = true)
    private String email;
    @Column(name = "company")
    private String company;
    @Column(name = "active")
    private Boolean active;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.name);
        hash = 71 * hash + Objects.hashCode(this.surname);
        hash = 71 * hash + Objects.hashCode(this.age);
        hash = 71 * hash + Objects.hashCode(this.phoneNumber);
        hash = 71 * hash + Objects.hashCode(this.email);
        hash = 71 * hash + Objects.hashCode(this.company);
        hash = 71 * hash + Objects.hashCode(this.active);
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
        final Provider other = (Provider) obj;
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
        if (!Objects.equals(this.company, other.company)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.age, other.age)) {
            return false;
        }
        return Objects.equals(this.active, other.active);
    }

    @Override
    public String toString() {
        return "Provider{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", age=" + age + ", phoneNumber=" + phoneNumber + ", email=" + email + ", company=" + company + ", active=" + active + '}';
    }
    
}
