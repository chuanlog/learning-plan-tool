package com.scut.algorithm;

import com.scut.algorithm.impl.CycleJudgeImpl;
import com.scut.entity.CoursePrerequisite;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 判圈算法的测试类
 */
class CycleJudgeImplTest {

    @Test
    void testJudgeGlobally_NoCycle() {
        // 无环依赖关系
        List<CoursePrerequisite> relations = new ArrayList<>();
        relations.add(new CoursePrerequisite(1L, 2L));
        relations.add(new CoursePrerequisite(2L, 3L));
        relations.add(new CoursePrerequisite(3L, 4L));

        List<Long> courseIds = Arrays.asList(1L, 2L, 3L, 4L);

        CycleJudge cycleJudge = new CycleJudgeImpl();
        List<Long> result = cycleJudge.judgeGlobally(relations, courseIds);

        // 预期无环，返回空列表
        assertTrue(result.isEmpty(), "Expected no cycle, but got: " + result);
    }

    @Test
    void testJudgeGlobally_WithCycle() {
        // 有环依赖关系
        List<CoursePrerequisite> relations = new ArrayList<>();
        relations.add(new CoursePrerequisite(1L, 2L));
        relations.add(new CoursePrerequisite(2L, 3L));
        relations.add(new CoursePrerequisite(3L, 1L)); // 形成环

        List<Long> courseIds = Arrays.asList(1L, 2L, 3L);

        CycleJudge cycleJudge = new CycleJudgeImpl();
        List<Long> result = cycleJudge.judgeGlobally(relations, courseIds);

        // 预期有环，返回环中的课程id
        assertFalse(result.isEmpty(), "Expected a cycle, but no cycle detected.");
        assertTrue(result.contains(1L), "Expected cycle to contain course 1.");
        assertTrue(result.contains(2L), "Expected cycle to contain course 2.");
        assertTrue(result.contains(3L), "Expected cycle to contain course 3.");
    }

    @Test
    void testJudgeGlobally_MultipleCycles() {
        // 多个环依赖关系
        List<CoursePrerequisite> relations = new ArrayList<>();
        relations.add(new CoursePrerequisite(1L, 2L));
        relations.add(new CoursePrerequisite(2L, 3L));
        relations.add(new CoursePrerequisite(3L, 1L)); // 形成第一个环

        relations.add(new CoursePrerequisite(4L, 5L));
        relations.add(new CoursePrerequisite(5L, 6L));
        relations.add(new CoursePrerequisite(6L, 4L)); // 形成第二个环

        List<Long> courseIds = Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L);

        CycleJudge cycleJudge = new CycleJudgeImpl();
        List<Long> result = cycleJudge.judgeGlobally(relations, courseIds);

        // 预期有环，返回环中的课程id
        assertFalse(result.isEmpty(), "Expected a cycle, but no cycle detected.");
        assertTrue(result.contains(1L), "Expected cycle to contain course 1.");
        assertTrue(result.contains(2L), "Expected cycle to contain course 2.");
        assertTrue(result.contains(3L), "Expected cycle to contain course 3.");
    }

    @Test
    void testJudgeGlobally_NoRelations() {
        // 无依赖关系，单独的课程
        List<CoursePrerequisite> relations = new ArrayList<>();
        List<Long> courseIds = Arrays.asList(1L, 2L, 3L, 4L);

        CycleJudge cycleJudge = new CycleJudgeImpl();
        List<Long> result = cycleJudge.judgeGlobally(relations, courseIds);

        // 无依赖关系，无法形成环，返回空列表
        assertTrue(result.isEmpty(), "Expected no cycle, but got: " + result);
    }

    @Test
    void testJudgeGlobally_SingleCourseCycle() {
        // 课程依赖自己，形成自环
        List<CoursePrerequisite> relations = new ArrayList<>();
        relations.add(new CoursePrerequisite(1L, 1L)); // 课程依赖自己

        List<Long> courseIds = Arrays.asList(1L);

        CycleJudge cycleJudge = new CycleJudgeImpl();
        List<Long> result = cycleJudge.judgeGlobally(relations, courseIds);

        // 预期自环，返回环中的课程id
        assertFalse(result.isEmpty(), "Expected a cycle, but no cycle detected.");
        assertTrue(result.contains(1L), "Expected cycle to contain course 1.");
    }
}
