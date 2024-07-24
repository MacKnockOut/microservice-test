package com.progarammingtechie.order_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequese {
    private List<OrderLineItemRequest> orderLineItemDtoList;
}
