package com.javidasgarov.notificationserver.repository;

import com.javidasgarov.notificationserver.domain.NotificationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationUserRepository extends JpaRepository<NotificationUser, Long> {
    Optional<NotificationUser> findByUsername(String username);
}
