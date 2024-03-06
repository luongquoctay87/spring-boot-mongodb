package com.sample.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class ProductResponse implements Serializable {
    private String id;
    private String name;
    private Double price;
    private String description;
    private Boolean displayed;
}
