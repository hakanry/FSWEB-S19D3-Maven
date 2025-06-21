package com.workintech.s19d2.controller;

import com.workintech.s19d2.entity.Account;
import com.workintech.s19d2.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountServiceImpl accountService;

    @PostMapping
    public Account create(@RequestBody Account account) {
        return accountService.save(account);
    }

    @GetMapping
    public List<Account> getAll() {
        return accountService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        accountService.delete(id);
    }

    @GetMapping("/{id}")
    public Account getById(@PathVariable Long id) {
        return accountService.getById(id);
    }
}
