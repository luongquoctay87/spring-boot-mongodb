package com.sample.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProductUpdateRequest implements Serializable {
    @NotBlank(message = "Id must be not blank")
    private String id;

    @NotBlank(message = "name must be not blank")
    private String name;

    @Min(value = 1, message = "price must be greater than 1")
    private Double price;

    private String description;

    private Boolean displayed;
}
