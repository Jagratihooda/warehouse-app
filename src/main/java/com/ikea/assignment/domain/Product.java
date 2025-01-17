package com.ikea.assignment.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ikea.assignment.enums.ProductStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @Column
    private String name;

    @Column
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @OneToMany(mappedBy = "product")
    @JsonBackReference
    private List<ProductArticle> productArticle;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdDate;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date updatedDate;
}
