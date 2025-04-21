package com.scut.service;

import com.scut.dto.CourseDTO;

public interface CourseService {
    /**
     * 添加课程
     * @param courseDTO
     */
    void addCourse(CourseDTO courseDTO);

    /**
     * 删除课程
     * @param id 课程id
     */
    void deleteCourse(Long id);
}
