package com.scut.algorithm.impl;

import com.scut.algorithm.CycleJudge;
import com.scut.entity.CoursePrerequisite;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component//加入到IOC容器中
public class CycleJudgeImpl implements CycleJudge {
    /**
     * 全局判断是否有环
     * @param relations 关系列表,其中CoursePrerequisite类的courseId为当前课程id，prerequisiteId为当前课程的先修课程id
     * @param courseIds 课程id列表
     * @return
     */
    @Override
    public List<Long> judgeGlobally(List<CoursePrerequisite> relations, List<Long> courseIds) {
        // 建立课程依赖关系图
        Map<Long, List<Long>> graph = new HashMap<>();
        for (CoursePrerequisite cp : relations) {
            graph.computeIfAbsent(cp.getPrerequisiteId(), k -> new ArrayList<>()).add(cp.getCourseId());
        }

        // 标记所有节点的访问状态
        Map<Long, Integer> visitStatus = new HashMap<>();
        List<Long> cycle = new ArrayList<>();

        // 遍历所有课程id
        for (Long courseId : courseIds) {
            if (!visitStatus.containsKey(courseId)) {
                if (hasCycle(courseId, graph, visitStatus, cycle)) {
                    return cycle;
                }
            }
        }

        return new ArrayList<>(); // 无环
    }

    /**
     * 深度优先搜索判断是否有环
     * @param courseId
     * @param graph
     * @param visitStatus
     * @param cycle
     * @return
     */
    private boolean hasCycle(Long courseId, Map<Long, List<Long>> graph, Map<Long, Integer> visitStatus, List<Long> cycle) {
        // 0: 未访问, 1: 当前访问, 2: 已访问
        visitStatus.put(courseId, 1);

        if (graph.containsKey(courseId)) {
            for (Long neighbor : graph.get(courseId)) {
                if (visitStatus.getOrDefault(neighbor, 0) == 1) { // 找到环
                    cycle.add(neighbor);
                    cycle.add(courseId);
                    return true;
                }
                if (visitStatus.getOrDefault(neighbor, 0) == 0) {
                    if (hasCycle(neighbor, graph, visitStatus, cycle)) {
                        if (!cycle.isEmpty() && !cycle.contains(courseId)) {
                            cycle.add(courseId); // 记录环路径
                        }
                        return true;
                    }
                }
            }
        }
        visitStatus.put(courseId, 2); // 完成访问
        return false;
    }
}
