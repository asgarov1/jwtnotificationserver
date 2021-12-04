package com.javidasgarov.notificationserver.service;

import com.javidasgarov.notificationserver.domain.Message;
import com.javidasgarov.notificationserver.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public void save(Message message) {
        messageRepository.save(message);
    }

    public List<Message> findAllMessagesForUser(String username) {
        return messageRepository.findAllByReceiverUsername(username);
    }
}
