package com.project.auth_service.application.service;

import com.project.auth_service.application.dto.LoginDto;
import com.project.auth_service.application.dto.SignUpDto;
import com.project.auth_service.application.dto.UserDto;
import com.project.auth_service.application.dto.UserProfileUpdateDto;
import com.project.auth_service.domain.model.Auth;
import com.project.auth_service.domain.model.UserRole;
import com.project.auth_service.domain.repository.AuthRepository;
import com.project.auth_service.presentation.request.LoginRequest;
import com.project.auth_service.presentation.response.AuthResponse;
import com.project.auth_service.presentation.response.UserResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    private final AuthRepository authRepository;

    private final SecretKey secretKey;

    @Value("${spring.jwt.access-expiration}")
    private Long accessExpiration;


    public AuthService(AuthRepository authRepository,
                       @Value("${spring.jwt.secret}") String secretKey , PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void signUp(SignUpDto request) {

        Auth auth = Auth.create(
                request.email(),
                passwordEncoder.encode(request.password()),
                request.nickName(),
                request.phoneNumber(),
                request.userRole()
        );

        authRepository.save(auth);
    }

    @Transactional
    public AuthResponse login(LoginDto request) {

        Auth user = authRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("회원이 존재하지 않습니다."));

        if(!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("비밀번호가 다름");
        }

        return createToken(user.getId(), user.getUserRole());

    }

    public AuthResponse createToken(Long userId, UserRole userRole) {
        return AuthResponse.of(Jwts.builder()
                .claim("userId", userId)
                .claim("role",userRole)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + accessExpiration))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact());
    }

    @Transactional
    public UserResponse getUser(Long userId, UserRole userRole) {
        Auth user = authRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저가 없습니다."));
        return UserResponse.of(user);

    }

    @Transactional
    public UserResponse getUser(Long userId) {
        Auth user = authRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저가 없습니다."));
        return UserResponse.of(user);

    }

    @Transactional
    public UserResponse updateUserProfile(Long userId, UserRole userRole, UserProfileUpdateDto request) {

        return authRepository.findById(userId).map(user -> {
            user.updateUserProfile(
                    request.email(),
                    request.nickName(),
                    request.phoneNumber()
            );
            return UserResponse.of(user);
        }).orElseThrow(() -> new RuntimeException("유저가 없습니다."));
    }

    @Transactional
    public void deleteUser(Long userId, UserRole userRole, String emailCheck) {


         authRepository.findById(userId).map(user -> {

             if (!user.getEmail().equals(emailCheck)) {
                 throw new RuntimeException("이메일이 일치하지 않습니다.");
             }

             user.delete();

             return UserResponse.of(user);
        }).orElseThrow(() -> new RuntimeException("유저가 없습니다."));
    }

}
