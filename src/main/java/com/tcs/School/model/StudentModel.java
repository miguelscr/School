package com.tcs.School.model;

import com.tcs.School.entity.CourseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "students")
@NoArgsConstructor
public class StudentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private Long id;
    private String name;
    private int age;
    private String semester;
    private CourseEntity[] courses;

    public StudentModel(Long id, String name, int age, String semester) {
        this.id=id;
        this.name = name;
        this.age = age;
        this.semester = semester;
    }
}
