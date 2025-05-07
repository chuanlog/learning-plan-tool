package com.scut.service;

import com.scut.dto.CourseDTO;
import com.scut.entity.Course;
import com.scut.vo.CourseVO;

import java.util.List;

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

    /**
     * 获取用户所有课程
     * @param userId
     * @return
     */
    List<CourseVO> getCoursesByUserId(Long userId);

    /**
     * 修改课程数据
     *
     * @param courseDTO
     */
    void updateCourse(CourseDTO courseDTO);
}
