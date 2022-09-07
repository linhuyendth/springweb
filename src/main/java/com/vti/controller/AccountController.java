package com.vti.controller;

import com.vti.entity.Account;
import com.vti.service.IAccountService;
import com.vti.service.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("accounts") // path dùng chung cho các hàm dưới
public class AccountController {

    @Autowired // ko có này thì ko có constructor thì sẽ bị null
    private IAccountService service;

    // READ
    @GetMapping
    public String getAll(Model model) {
        List<Account> accounts = service.getAll();
        model.addAttribute("listAccounts", accounts);
        return "accounts";
    }

    // CREATE
    @GetMapping("add") // dẫn đến trang add để điền form
    public String add(Model model) {
        model.addAttribute("account", new Account());
        model.addAttribute("pageTitle", "Add New Account");
        return "account-form";
    }

    // dùng chung cho Create and Update
    @PostMapping("save") // đầu vào là form ở trang add đã điền rồi save vào db
    public String save(@ModelAttribute Account account, RedirectAttributes ra) {
        service.save(account);
        ra.addFlashAttribute("message", "The account has been saved successfully!");
        return "redirect:/accounts";
    }

    // UPDATE
    @GetMapping("edit/{id}") // truy cập vào trang edit từ id ở trang list
    public String edit(@PathVariable Integer id, Model model, RedirectAttributes ra) {
        try {
            Account account = service.getById(id);
            model.addAttribute("account", account);
            model.addAttribute("pageTitle", "Edit Account (ID: " + id + ")");
            return "account-form";
        } catch (NotFoundException e) {
            ra.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/accounts";
        }
    }

    // DELETE
    @GetMapping("delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes ra) {
        try {
            service.deleteById(id);
            ra.addFlashAttribute("message", "The account ID " + id + " has been deleted.");
        } catch (NotFoundException e) {
            ra.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/accounts";
    }


    // PHẦN CODE DÙNG API VÀ @RESTCONTROLLER
//    @GetMapping
//    public List<Account> getAll() {
//        return accountRepo.findAll();
//    }
//
//    @PostMapping // điền thông tin vào body rồi create qua API
//    public String save(@RequestBody Account account) {
//        accountRepo.save(account);
//        return "Create success!";
//    }
//
//    @PutMapping // điền thông tin vào body rồi update qua API
//    public String update(@RequestBody Account account) { // ở đây id null
//        System.out.println(account);
//        accountRepo.save(account);
//        return "Update success!";
//    }
//
//    @DeleteMapping("{id}")
//    public String delete(@PathVariable Integer id) {
//        Optional<Account> account = accountRepo.findById(id);
//
//        if (account.isPresent() == false) {
//            return "Delete fail! Not found id: " + id;
//        }
//
//        accountRepo.delete(account.get());
//        return "Delete success!";
//    }
}
