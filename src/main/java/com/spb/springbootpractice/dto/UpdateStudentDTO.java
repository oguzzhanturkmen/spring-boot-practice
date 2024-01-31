package com.spb.springbootpractice.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UpdateStudentDTO {

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;


    @Email(message = "Email should be valid")
    private String email;


}
