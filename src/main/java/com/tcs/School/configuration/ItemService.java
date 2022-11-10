package com.tcs.School.configuration;

import com.tcs.School.entity.CourseEntity;

public interface ItemService {
    public CourseEntity[] getCourseById(Long id);
}