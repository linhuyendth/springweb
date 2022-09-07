package com.vti.dto;

import com.vti.entity.Category;
import lombok.Data;

@Data
public class ProductDTO {

    private Integer id;

    private String productName;

    private Integer price;

    private String categoryName;

//    @Data
//    static class Category {
//        private Integer id;
//        private String categoryName;
//    }
}
