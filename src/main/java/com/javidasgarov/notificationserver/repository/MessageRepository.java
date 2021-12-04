package com.javidasgarov.notificationserver.repository;

import com.javidasgarov.notificationserver.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByReceiverUsername(String username);
}
