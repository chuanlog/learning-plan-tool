package com.scut.algorithm;

import com.scut.entity.CoursePrerequisite;

import java.util.List;
//TODO 实现判圈算法：交给队友搞
public interface CycleJudge {
    /**
     * 判断当前课程依赖是否有环，用来在添加课程时动态判断是否有环
     * @param relations 关系列表
     * @param courseIds 课程id列表
     * @return
     */
    boolean judge(List<CoursePrerequisite>relations, List<Long>courseIds);
}
