package com.javidasgarov.notificationserver.controller;

import com.javidasgarov.notificationserver.domain.NotificationUser;
import com.javidasgarov.notificationserver.dto.AuthenticationRequestDTO;
import com.javidasgarov.notificationserver.dto.AuthenticationResponseDTO;
import com.javidasgarov.notificationserver.dto.SignupRequestDTO;
import com.javidasgarov.notificationserver.security.jwt.JwtTokenProvider;
import com.javidasgarov.notificationserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody AuthenticationRequestDTO dto) {
        try {
            String username = dto.username();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, dto.password()));
            NotificationUser user = userService.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " not found."));

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            return ResponseEntity.ok(new AuthenticationResponseDTO(username, token));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupRequestDTO dto) {
        if (userService.findByUsername(dto.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("username " + dto.getEmail() + " is not available.");
        }

        userService.register(new NotificationUser(dto.getEmail(), dto.getPassword()));
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
