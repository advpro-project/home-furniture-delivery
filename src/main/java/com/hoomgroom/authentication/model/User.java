package com.hoomgroom.authentication.model;

import com.hoomgroom.authentication.enums.Gender;
import com.hoomgroom.authentication.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class User {
    private String fullName;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String username;
    private String email;
    private String password;
    private Role role;
    private double walletBalance;
}
