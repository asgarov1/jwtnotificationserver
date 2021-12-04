package com.javidasgarov.notificationserver.controller.util;

import com.javidasgarov.notificationserver.security.jwt.JwtUser;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class ControllerUtil {
    public static Optional<JwtUser> getPrincipal() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .map(JwtUser.class::cast);
    }
}
