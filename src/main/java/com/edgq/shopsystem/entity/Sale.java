package com.edgq.shopsystem.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.edgq.shopsystem.entity.Ticket;
import com.edgq.shopsystem.entity.PayMethod;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author edwin
 */

@Data
@Entity
@Table(name = "sale")
@NoArgsConstructor
@AllArgsConstructor
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double total;
    @Temporal(TemporalType.DATE)
    private Date date;
    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;
    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;
    @OneToOne
    @JoinColumn(name="ticket_id", referencedColumnName = "id")
    private Ticket ticket;
    @OneToOne
    @JoinColumn(name = "paymethod_id", referencedColumnName = "id")
    private PayMethod payMethod; 
    
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<SaleItem> items;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.id);
        hash = 61 * hash + Objects.hashCode(this.total);
        hash = 61 * hash + Objects.hashCode(this.date);
        hash = 61 * hash + Objects.hashCode(this.employee);
        hash = 61 * hash + Objects.hashCode(this.customer);
        hash = 61 * hash + Objects.hashCode(this.ticket);
        hash = 61 * hash + Objects.hashCode(this.payMethod);
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
        final Sale other = (Sale) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.total, other.total)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.employee, other.employee)) {
            return false;
        }
        if (!Objects.equals(this.customer, other.customer)) {
            return false;
        }
        if (!Objects.equals(this.ticket, other.ticket)) {
            return false;
        }
        return Objects.equals(this.payMethod, other.payMethod);
    }

    @Override
    public String toString() {
        return "Sale{" + "id=" + id + ", total=" + total + ", date=" + date + ", employee=" + employee + ", customer=" + customer + ", ticked=" + ticket + ", pay_method=" + payMethod + '}';
    }

    
    
}
