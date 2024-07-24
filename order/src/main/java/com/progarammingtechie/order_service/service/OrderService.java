package com.progarammingtechie.order_service.service;

import com.progarammingtechie.order_service.dto.InventoryRequest;
import com.progarammingtechie.order_service.dto.OrderLineItemRequest;
import com.progarammingtechie.order_service.dto.OrderRequese;
import com.progarammingtechie.order_service.model.Order;
import com.progarammingtechie.order_service.model.OrderLineItems;
import com.progarammingtechie.order_service.repository.OrderResponsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderResponsitory orderResponsitory;
    private final WebClient webClient;
    public void placeOrder(OrderRequese orderRequese) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItemsList = orderRequese.getOrderLineItemDtoList()
                .stream().map(this::mapToDTO)
                .toList();
        order.setOrderLineItemsList(orderLineItemsList);

        List<String> skuCodes = orderRequese.getOrderLineItemDtoList().stream()
                .map(OrderLineItemRequest::getSkuCode).toList();

        // Check sản phẩm có còn trong kho không
        InventoryRequest [] result = webClient.get()
                .uri("http://localhost:8083/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryRequest[].class).block();
        if (result.length > 0 ) {
            orderResponsitory.save(order);
        } else {
            throw new IllegalArgumentException("Prodcut is not in stock, please try agian later");
        }
    }


    private OrderLineItems mapToDTO(OrderLineItemRequest orderLineItemDto) {
        OrderLineItems orderLineItem =  OrderLineItems.builder()
                .skuCode(orderLineItemDto.getSkuCode())
                .price(orderLineItemDto.getPrice())
                .quantity(orderLineItemDto.getQuantity()).build();

        return orderLineItem;
    }
}
