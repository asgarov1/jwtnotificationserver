package com.javidasgarov.notificationserver.service;

import com.javidasgarov.notificationserver.domain.NotificationUser;
import com.javidasgarov.notificationserver.domain.Role;
import com.javidasgarov.notificationserver.domain.Status;
import com.javidasgarov.notificationserver.repository.NotificationUserRepository;
import com.javidasgarov.notificationserver.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.javidasgarov.notificationserver.domain.Role.ROLE_USER;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {

    private final NotificationUserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void save(NotificationUser user) {
        userRepository.save(user);
    }

    public Optional<NotificationUser> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public NotificationUser register(NotificationUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Optional<Role> role = roleRepository.findByName(ROLE_USER);
        role.map(List::of).ifPresent(user::setRoles);

        user.setStatus(Status.ACTIVE);

        NotificationUser registeredUser = userRepository.save(user);

        log.info("IN register - user: {} successfully registered", registeredUser);

        return registeredUser;
    }

    public List<NotificationUser> getAll() {
        List<NotificationUser> result = userRepository.findAll();

        log.info("IN getAll - {} users found", result.size());

        return result;
    }

}
