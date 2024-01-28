package com.spb.springbootpractice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor // creates a constructor for all required fields like @NotNull or final ...
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Last name is mandatory")
    @Column(nullable = false)
    private String lastName;

    @Column(unique = true)
    @Email(message = "Email should be valid")
    //@Pattern()
    private String email;

    @NotNull(message = "Grade is mandatory")
    @Column(nullable = false)
    private Integer grade;

    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt = LocalDateTime.now();
}
