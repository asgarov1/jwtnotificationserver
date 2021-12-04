package com.javidasgarov.notificationserver.controller;

import com.javidasgarov.notificationserver.domain.Message;
import com.javidasgarov.notificationserver.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@AllArgsConstructor
@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public HttpStatus saveMessageToServer(Message message) {
        try {
            messageService.save(message);
            return NO_CONTENT;
        } catch (DataAccessException e) {
            return INTERNAL_SERVER_ERROR;
        }
    }
}
