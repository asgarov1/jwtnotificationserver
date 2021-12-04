package com.javidasgarov.notificationserver.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class SignupRequestDTO {

    public static final int MIN_LENGTH = 8;

    @Email
    private String email;

    @Size(min = MIN_LENGTH, message = "Password must have minimum length of " + MIN_LENGTH)
    private String password;
}
