package com.gaaji.Gaaji.Eccomerce.dto;/*  gaajiCode
    99
    09/09/2024
    */

import lombok.Data;

@Data
public class OrderItemRequest {
    private Long productId;
    private int quantity;
}
