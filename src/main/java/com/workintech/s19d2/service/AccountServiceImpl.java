package com.workintech.s19d2.service;

import com.workintech.s19d2.entity.Account;
import com.workintech.s19d2.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AccountServiceImpl {

    private AccountRepository accountRepository;

    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public void delete(Long id) {
        accountRepository.deleteById(id);
    }

    public Account getById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }
}
