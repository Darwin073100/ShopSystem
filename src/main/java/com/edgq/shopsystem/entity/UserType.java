package com.edgq.shopsystem.entity;

import com.edgq.shopsystem.enums.Type;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
@Table(name = "user_type")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "type", nullable = false, length = 100)
    @Enumerated(EnumType.STRING)
    private Type type;
    @Column(name = "description", length = 250, nullable = false)
    private String description;
    @Temporal(TemporalType.DATE)
    @Column(name = "create_at", nullable = false)
    private Date createAt;
    @OneToOne(mappedBy = "userType")
    private User user;
    
}
