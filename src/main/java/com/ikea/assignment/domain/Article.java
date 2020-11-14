package com.ikea.assignment.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
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

    @Column(name = "name")
    private String name;

    @Column(name = "stock")
    private long stock;

    @OneToMany(mappedBy = "article")
    @JsonBackReference
    private Set<ProductArticle> articleProduct;

}
