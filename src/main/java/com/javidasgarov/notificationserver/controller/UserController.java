package com.javidasgarov.notificationserver.controller;

import com.javidasgarov.notificationserver.domain.Message;
import com.javidasgarov.notificationserver.security.jwt.JwtUser;
import com.javidasgarov.notificationserver.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.javidasgarov.notificationserver.controller.util.ControllerUtil.getPrincipal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final MessageService messageService;

    @GetMapping("/messages")
    public List<Message> receiveMessages() {
        JwtUser jwtUser = getPrincipal().orElseThrow(() -> new BadCredentialsException("Unauthorized"));
        return messageService.findAllMessagesForUser(jwtUser.getUsername());
    }

    @GetMapping
    public String hello() {
        return "Hello " + getPrincipal().map(JwtUser::getUsername).orElse(" unknown");
    }
}
