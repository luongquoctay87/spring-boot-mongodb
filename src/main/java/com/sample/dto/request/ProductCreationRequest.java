package com.sample.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProductCreationRequest implements Serializable {

    @NotBlank(message = "name must be not blank")
    private String name;

    @Min(value = 1, message = "price must be not blank")
    private Double price;

    private String description;

    private Boolean displayed;
}
