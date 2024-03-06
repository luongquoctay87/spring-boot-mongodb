package com.sample.service;

import com.sample.dto.request.ProductCreationRequest;
import com.sample.dto.request.ProductUpdateRequest;
import com.sample.dto.response.ProductResponse;
import com.sample.exception.ResourceNotFoundException;
import com.sample.model.Product;
import com.sample.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
public record ProductService(ProductRepository productRepository) {

    /**
     * Add new product
     * @param req
     * @return
     */
    public String addProduct(ProductCreationRequest req) {
        log.info("Saving product ...");

        Product response = productRepository.save(Product.builder()
                .name(req.getName())
                .price(req.getPrice())
                .description(req.getDescription())
                .displayed(req.getDisplayed())
                .build());

        log.info("Product has saved successful");

        return response.getId();
    }

    /**
     * Update product
     * @param req
     */
    public void updateProduct(ProductUpdateRequest req) {
        log.info("Updating product ...");

        Product product = get(req.getId());

        if (StringUtils.hasLength(req.getName())) {
            product.setName(req.getName());
        }
        if (req.getPrice() != null && req.getPrice() > 0) {
            product.setPrice(req.getPrice());
        }
        if (StringUtils.hasLength(req.getDescription())) {
            product.setDescription(req.getDescription());
        }
        if (req.getDisplayed() != null) {
            product.setDisplayed(req.getDisplayed());
        }

        productRepository.save(product);

        log.info("Product has updated successful");
    }


    /**
     * Delete product
     * @param id
     */
    public void deleteProduct(String id) {
        log.info("Deleting product ...");
        productRepository.delete(get(id));
        log.info("Product has deleted successful");
    }

    /**
     * Get product by id
     * @param id
     * @return product
     */
    public ProductResponse getProduct(String id) {
        log.info("Getting product detail ...");

        Product product = get(id);

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .displayed(product.getDisplayed())
                .build();
    }

    /**
     * Get list of product
     *
     * @return product list
     */
    public List<ProductResponse> getProducts(boolean status) {
        log.info("Getting product list ...");

        List<Product> responses = productRepository.findByDisplayed(status);

        return responses.stream().map(product -> ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .displayed(product.getDisplayed())
                .build()
        ).toList();
    }

    /**
     * Search product by name
     * @param name
     * @return
     */
    public List<ProductResponse> searchProduct(String name) {
        log.info("Searching product list ...");

        List<Product> responses = productRepository.findByNameContaining(name);

        return responses.stream().filter(Product::getDisplayed).map(product -> ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .displayed(product.getDisplayed())
                .build()
        ).toList();
    }

    private Product get(String id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found product, id=" + id));
    }
}
