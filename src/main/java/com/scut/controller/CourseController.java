package com.scut.controller;

import com.scut.dto.CourseDTO;
import com.scut.entity.Course;
import com.scut.entity.CoursePrerequisite;
import com.scut.result.Result;
import com.scut.service.CourseService;
import com.scut.vo.CourseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程相关接口
 */
@RestController
@RequestMapping("/course")
@Slf4j
@Api(tags = "课程接口")
public class CourseController {
    @Autowired
    private CourseService courseService;

    /**
     * 添加课程
     * @param courseDTO
     * @return
     */
    @PostMapping
    @ApiOperation("添加课程")
    public Result addCourse(@RequestBody CourseDTO courseDTO) {
        log.info("添加课程：{}", courseDTO);
        courseService.addCourse(courseDTO);
        return Result.success();
    }

    /**
     * 删除课程
     * @param id 课程id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation("删除课程")
    public Result deleteCourse(@PathVariable Long id) {
        log.info("删除课程：{}", id);
        courseService.deleteCourse(id);
        return Result.success();
    }

    /**
     * 查询用户所有课程
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    @ApiOperation("获取用户所有课程")
    public Result<List<CourseVO>> getCoursesByUserId(@PathVariable Long userId) {
        log.info("获取用户所有课程：{}", userId);
        return Result.success(courseService.getCoursesByUserId(userId));
    }

    /**
     * 修改课程数据
     * @param courseDTO 修改后的课程数据
     * @return
     */
    @PutMapping
    @ApiOperation("修改课程数据")
    public Result updateCourse(@RequestBody CourseDTO courseDTO) {
        log.info("修改课程数据：{}", courseDTO);
        courseService.updateCourse(courseDTO);
        return Result.success();
    }
    /**
     * 添加新的课程依赖关系
     * @param coursePrerequisite
     */
    @PostMapping("/prerequisite")
    @ApiOperation("添加新的课程依赖关系")
    public Result addCoursePrerequisite(@RequestBody CoursePrerequisite coursePrerequisite) {
        courseService.addCoursePrerequisite(coursePrerequisite);
        return Result.success();
    }
}
