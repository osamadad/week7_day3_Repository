package com.tuwaiq.repository_usermanagmentsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Sorry your name can't be empty, please try again")
    @Size(min = 4, max = 10, message = "Sorry your name can't be less than 4 or longer than 10 characters, please try again")
    @Column(columnDefinition = "varchar(10) not null")
    private String name;
    @NotEmpty(message = "Sorry your username can't be empty, please try again")
    @Size(min = 4,max = 20, message = "Sorry your name can't be less than 4 or longer than 20 characters, please try again")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String userName;
    @NotEmpty(message = "Sorry your password can't be empty, please try again")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,16}$", message = "Sorry your password must have at least 1 capital letter, 1 small letter, and 1 number, please try again")
    @Column(columnDefinition = "varchar(16) not null")
    private String password;
    @NotEmpty(message = "Sorry your email can't be empty, please try again")
    @Email(message = "Sorry your email must be a valid email, please try again")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String email;
    @NotEmpty(message = "Sorry your role can't be empty, please try again")
    @Pattern(regexp = "user|admin", message = "Sorry your role can only be 'user' or 'admin', please try again")
    @Column(columnDefinition = "varchar(5) not null")
    private String role;
    @NotNull(message = "Sorry your age can't be empty, please try again")
    @Positive(message = "Sorry your age can't be negative, please try again")
    @Column(columnDefinition = "int not null")
    private Integer age;
}
