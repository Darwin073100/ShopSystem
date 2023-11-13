package com.edgq.shopsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author edwin
 */

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double price;
    private Integer stock;
    @Column(length = 50)
    private String unit;
    @Column(name = "expiration_date")
    @Temporal(TemporalType.DATE)
    private Date expirationDate;
    private Boolean active;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.name);
        hash = 71 * hash + Objects.hashCode(this.price);
        hash = 71 * hash + Objects.hashCode(this.stock);
        hash = 71 * hash + Objects.hashCode(this.unit);
        hash = 71 * hash + Objects.hashCode(this.expirationDate);
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
        final Product other = (Product) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.unit, other.unit)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        if (!Objects.equals(this.stock, other.stock)) {
            return false;
        }
        if (!Objects.equals(this.expirationDate, other.expirationDate)) {
            return false;
        }
        return Objects.equals(this.active, other.active);
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", price=" + price + ", stock=" + stock + ", unit=" + unit + ", expirationDate=" + expirationDate + ", active=" + active + '}';
    }
    
    
}
