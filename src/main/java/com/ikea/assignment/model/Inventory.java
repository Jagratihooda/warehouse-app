package com.ikea.assignment.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Inventory {

    private long art_id;
    private String name;
    private long stock;

}
