package com.example.todo.domain.service.account;

import com.example.todo.domain.model.account.Account;

public interface AccountSharedService {
    Account findByUsername(String username);
}
