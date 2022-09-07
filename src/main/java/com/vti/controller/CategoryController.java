package com.vti.controller;

import com.vti.entity.Category;
import com.vti.service.ICategoryService;
import com.vti.service.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("categories")
public class CategoryController {

    @Autowired
    private ICategoryService service;

    // READ
    @GetMapping
    public String getAll(Model model) {
        List<Category> categories = service.getAll();
        model.addAttribute("listCategories", categories);
        return "categories";
    }

    // CREATE
    @GetMapping("add") // dẫn đến trang add để điền form
    public String add(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("pageTitle", "Add New Category");
        return "category-form";
    }

    // dùng chung cho Create and Update
    @PostMapping("save") // đầu vào là form ở trang add đã điền rồi save vào db
    public String save(@ModelAttribute Category category, RedirectAttributes ra) {
        service.save(category);
        ra.addFlashAttribute("message", "The category has been saved successfully!");
        return "redirect:/categories";
    }

    // UPDATE
    @GetMapping("edit/{id}") // truy cập vào trang edit từ id ở trang list
    public String edit(@PathVariable Integer id, Model model, RedirectAttributes ra) {
        try {
            Category category = service.getById(id);
            model.addAttribute("category", category);
            model.addAttribute("pageTitle", "Edit Category (ID: " + id + ")");
            return "category-form";
        } catch (NotFoundException e) {
            ra.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/categories";
        }
    }

    // DELETE
    @GetMapping("delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes ra) {
        try {
            service.deleteById(id);
            ra.addFlashAttribute("message", "The category ID " + id + " has been deleted.");
        } catch (NotFoundException e) {
            ra.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/categories";
    }


    // PHẦN CODE DÙNG API VÀ @RESTCONTROLLER
//    @GetMapping
//    public List<Category> getAll() {
//        return categoryRepo.findAll();
//    }
//
//    @PostMapping
//    public String save(@RequestBody Category category) {
//        categoryRepo.save(category);
//        return "Create success!";
//    }
//
//    @PutMapping
//    public String update(@RequestBody Category category) {
//        System.out.println(category);
//        categoryRepo.save(category);
//        return "Update success!";
//    }
//
//    @DeleteMapping("{id}")
//    public String delete(@PathVariable Integer id) {
//        Optional<Category> category = categoryRepo.findById(id);
//
//        if (category.isPresent() == false) {
//            return "Delete fail! Not found id: " + id;
//        }
//
//        categoryRepo.delete(category.get());
//        return "Delete success!";
//    }

//  Phải comment lại ko sẽ bị báo lỗi Mapping

//    @Autowired
//    private ModelMapper modelMapper;
//
//    @GetMapping // dùng ModelMapper để lấy data từ khoá ngoại
//    public List<CategoryDTO> getAllCategoryDTOs() {
//        List<Category> categories = categoryRepo.findAll();
//        return modelMapper.map(categories, new TypeToken<List<CategoryDTO>>(){}.getType());
//    }
}
