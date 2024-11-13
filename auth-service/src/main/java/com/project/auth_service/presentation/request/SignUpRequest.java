package com.project.auth_service.presentation.request;

import com.project.auth_service.application.dto.SignUpDto;
import com.project.auth_service.domain.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SignUpRequest(
                            @NotNull(message = "email cannot be null")
                            @Size(min =2, message = "Email not be less than two characters")
                            @Email
                            String email,

                            @NotNull(message = "Password cannot be null")
                            @Size(min = 2, message = "Password not be less than two characters")
                            String password,

                            @NotNull(message = "Name cannot be null")
                            @Size(min = 2, message = "Name not be less than two characters")
                            String passwordCheck,

                            @NotNull(message = "Name cannot be null")
                            @Size(min = 2, message = "Name not be less than two characters")
                            String nickName,


                            @NotNull(message = "phoneNumber cannot be null")
                            @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$",message = "핸드폰 번호의 양식과 맞지 않습니다. 01x-xxxx-xxxx")
                            String phoneNumber,

                            UserRole userRole) {
    public SignUpDto toDto() {
        return SignUpDto.create(
                this.email,
                this.password,
                this.passwordCheck,
                this.nickName,
                this.phoneNumber,
                this.userRole
        );
    }

}
