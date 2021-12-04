package com.javidasgarov.notificationserver.controller;

import com.javidasgarov.notificationserver.security.jwt.JwtUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String hello(@AuthenticationPrincipal JwtUser user) {
        return "Hello " + user.getUsername();
    }
}
