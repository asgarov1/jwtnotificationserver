package com.javidasgarov.notificationserver;

import com.javidasgarov.notificationserver.domain.NotificationUser;
import com.javidasgarov.notificationserver.domain.Role;
import com.javidasgarov.notificationserver.domain.Status;
import com.javidasgarov.notificationserver.repository.NotificationUserRepository;
import com.javidasgarov.notificationserver.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class Bootstrap {
    private final NotificationUserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void createAdmin() {
        Role userRole = roleRepository.save(Role.ofUser());
        Role adminRole = roleRepository.save(Role.ofAdmin());

        userRepository.save(new NotificationUser(
                "admin@admin.com",
                passwordEncoder.encode("admin"),
                Status.ACTIVE,
                new Date(),
                List.of(userRole, adminRole)
        ));

        userRepository.save(new NotificationUser(
                "user@user.com",
                passwordEncoder.encode("user"),
                Status.ACTIVE,
                new Date(),
                List.of(userRole)
        ));
    }
}
