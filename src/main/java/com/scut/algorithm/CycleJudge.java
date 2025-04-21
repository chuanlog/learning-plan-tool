package com.scut.algorithm;

import com.scut.entity.CoursePrerequisite;
import io.swagger.models.auth.In;

import java.util.List;
//TODO 实现判圈算法接口--交给队友搞
public interface CycleJudge {
    /**
     * 判断当前课程依赖是否有环
     * @param relations 关系列表,其中CoursePrerequisite类的courseId为当前课程id，prerequisiteId为当前课程的先修课程id
     * @param courseIds 课程id列表
     * @return 返回环中的课程id列表，如果没有环则返回空
     */
    List<Long> judgeDynamically(List<CoursePrerequisite>relations, List<Long>courseIds);

    /**
     * 动态地判断新加入的课程会不会导致新的课程依赖产生环
     * @param relations 原来的课程依赖关系
     * @param courseIds 原来的课程id列表
     * @param newcourseId 新加入的课程id
     * @param newcoursePrequisiteIds 新加入的课程的先修课程id列表
     * @return 返回环中的课程id列表，如果没有环则返回空
     */
    List<Long> judgeDynamically(List<CoursePrerequisite>relations,
                                List<Long>courseIds,
                                Long newcourseId,
                                List<Long>newcoursePrequisiteIds);
}
