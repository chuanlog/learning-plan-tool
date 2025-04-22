package com.scut.mapper;

import com.scut.entity.CoursePrerequisite;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CoursePrerequisiteMapper {
    /**
     * 添加新的先修课程关系
     * @param preCourse
     */
    void add(CoursePrerequisite preCourse);

    /**
     * 根据课程id获取以该课程为先修课程的关系
     * @param id 课程id
     * @return
     */
    @Select("select * from course_prerequisite where prerequisite_id = #{id}")
    List<CoursePrerequisite> getByPrerequisiteCourseId(Long id);

    /**
     * 删除id为id的所有先修关系
     * @param id 课程id
     */
    @Delete("delete from course_prerequisite where course_id = #{id}")
    void deleteByCourseId(Long id);

    /**
     * 查询对应课程id列表的所有先修关系
     * @param courseIds
     * @return
     */
    List<CoursePrerequisite> getByCourseIds(List<Long> courseIds);
}
