package com.tcs.School.entity;

import com.tcs.School.model.StudentModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    //CourseEntity[] course;
    StudentModel student;
}
