package com.vti.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {

    private Integer id;

    private String categoryName;

    private List<ProductDTO> products;

    @Data
    static class ProductDTO {
        private String productName;
        private Integer price;
    }
}
