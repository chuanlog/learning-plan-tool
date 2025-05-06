package com.scut.algorithm;

import com.scut.entity.Course;
import com.scut.entity.CoursePrerequisite;
import com.scut.entity.LearningPlan;

import java.util.List;

/**
 * 培养计划编制的相关算法
 */
//TODO 实现此接口
public interface PlanningAlgorithm {
    LearningPlan getLearningPlan(List<Course>courses, List<CoursePrerequisite> relations);
}
