package com.vti.controller;

import com.vti.entity.Category;
import com.vti.entity.Product;
import com.vti.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

//    @GetMapping
//    public List<ProductDTO> getAllProductDTOs() {
//        List<Product> products = productRepo.findAll();
//        return modelMapper.map(products, new TypeToken<List<ProductDTO>>(){}.getType());
//    }

    // READ
    @GetMapping
    public String getAll(Model model) {
        List<Product> products = productService.getAll();
        model.addAttribute("listProducts", products);
        return "products";
    }

    // CREATE
    @GetMapping("add") // dẫn đến trang add để điền form
    public String add(Model model) {
        List<Category> listCategories = categoryService.getAll();
        model.addAttribute("product", new Product());
        model.addAttribute("listCategories", listCategories);
        model.addAttribute("pageTitle", "Add New Product");
        return "product-form";
    }

    // dùng chung cho Create and Update
    @PostMapping("save") // đầu vào là form ở trang add đã điền rồi save vào db
    public String save(@ModelAttribute Product product, RedirectAttributes ra) {
        productService.save(product);
        ra.addFlashAttribute("message", "The product has been saved successfully!");
        return "redirect:/products";
    }

    // UPDATE
    @GetMapping("edit/{id}") // truy cập vào trang edit từ id ở trang list
    public String edit(@PathVariable Integer id, Model model, RedirectAttributes ra) {
        try {
            Product product = productService.getById(id);
            model.addAttribute("product", product);

            List<Category> listCategories = categoryService.getAll();
            model.addAttribute("listCategories", listCategories);

            model.addAttribute("pageTitle", "Edit Product (ID: " + id + ")");
            return "product-form";
        } catch (NotFoundException e) {
            ra.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/products";
        }
    }

    // DELETE
    @GetMapping("delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes ra) {
        try {
            productService.deleteById(id);
            ra.addFlashAttribute("message", "The product ID " + id + " has been deleted.");
        } catch (NotFoundException e) {
            ra.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/products";
    }


    // PHẦN CODE DÙNG API VÀ @RESTCONTROLLER
//    @GetMapping
//    public List<Product> getAll() {
//        return productRepo.findAll();
//    }
//
//    @PostMapping
//    public String save(@RequestBody Product product) {
//        productRepo.save(product);
//        return "Create success!";
//    }
//
//    @PutMapping
//    public String update(@RequestBody Product product) {
//        System.out.println(product);
//        productRepo.save(product);
//        return "Update success!";
//    }
//
//    @DeleteMapping("{id}")
//    public String delete(@PathVariable Integer id) {
//        Optional<Product> product = productRepo.findById(id);
//
//        if (product.isPresent() == false) {
//            return "Delete fail! Not found id: " + id;
//        }
//
//        productRepo.delete(product.get());
//        return "Delete success!";
//    }



}
