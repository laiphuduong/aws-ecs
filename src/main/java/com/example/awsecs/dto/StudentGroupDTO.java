package com.example.awsecs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentGroupDTO {
    private String studentName;
    private String groupName;

    @Override
    public String toString() {
        return "StudentGroupDTO{" +
                "studentName='" + studentName + '\'' +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
