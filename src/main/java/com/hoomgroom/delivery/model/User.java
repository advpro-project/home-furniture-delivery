package com.hoomgroom.delivery.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import com.hoomgroom.delivery.enums.Gender;
import com.hoomgroom.delivery.enums.Role;
import jakarta.persistence.*;
import lombok.Generated;

@Entity
@Getter @Setter
@SuppressWarnings("unused")
@Table(name = "user")
public class User {
    private String fullName;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String username;
    
    @Id
    private String email;

    private String password;
    private Role role;
    private double walletBalance;
}
