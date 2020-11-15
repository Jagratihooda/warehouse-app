package com.ikea.assignment.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Article {

    @Id
    @Column(name = "id", nullable = false)
    private long id;

    @Column
    private String name;

    @Column
    private long stock;

    @OneToMany(mappedBy = "article")
    @JsonBackReference
    private Set<ProductArticle> articleProduct;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdDate;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date updatedDate;

}
