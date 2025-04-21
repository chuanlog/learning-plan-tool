package com.scut.controller;

import com.scut.dto.CourseDTO;
import com.scut.entity.Course;
import com.scut.result.Result;
import com.scut.service.CourseService;
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

    @GetMapping("/{userId}")
    @ApiOperation("获取用户所有课程")
    public Result<List<Course>> getCoursesByUserId(@PathVariable Long userId) {
        log.info("获取用户所有课程：{}", userId);
        return Result.success(courseService.getCoursesByUserId(userId));
    }
}
