package com.edgq.shopsystem.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "administration")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Administration{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "enterprise", nullable = false, length = 250)
    private String enterprise;
    @Column(name = "capital_mount", nullable = false)
    private Double capitalMount;
    @Column(name = "business_line", nullable = false, length = 250)
    private String businessLine;
    @Column(name = "mission", nullable = true)
    private String mission;
    @Column(name = "vision", nullable = true)
    private String vision;
    @Column(name = "history", nullable = true)
    private String history;
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    @OneToMany(mappedBy = "administration")
    private List<Branch> branches;
}
