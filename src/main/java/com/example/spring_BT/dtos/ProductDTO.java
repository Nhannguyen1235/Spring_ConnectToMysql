package com.example.spring_BT.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    @JsonProperty("name")
    @NotEmpty(message = "Product name cannot be empty")
    private String nameProduct;
    @JsonProperty("description")
    @NotEmpty(message = "Product description cannot be empty")
    private String descriptionProduct;
    @JsonProperty("price")
    @NotNull(message = "Product price cannot be empty")
    @Positive
    private Double priceProduct;
}
