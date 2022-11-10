package com.tcs.School.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseEntity{
    private Long id;
    private String name;
    private int credits;
    private Long studentId;
}