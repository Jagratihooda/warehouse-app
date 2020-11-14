package com.ikea.assignment.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WarehouseProductArticle {
    private long art_id;
    private long amount_of;
}
