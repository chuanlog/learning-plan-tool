package com.scut.service.impl;

import com.fasterxml.jackson.databind.ext.CoreXMLSerializers;
import com.scut.algorithm.CycleJudge;
import com.scut.constant.MessageConstant;
import com.scut.context.BaseContext;
import com.scut.dto.CourseDTO;
import com.scut.entity.Course;
import com.scut.entity.CoursePrerequisite;
import com.scut.exception.BaseException;
import com.scut.mapper.CourseMapper;
import com.scut.mapper.CoursePrerequisiteMapper;
import com.scut.service.CourseService;
import com.scut.vo.CourseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CoursePrerequisiteMapper coursePrerequisiteMapper;
    @Autowired
    private CycleJudge cycleJudge;

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
    @Transactional
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
        if (coursePrerequisites != null && !coursePrerequisites.isEmpty()) {
            // 存在先修课程，不能删除，拼接错误信息
            StringBuilder message = new StringBuilder();
            for (CoursePrerequisite coursePrerequisite : coursePrerequisites) {
                message.append(coursePrerequisite.getCourseId()).append(",");
            }
            message.deleteCharAt(message.length() - 1);
            throw new BaseException(MessageConstant.COURSE_HAS_PRE_REQUISITE + "  id为:" + message);
        }
        courseMapper.deleteById(id);
        coursePrerequisiteMapper.deleteByCourseId(id);
    }

    /**
     * 获取用户所有课程
     *
     * @param userId
     * @return
     */
    @Override
    public List<CourseVO> getCoursesByUserId(Long userId) {
        //判断当前请求uid是否是当前用户的id
        if (!Objects.equals(userId, BaseContext.getCurrentId())) {
            // 权限错误
            throw new BaseException(MessageConstant.PERMISSION_ERROR);
        }
        List<Course> byUserId = courseMapper.getByUserId(userId);
        List<CourseVO> resuleSet = new ArrayList<>();
        for (Course course : byUserId) {
            CourseVO temp=new CourseVO();
            BeanUtils.copyProperties(course,temp);
            List<Long> preRequisiteCourseIds = coursePrerequisiteMapper.getByCurrCourseId(course.getId());
            temp.setPreRequisiteCourseIds(preRequisiteCourseIds);
            resuleSet.add(temp);
        }
        return resuleSet;
    }

    /**
     * 修改课程数据
     *
     * @param courseDTO
     */
    @Override
    public void updateCourse(CourseDTO courseDTO) {
        if (courseDTO.getId() == null) {
            //课程为空
            throw new BaseException(MessageConstant.COURSE_NOT_EXIST);
        }
        Course currCourse = courseMapper.getById(courseDTO.getId());
        if(currCourse==null){
            //没有这个课程
            throw new BaseException(MessageConstant.COURSE_NOT_EXIST);
        }
        if (!Objects.equals(currCourse.getUserId(), BaseContext.getCurrentId())) {
            //权限错误
            throw new BaseException(MessageConstant.PERMISSION_ERROR);
        }
        //如果涉及修改先修关系，则需要先判断是否存在循环依赖
        if (courseDTO.getPreRequisiteCourseIds() != null) {
            //查询当前用户的所有课程及其id,并建立id哈希集合加速查询
            List<Course> courses = courseMapper.getByUserId(BaseContext.getCurrentId());
            List<Long> courseIds = courses.stream().map(Course::getId).collect(Collectors.toList());
            HashSet<Long> courseIdSet = new HashSet<>(courseIds);
            //查询当前用户的所有先修关系
            List<CoursePrerequisite> coursePrerequisites = coursePrerequisiteMapper.getByCourseIds(courseIds);
            //删除原课程的先修关系
            coursePrerequisites.removeIf(coursePrerequisite -> coursePrerequisite.getCourseId().equals(courseDTO.getId()));
            //构建课程新的先修关系
            List<Long> preCourseIds = courseDTO.getPreRequisiteCourseIds();
            for (Long preCourseId : preCourseIds) {
                if (!courseIdSet.contains(preCourseId)) {
                    //先修课程不存在
                    throw new BaseException(MessageConstant.COURSE_NOT_EXIST);
                }
                coursePrerequisites.add(new CoursePrerequisite(courseDTO.getId(), preCourseId));
            }
            //判圈
            List<Long> cycle = cycleJudge.judgeGlobally(coursePrerequisites, courseIds);
            if (!cycle.isEmpty()) {
                //存在环
                StringBuilder message = new StringBuilder();
                for (Long id : cycle) {
                    message.append(id).append(",");
                }
                message.deleteCharAt(message.length() - 1);
                throw new BaseException(MessageConstant.COURSE_HAS_CYCLE + "  id为:" + message);
            }
            //修改课程表内容
            Course newCourse = new Course();
            BeanUtils.copyProperties(courseDTO, newCourse);
            courseMapper.update(newCourse);
            //修改先修关系
            coursePrerequisiteMapper.deleteByCourseId(courseDTO.getId());
            for (Long preCourseId : preCourseIds) {
                coursePrerequisiteMapper.add(new CoursePrerequisite(courseDTO.getId(), preCourseId));
            }
        } else {
            Course course = new Course();
            BeanUtils.copyProperties(courseDTO, course);
            courseMapper.update(course);
        }
    }

    /**
     * 添加新的课程依赖关系
     * @param coursePrerequisite
     */
    @Override
    public void addCoursePrerequisite(CoursePrerequisite coursePrerequisite) {
        //查询当前用户的所有课程及其id,并建立id哈希集合加速查询
        List<Course> courses = courseMapper.getByUserId(BaseContext.getCurrentId());
        List<Long> courseIds = courses.stream().map(Course::getId).collect(Collectors.toList());
        HashSet<Long> courseIdSet = new HashSet<>(courseIds);
        //查询当前用户的所有先修关系
        List<CoursePrerequisite> coursePrerequisites = coursePrerequisiteMapper.getByCourseIds(courseIds);
        //存在性校验
        if (!courseIdSet.contains(coursePrerequisite.getCourseId())) {
            //课程不存在
            throw new BaseException(MessageConstant.COURSE_NOT_EXIST+" id为:"+coursePrerequisite.getCourseId());
        }
        if (!courseIdSet.contains(coursePrerequisite.getPrerequisiteId())) {
            //先修课程不存在
            throw new BaseException(MessageConstant.COURSE_NOT_EXIST+" id为:"+coursePrerequisite.getPrerequisiteId());
        }
        if (coursePrerequisites.contains(coursePrerequisite)) {
            //已经存在先修关系
            throw new BaseException(MessageConstant.COURSE_PREREQUISITE_EXIST);
        }
        //判圈，如果添加后没有圈就持久化这条添加
        coursePrerequisites.add(coursePrerequisite);
        List<Long> cycle = cycleJudge.judgeGlobally(coursePrerequisites, courseIds);
        if (!cycle.isEmpty()) {
            //存在环
            StringBuilder message = new StringBuilder();
            for (Long id : cycle) {
                message.append(id).append(",");
            }
            message.deleteCharAt(message.length() - 1);
            throw new BaseException(MessageConstant.COURSE_HAS_CYCLE + "  id为:" + message);
        } else {
            //没有环，持久化
            coursePrerequisiteMapper.add(coursePrerequisite);
        }
    }
}
