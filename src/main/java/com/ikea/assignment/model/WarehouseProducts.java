package com.ikea.assignment.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class WarehouseProducts {
    private String name;
    private BigDecimal price;
    private List<WarehouseProductArticle> contain_articles;

}
