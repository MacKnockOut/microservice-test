package com.example.inventory.service;

import com.example.inventory.dto.InventoryReponse;
import com.example.inventory.repository.InventoryRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRespository inventoryRespository;
    @Transactional(readOnly = true)
    public List<InventoryReponse> isInStock(List<String> skuCode) {
        return inventoryRespository.findBySkuCodeIn(skuCode).stream().map(inventory -> {
            return InventoryReponse.builder()
                    .skuCode(inventory.getSkuCode())
                    .isInStock(inventory.getQuantity() > 0)
                    .build();
        }).toList();
    }
}
