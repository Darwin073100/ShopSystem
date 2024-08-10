package com.edgq.shopsystem.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Edwin
 */
@Data
@Entity
@Table(name = "register")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Register {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "branch_id", referencedColumnName = "id", insertable = false, updatable = false,nullable = false)
    private Branch branch;
    @Column(name = "initial_mount", nullable = false)
    private Double initialMount;
    @Column(name = "initial_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date initialDate;
    @Column(name = "close_mount", nullable = false)
    private Double closeMount;
    @Column(name = "close_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date closeDate;
    
    @OneToMany(mappedBy = "register")
    private List<Sale> sales;
}
