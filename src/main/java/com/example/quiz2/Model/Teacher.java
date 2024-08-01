package com.example.quiz2.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Teacher {

    @NotNull(message = "id should be not null")
    private int id;

    @NotEmpty(message = "name should be not empty")
    private String name;

    @NotNull(message = "salary should be not null")
    private int salary;

}
