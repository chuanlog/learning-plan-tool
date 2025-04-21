package com.scut.algorithm;

import com.scut.entity.CoursePrerequisite;

import java.util.List;

public interface CycleJudge {
    /**
     * 判断是否有环
     * @param relations
     * @param courseIds
     * @return
     */
    boolean judge(List<CoursePrerequisite>relations, List<Long>courseIds);
}
