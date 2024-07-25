package com.project.auth_service.service;

import com.project.auth_service.dto.LoginRequest;
import com.project.auth_service.dto.RegisterRequest;
import com.project.auth_service.dto.UserResponse;
import com.project.auth_service.dto.UserTokenResponse;
import com.project.auth_service.exception.CustomException;
import com.project.auth_service.jwt.JwtUtil;
import com.project.auth_service.model.User;
import com.project.auth_service.model.UserCreatedEvent;
import com.project.auth_service.repository.UserRepository;
import com.project.auth_service.type.UserRole;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    // 회원가입
    @Transactional
    public UserResponse signUp(RegisterRequest request
                               ) throws UnsupportedEncodingException {

            // 이메일 중복확인
            isExistUserEmail(request.getEmail());

            // 패스워드 체크
            checkPassword(request.getPassword(), request.getPasswordCheck());


            User user =  User.builder()
                    .email(request.getEmail())
                    .password(bCryptPasswordEncoder.encode(request.getPassword()))
                    .userRole(UserRole.USER)
                    .build();

            kafkaTemplate.send("user_created", new UserCreatedEvent(user.getEmail(), request.getNickName(), request.getGender()));


        return UserResponse.fromEntity(userRepository.save(user));
    }


    // 로그인
    @Transactional
    public UserTokenResponse login(LoginRequest request) {

        //
        authenticate(request.getEmail(), request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        checkEncodePassword(request.getPassword(), userDetails.getPassword());

        // 로그인된 회원 닉네임
        User loginUser = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> CustomException.USER_NOT_FOUND);

        // 토큰 발행
        String token = jwtUtil.generateToken(userDetails);
        return UserTokenResponse.fromEntity(loginUser, token);
    }


    // 인증로직
    @Transactional
    private void authenticate(String email, String password) {

        //AuthenticationManager를 사용하여 사용자의 이메일과 비밀번호로 인증을 시도합니다.
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw CustomException.USER_NOT_FOUND; // DisabledException: 사용자가 비활성화되어 있을 때 발생하는 예외
        } catch (BadCredentialsException e) { // BadCredentialsException: 비밀번호가 일치하지 않을 때 발생하는 예외
            throw CustomException.PASSWORD_NOT_MATCH;
        }
    }


    @Transactional
    private void isExistUserEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw CustomException.ALREADY_USER_EXISTS;
        }
    }

    // db에 있는 비밀번호와 같은지
    private void checkEncodePassword(String rawPassword, String encodedPassword) {
        if (!bCryptPasswordEncoder.matches(rawPassword, encodedPassword)) {
            throw CustomException.PASSWORD_NOT_MATCH;
        }

    }


    private void checkPassword(String password, String passwordCheck) {
        if (!password.equals(passwordCheck)) {
            throw CustomException.PASSWORD_NOT_MATCH;
        }
    }
}
