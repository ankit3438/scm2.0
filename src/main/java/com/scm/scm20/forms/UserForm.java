package com.scm.scm20.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForm {

    @NotBlank(message="uername is required")
    private String name;
    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message="Password is required")
    @Size(min=6,  message="Min 6 character required")
    private String password;
    @NotBlank(message = "About is required")
    private String about;
    @NotBlank(message = "Phone number is required")
    @Size(min=8,max=12,message = "Invalid phone number")
    private String phoneNumber;

}
