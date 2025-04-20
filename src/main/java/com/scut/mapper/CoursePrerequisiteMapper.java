package com.scut.mapper;

import com.scut.entity.CoursePrerequisite;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CoursePrerequisiteMapper {
    /**
     * 添加新的先修课程关系
     * @param preCourse
     */
    void add(CoursePrerequisite preCourse);
}
