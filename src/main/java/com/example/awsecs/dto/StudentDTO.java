package com.example.awsecs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private Integer id;
    @Pattern(regexp = "([a-zA-Z0-9]{4}$)")
    private String name;
    @Range(min = 10, max = 50)
    private Integer age;
    private Float gpa;
    private String address;

}
