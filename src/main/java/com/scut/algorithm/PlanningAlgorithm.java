package com.scut.algorithm;

import com.scut.entity.Course;
import com.scut.entity.CoursePrerequisite;
import com.scut.entity.LearningPlan;

import java.util.List;

/**
 * 培养计划编制的相关算法
 */
public interface PlanningAlgorithm {
    /**
     * 获取培养计划
     * @param courses 课程列表
     * @param relations 先修关系列表
     * @param creditsOfProfessionalElectiveCourses 专选课学分要求
     * @param creditsOfCommonElectiveCourses 通选课学分要求
     * @return 培养计划
     */
    LearningPlan getLearningPlan(List<Course>courses,
                                 List<CoursePrerequisite> relations,
                                 int creditsOfProfessionalElectiveCourses,
                                 int creditsOfCommonElectiveCourses);
}
