package com.javidasgarov.notificationserver.security.jwt;

import com.javidasgarov.notificationserver.domain.NotificationUser;
import com.javidasgarov.notificationserver.domain.Role;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

import static com.javidasgarov.notificationserver.domain.Status.ACTIVE;

@UtilityClass
public class JwtUserFactory {

    public static JwtUser create(NotificationUser user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getLastUpdated(),
                getAuthorities(user),
                ACTIVE.equals(user.getStatus()));
    }

    private static List<SimpleGrantedAuthority> getAuthorities(NotificationUser user) {
        return user.getRoles().stream()
                .map(Role::getName)
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

}
