package com.scut.service.impl;

import com.scut.constant.MessageConstant;
import com.scut.context.BaseContext;
import com.scut.dto.CourseDTO;
import com.scut.entity.Course;
import com.scut.entity.CoursePrerequisite;
import com.scut.exception.BaseException;
import com.scut.mapper.CourseMapper;
import com.scut.mapper.CoursePrerequisiteMapper;
import com.scut.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CoursePrerequisiteMapper coursePrerequisiteMapper;

    /**
     * 添加课程
     *
     * @param courseDTO
     */
    @Override
    @Transactional
    public void addCourse(CourseDTO courseDTO) {
        //构建要插入的课程对象
        Course course = new Course();
        BeanUtils.copyProperties(courseDTO, course);
        course.setUserId(BaseContext.getCurrentId());
        //查出当前用户所有的课程，并统计其id，顺便检查是否存在当前课程
        List<Course> courses = courseMapper.getByUserId(BaseContext.getCurrentId());
        HashSet<Long> courseIds = new HashSet<>();
        for (Course c : courses) {
            courseIds.add(c.getId());
            if (c.getCourseName().equals(course.getCourseName())) {
                throw new BaseException(MessageConstant.COURSE_EXIST);
            }
        }
        //检查先修课程是否存在
        for (Long courseId : courseDTO.getPreRequisiteCourseIds()) {
            if (!courseIds.contains(courseId)) {
                throw new BaseException(MessageConstant.COURSE_NOT_EXIST
                        + "  id为:"
                        + courseId);
            }
        }
        //插入新课程
        courseMapper.add(course);
        //插入新课程的先修课程关系
        HashSet<CoursePrerequisite> preCourses = new HashSet<>();
        List<Long> coursePrerequisites = courseDTO.getPreRequisiteCourseIds();
        for (Long coursePrerequisite : coursePrerequisites) {
            preCourses.add(new CoursePrerequisite(course.getId(), coursePrerequisite));
        }
        for (CoursePrerequisite preCourse : preCourses) {
            coursePrerequisiteMapper.add(preCourse);
        }
    }

    /**
     * 删除课程
     *
     * @param id 课程id
     */
    @Override
    public void deleteCourse(Long id) {
        Course course = courseMapper.getById(id);
        // 进行校验
        if (course == null) {
            // 课程不存在
            throw new BaseException(MessageConstant.COURSE_NOT_EXIST);
        }
        if (!Objects.equals(course.getUserId(), BaseContext.getCurrentId())) {
            // 当前用户不是该课程的创建者
            throw new BaseException(MessageConstant.CANNOT_OPERATE_COURSE);
        }
        List<CoursePrerequisite> coursePrerequisites = coursePrerequisiteMapper.getByPrerequisiteCourseId(id);
        if (coursePrerequisites!=null&& !coursePrerequisites.isEmpty()){
            // 存在先修课程，不能删除，拼接错误信息
            StringBuilder message = new StringBuilder();
            for (CoursePrerequisite coursePrerequisite : coursePrerequisites) {
                message.append(coursePrerequisite.getCourseId()).append(",");
            }
            message.deleteCharAt(message.length() - 1);
            throw new BaseException(MessageConstant.COURSE_HAS_PRE_REQUISITE+"  id为:"+message);
        }
        courseMapper.deleteById(id);
        coursePrerequisiteMapper.deleteByCourseId(id);
    }
}
