package com.blogapp.blog.Payloads;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {


    private int user_id;

    @NotEmpty(message = "Username cannot be  empty")

    private String user_name;

    @Email(message = "Email is invalid !!")
    private String user_email;

    @NotNull(message = "Password Cannot be Empty")
    @Size(min = 4,max = 10,message = "password should be between 4 - 10 Character" )
    private String user_password;

    @NotNull(message = "Please write about you in one sentence")
    private String user_about;
}