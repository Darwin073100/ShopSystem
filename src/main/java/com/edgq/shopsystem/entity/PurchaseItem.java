package com.edgq.shopsystem.entity;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author edwin
 */
@Data
@Entity
@Table(name = "purchase_item")
public class PurchaseItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(insertable = false, updatable = false, referencedColumnName = "id")
    private Purchase purchase;
    @OneToOne
    @JoinColumn(insertable = false, updatable = false, referencedColumnName = "id")
    private Product product;
    private Integer quantity;
    private Double total;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.purchase);
        hash = 71 * hash + Objects.hashCode(this.product);
        hash = 71 * hash + Objects.hashCode(this.quantity);
        hash = 71 * hash + Objects.hashCode(this.total);
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
        final PurchaseItem other = (PurchaseItem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.purchase, other.purchase)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        if (!Objects.equals(this.quantity, other.quantity)) {
            return false;
        }
        return Objects.equals(this.total, other.total);
    }

    @Override
    public String toString() {
        return "PurchaseItem{" + "id=" + id + ", purchase=" + purchase + ", product=" + product + ", quantity=" + quantity + ", total=" + total + '}';
    }
    
    
}
