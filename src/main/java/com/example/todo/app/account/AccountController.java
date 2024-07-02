package com.example.todo.app.account;

import com.example.todo.domain.model.account.Account;
import com.example.todo.domain.service.userdetails.SampleUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("account")
public class AccountController {

    @GetMapping
    public String view(@AuthenticationPrincipal SampleUserDetails userDetails, Model model) {
        Account account = userDetails.getAccount();
        model.addAttribute(account);
        return "account/view";
    }
}
