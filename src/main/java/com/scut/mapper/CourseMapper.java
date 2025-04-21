package com.scut.mapper;

import com.scut.entity.Course;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseMapper {
    /**
     * 查询当前用户所有的课程
     * @param currentId
     * @return
     */
    @Select("select * from course where user_id = #{currentId}")
    List<Course> getByUserId(Long currentId);

    /**
     * 添加课程
     * @param course 课程对象
     */
    void add(Course course);

    /**
     * 根据课程id查询课程
     * @param id
     * @return
     */
    @Select("select * from course where id = #{id}")
    Course getById(Long id);

    /**
     * 根据id删除课程
     * @param id 课程id
     */
    @Delete("delete from course where id = #{id}")
    void deleteById(Long id);
}
