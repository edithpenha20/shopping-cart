package com.ecommerce.cart.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ItemDto {

    private Long productId;
    private int quantity;
    private Long bagId;

}
