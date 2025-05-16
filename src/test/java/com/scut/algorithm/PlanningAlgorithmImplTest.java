package com.scut.algorithm;

import com.scut.algorithm.impl.PlanningAlgorithmImpl;
import com.scut.constant.CourseTypeConstant;
import com.scut.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlanningAlgorithmImplTest {
    private PlanningAlgorithm planningAlgorithm;
    private List<Course> courses;
    private List<CoursePrerequisite> relations;

    @BeforeEach
    void setUp() {
        planningAlgorithm = new PlanningAlgorithmImpl();
        courses = new ArrayList<>();
        relations = new ArrayList<>();

        // 添加测试课程（包含必修、选修、体育课、毕业设计）
        courses.add(createCourse(1L, "高等数学", 5, 90, CourseTypeConstant.PUBLIC_BASE_COURSE));
        courses.add(createCourse(2L, "线性代数", 4, 72, CourseTypeConstant.PUBLIC_BASE_COURSE));
        courses.add(createCourse(3L, "数据结构", 3, 54, CourseTypeConstant.MAJOR_BASE_COURSE));
        courses.add(createCourse(4L, "算法设计", 3, 54, CourseTypeConstant.MAJOR_BASE_COURSE));
        courses.add(createCourse(5L, "机器学习", 3, 54, CourseTypeConstant.MAJOR_ELECTIVE_COURSE));
        courses.add(createCourse(6L, "人工智能", 2, 36, CourseTypeConstant.GENERAL_ELECTIVE_COURSE));
        courses.add(createCourse(7L, "毕业设计", 8, 144, CourseTypeConstant.GRADUATE_DESIGN));
        courses.add(createCourse(8L, "体育课1", 1, 36, CourseTypeConstant.PHYSICAL_EDUCATION));
        courses.add(createCourse(9L, "体育课2", 1, 36, CourseTypeConstant.PHYSICAL_EDUCATION));

        // 设置先修关系：数据结构 -> 算法设计，高等数学 -> 线性代数
        relations.add(createPrerequisite(4L, 3L)); // 算法设计依赖数据结构
        relations.add(createPrerequisite(2L, 1L)); // 线性代数依赖高等数学
    }

    private Course createCourse(Long id, String name, int credits, int hours, int type) {
        return Course.builder()
                .id(id)
                .courseName(name)
                .credits(credits)
                .totalHours(hours)
                .courseType(type)
                .build();
    }

    private CoursePrerequisite createPrerequisite(Long courseId, Long prerequisiteId) {
        return CoursePrerequisite.builder()
                .courseId(courseId)
                .prerequisiteId(prerequisiteId)
                .build();
    }

    @Test
    void testLearningPlanGeneration() {
        // 调用算法生成培养计划
        LearningPlan plan = planningAlgorithm.getLearningPlan(
                courses, relations, 
                3, // 专业选修课要求3学分
                2  // 通选课要求2学分
        );
        printLearningPlan(plan.getAllSemesters());

        // 断言1：共有8个学期
        List<Semester> semesters = plan.getAllSemesters();
        assertEquals(8, semesters.size());

        // 断言2：毕业设计必须在第8学期
        assertTrue(semesters.get(7).getCourses().stream()
                .anyMatch(c -> c.getCourseType() == CourseTypeConstant.GRADUATE_DESIGN));

        // 断言3：体育课均匀分布在前4学期，且每学期最多1节
        long peCoursesInSemesters = semesters.subList(0, 4).stream()
                .filter(s -> s.getCourses().stream()
                        .anyMatch(c -> c.getCourseType() == CourseTypeConstant.PHYSICAL_EDUCATION))
                .count();
        assertEquals(2, peCoursesInSemesters); // 测试数据中有2节体育课

        // 断言4：先修关系不违反（线性代数必须在高等数学之后）
        int higherMathSemester = findCourseSemester(semesters, 1L); // 高等数学
        int linearAlgebraSemester = findCourseSemester(semesters, 2L); // 线性代数
        assertTrue(linearAlgebraSemester > higherMathSemester);

        // 断言5：选修课学分略高于要求（专业选修要求3，实际分配3；通选课要求2，实际分配2）
        int professionalElectiveCredits = semesters.stream()
                .mapToInt(Semester::getMajorElectiveCourseCredits)
                .sum();
        int commonElectiveCredits = semesters.stream()
                .mapToInt(Semester::getCommonElectiveCourseCredits)
                .sum();
        assertTrue(professionalElectiveCredits >= 3);
        assertTrue(commonElectiveCredits >= 2);
    }

    // 辅助方法：查找课程所在学期序号
    private int findCourseSemester(List<Semester> semesters, Long courseId) {
        for (int i = 0; i < semesters.size(); i++) {
            boolean exists = semesters.get(i).getCourses().stream()
                    .anyMatch(c -> c.getId().equals(courseId));
            if (exists) return i;
        }
        return -1;
    }
    // 辅助方法：打印培养计划详情
    private void printLearningPlan(List<Semester> semesters) {
        System.out.println("=============== 培养计划详情 ===============");
        for (int i = 0; i < semesters.size(); i++) {
            Semester semester = semesters.get(i);
            System.out.printf("第%d学期：\n", i + 1);
            System.out.printf("- 总学分：%d，总学时：%d\n", semester.getTotalCredits(), semester.getTotalHours());
            System.out.println("- 课程列表：");
            semester.getCourses().forEach(course ->
                    System.out.printf("  - %s（ID：%d，类型：%d，学分：%d，学时：%d）\n",
                            course.getCourseName(),
                            course.getId(),
                            course.getCourseType(),
                            course.getCredits(),
                            course.getTotalHours())
            );
            System.out.println("-----------------------------------");
        }
        System.out.println("=======================================");
    }
}