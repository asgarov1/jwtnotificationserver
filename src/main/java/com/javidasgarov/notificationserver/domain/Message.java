package com.javidasgarov.notificationserver.domain;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;

@Entity
@Data
public class Message extends AbstractPersistable<Long> {
    private String content;
    private String senderUsername;
    private String receiverUsername;
}
