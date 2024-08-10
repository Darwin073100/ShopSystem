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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Entity
@Table(name = "product")
@EqualsAndHashCode
@ToString
public class Product{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "branch_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Branch branch;
    @Column(name = "name", unique = true, nullable = false, length = 250)
    private String name;
    @Column(name = "var_code", nullable = false, length = 100, unique = true)
    private String varCode;
    @Column(name = "in_price", nullable = false)
    private Double inPrice;
    @Column(name = "out_price", nullable = false)
    private Double outPrice;
    @Column(name = "wholesale_price", nullable = false)
    private Double wholesalePrice;
    @Column(name = "description", nullable = true)
    private String description;
    @Column(name = "stock", nullable = false)
    private Integer stock;
    @Column(name = "min_stock", nullable = false)
    private Integer minStock;
    @Column(name = "unit", nullable = false,length = 50)
    private String unit;
    @Column(name = "expiration_date", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date expirationDate;
    @Column(name = "active", nullable = false)
    private Boolean active;
    
    @OneToOne(mappedBy = "product")
    private SaleItem saleItem;
    
    @OneToOne(mappedBy = "product")
    private PurchaseItem purchaseItem;

}
