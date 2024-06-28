package com.example.todo.domain.repository.account;

import com.example.todo.domain.model.account.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountRepository {
    Account findByUsername(String username);
}
