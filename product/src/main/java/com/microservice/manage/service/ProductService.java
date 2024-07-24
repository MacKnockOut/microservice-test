package com.microservice.manage.service;

import com.microservice.manage.dto.ProductRequest;
import com.microservice.manage.dto.ProductResponse;
import com.microservice.manage.model.Product;
import com.microservice.manage.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    public void creatProduct(ProductRequest productRequest) {
        Product product = Product.builder().name(productRequest.getName()).
                price(productRequest.getPrice()).
                descripitions(productRequest.getDescripitions()).build();
        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return  products.stream().map(product -> mapToProdcutResponse(product)).toList();
    }

    private ProductResponse mapToProdcutResponse(Product product) {
        return ProductResponse.builder().
                id(product.getId())
                .name(product.getName())
                .descripitions(product.getDescripitions())
                .price(product.getPrice()).build();
    }

}
