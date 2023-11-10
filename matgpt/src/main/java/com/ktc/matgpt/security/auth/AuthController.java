package com.ktc.matgpt.security.auth;

import com.ktc.matgpt.domain.user.entity.User;
import com.ktc.matgpt.security.dto.AuthDto;
import com.ktc.matgpt.security.dto.TokenDto;
import com.ktc.matgpt.domain.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<TokenDto.Response> signup(@RequestBody AuthDto.DefaultRequest defaultRequest) {
        authService.signup(defaultRequest);
        return ResponseEntity.ok(authService.login(defaultRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto.Response> login(@RequestBody AuthDto.DefaultRequest defaultRequest) {
        return ResponseEntity.ok(authService.login(defaultRequest));
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody TokenDto.Request requestDto, HttpServletRequest request) {
        authService.logout(requestDto, request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto.Response> reissue(@RequestBody TokenDto.Request requestDto, HttpServletRequest request) {
        return ResponseEntity.ok(authService.reissue(requestDto, request));
    }

}
