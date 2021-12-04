package com.javidasgarov.notificationserver.controller;

import com.javidasgarov.notificationserver.security.jwt.JwtUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.javidasgarov.notificationserver.controller.util.ControllerUtil.getPrincipal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String hello() {
        return "Hello " + getPrincipal().map(JwtUser::getUsername).orElse(" unknown");
    }
}
