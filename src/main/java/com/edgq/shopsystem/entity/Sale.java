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
import javax.persistence.Column;
import javax.persistence.OneToMany;

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
@Table(name = "sale")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "sub_total", nullable = true)
    private Double subTotal;
    @Column(name = "iva", nullable = true)
    private Double iva;
    @Column(name = "pay_quantity", nullable = true)
    private Double payQuantity;
    @Column(name = "change_amount", nullable = true)
    private Double changeAmount;
    @Column(name = "total")
    private Double total;
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
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

    public Sale(Double subTotal, Double iva, Double payQuantity, Double changeAmount, Double total) {
        this.subTotal = subTotal;
        this.iva = iva;
        this.payQuantity = payQuantity;
        this.changeAmount = changeAmount;
        this.total = total;
    }
}
