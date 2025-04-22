package com.scut.algorithm;

import com.scut.entity.CoursePrerequisite;
import org.springframework.stereotype.Component;

import java.util.List;


public interface CycleJudge {
    /**
     * 全局判断当前课程依赖是否有环
     * @param relations 关系列表,其中CoursePrerequisite类的courseId为当前课程id，prerequisiteId为当前课程的先修课程id
     * @param courseIds 课程id列表
     * @return 返回环中的课程id列表，如果没有环则返回空
     */
    List<Long> judgeGlobally(List<CoursePrerequisite>relations, List<Long>courseIds);
}
