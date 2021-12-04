package com.javidasgarov.notificationserver.controller;

import com.javidasgarov.notificationserver.domain.Message;
import com.javidasgarov.notificationserver.security.jwt.JwtUser;
import com.javidasgarov.notificationserver.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final MessageService messageService;

    @GetMapping("/messages")
    public List<Message> receiveMessages(@AuthenticationPrincipal JwtUser user) {
        return messageService.findAllMessagesForUser(user.getUsername());
    }

    @GetMapping
    public String hello(@AuthenticationPrincipal JwtUser user) {
        return "Hello " + user.getUsername();
    }
}
