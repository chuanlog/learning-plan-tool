package com.scut.algorithm.impl;

import com.scut.algorithm.PlanningAlgorithm;
import com.scut.constant.CourseTypeConstant;
import com.scut.entity.Course;
import com.scut.entity.CoursePrerequisite;
import com.scut.entity.LearningPlan;
import com.scut.entity.Semester;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 学习计划生成算法实现类
 * 基于拓扑排序和学分平衡策略，将课程分配到各个学期
 */
public class PlanningAlgorithmImpl2 implements PlanningAlgorithm {
    @Override
    public LearningPlan getLearningPlan(List<Course> courses,
                                        List<CoursePrerequisite> relations,
                                        int creditsOfProfessionalElectiveCourses,
                                        int creditsOfCommonElectiveCourses) {
        // 预分配特殊课程（体育课、毕业设计）
        Map<Long, Course> courseMap = courses.stream().collect(Collectors.toMap(Course::getId, c -> c));
        List<Course> remainingCourses = new ArrayList<>(courses);
        List<Semester> semesters = initializeSemesters();

        preAssignSpecialCourses(remainingCourses, semesters, courseMap);  // 处理必须放在特定学期的课程

        // 构建拓扑排序并分组课程
        Map<Long, List<Long>> graph = buildGraph(relations);  // 构建课程依赖图
        Map<Long, Integer> inDegree = calculateInDegree(relations, courses);  // 计算入度
        List<Course> sortedCourses = topologicalSort(courses, graph, inDegree);  // 拓扑排序

        // 按课程类型分组
        Map<Integer, List<Course>> coursesByType = sortedCourses.stream()
                .collect(Collectors.groupingBy(Course::getCourseType));  // 按课程类型分类

        // 分配必修课程（公共基础课、专业必修课）
        assignRequiredCourses(coursesByType, semesters, courseMap);  // 分配必修课程

        // 分配选修课程（专业选修课、通选课）
        assignElectiveCourses(coursesByType, semesters, creditsOfProfessionalElectiveCourses,
                creditsOfCommonElectiveCourses, courseMap);  // 分配选修课程

        // 平衡学分并填充学期信息
        balanceCredits(semesters);  // 平衡各学期学分

        return LearningPlan.builder().AllSemesters(semesters).build();  // 构建学习计划
    }

    /**
     * 初始化8个学期对象
     */
    private List<Semester> initializeSemesters() {
        List<Semester> semesters = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            semesters.add(Semester.builder()
                    .Courses(new ArrayList<>())
                    .totalCredits(0)
                    .totalHours(0)
                    .publicBaseCourseCredits(0)
                    .majorBaseCourseCredits(0)
                    .majorElectiveCourseCredits(0)
                    .commonElectiveCourseCredits(0)
                    .otherCourseCredits(0)
                    .build());
        }
        return semesters;
    }

    /**
     * 预分配特殊课程：毕业设计和体育课
     */
    private void preAssignSpecialCourses(List<Course> remainingCourses, List<Semester> semesters, Map<Long, Course> courseMap) {
        // 毕业设计放在第8学期
        remainingCourses.removeIf(course -> {
            if (course.getCourseType() == CourseTypeConstant.GRADUATE_DESIGN) {
                semesters.get(7).getCourses().add(course);
                return true;
            }
            return false;
        });

        // 体育课均匀分配到前4个学期，每学期最多一节
        List<Course> peCourses = remainingCourses.stream()
                .filter(c -> c.getCourseType() == CourseTypeConstant.PHYSICAL_EDUCATION)
                .collect(Collectors.toList());
        for (int i = 0; i < peCourses.size(); i++) {
            Course pe = peCourses.get(i);
            semesters.get(i % 4).getCourses().add(pe);
            remainingCourses.remove(pe);
        }
    }

    /**
     * 构建课程依赖关系图
     */
    private Map<Long, List<Long>> buildGraph(List<CoursePrerequisite> relations) {
        Map<Long, List<Long>> graph = new HashMap<>();
        for (CoursePrerequisite cp : relations) {
            graph.computeIfAbsent(cp.getPrerequisiteId(), k -> new ArrayList<>()).add(cp.getCourseId());
        }
        return graph;
    }

    /**
     * 计算每个课程的入度（前置课程数量）
     */
    private Map<Long, Integer> calculateInDegree(List<CoursePrerequisite> relations, List<Course> courses) {
        Map<Long, Integer> inDegree = new HashMap<>();
        courses.forEach(c -> inDegree.put(c.getId(), 0));
        for (CoursePrerequisite cp : relations) {
            inDegree.put(cp.getCourseId(), inDegree.getOrDefault(cp.getCourseId(), 0) + 1);
        }
        return inDegree;
    }

    /**
     * 进行拓扑排序，确保满足所有课程前置条件
     */
    private List<Course> topologicalSort(List<Course> courses, Map<Long, List<Long>> graph, Map<Long, Integer> inDegree) {
        Queue<Course> queue = new LinkedList<>();
        List<Course> sortedCourses = new ArrayList<>();

        // 入度为0的课程加入队列
        courses.stream()
                .filter(c -> inDegree.get(c.getId()) == 0)
                .forEach(queue::offer);

        while (!queue.isEmpty()) {
            Course course = queue.poll();
            sortedCourses.add(course);
            for (Long neighborId : graph.getOrDefault(course.getId(), new ArrayList<>())) {
                inDegree.put(neighborId, inDegree.get(neighborId) - 1);
                if (inDegree.get(neighborId) == 0) {
                    courses.stream()
                            .filter(c -> c.getId().equals(neighborId))
                            .findFirst().ifPresent(queue::offer);
                }
            }
        }
        return sortedCourses;
    }

    /**
     * 分配必修课程（公共基础课、专业必修课）到对应学期
     */
    private void assignRequiredCourses(Map<Integer, List<Course>> coursesByType, List<Semester> semesters, Map<Long, Course> courseMap) {
        // 公共基础课（前3学期）
        List<Course> publicBase = coursesByType.getOrDefault(CourseTypeConstant.PUBLIC_BASE_COURSE, new ArrayList<>());
        distributeCourses(publicBase, semesters.subList(0, 3), courseMap);

        // 专业必修课（4-6学期）
        List<Course> majorBase = coursesByType.getOrDefault(CourseTypeConstant.MAJOR_BASE_COURSE, new ArrayList<>());
        distributeCourses(majorBase, semesters.subList(3, 6), courseMap);
    }

    /**
     * 将课程平均分配到目标学期中
     */
    private void distributeCourses(List<Course> courses, List<Semester> targetSemesters, Map<Long, Course> courseMap) {
        int coursesPerSemester = (int) Math.ceil((double) courses.size() / targetSemesters.size());
        int index = 0;
        for (Semester semester : targetSemesters) {
            for (int i = 0; i < coursesPerSemester && index < courses.size(); i++) {
                Course course = courses.get(index++);
                semester.getCourses().add(course);
                updateSemesterStats(semester, course);
            }
        }
    }

    /**
     * 分配选修课程（专业选修课、通选课）到对应学期
     */
    private void assignElectiveCourses(Map<Integer, List<Course>> coursesByType, List<Semester> semesters,
                                       int professionalCreditsReq, int commonCreditsReq, Map<Long, Course> courseMap) {
        // 专业选修课（4-7学期）
        List<Course> professionalElectives = coursesByType.getOrDefault(CourseTypeConstant.MAJOR_ELECTIVE_COURSE, new ArrayList<>());
        distributeElectives(professionalElectives, semesters.subList(3, 7), professionalCreditsReq);

        // 通选课（1-7学期）
        List<Course> commonElectives = coursesByType.getOrDefault(CourseTypeConstant.GENERAL_ELECTIVE_COURSE, new ArrayList<>());
        distributeElectives(commonElectives, semesters.subList(0, 7), commonCreditsReq);
    }

    /**
     * 分配选修课程直到满足或略超学分要求
     */
    private void distributeElectives(List<Course> electives, List<Semester> targetSemesters, int creditReq) {
        int totalAssignedCredits = 0;
        for (Semester semester : targetSemesters) {
            for (Course course : electives) {
                if (totalAssignedCredits >= creditReq + 2) break; // 略超学分但合理
                if (!semester.getCourses().contains(course)) {
                    semester.getCourses().add(course);
                    updateSemesterStats(semester, course);
                    totalAssignedCredits += course.getCredits();
                }
            }
        }
    }

    /**
     * 更新学期统计信息（添加课程时）
     */
    private void updateSemesterStats(Semester semester, Course course) {
        semester.setTotalCredits(semester.getTotalCredits() + course.getCredits());
        semester.setTotalHours(semester.getTotalHours() + course.getTotalHours());
        switch (course.getCourseType()) {
            case CourseTypeConstant.PUBLIC_BASE_COURSE:
                semester.setPublicBaseCourseCredits(semester.getPublicBaseCourseCredits() + course.getCredits());
                break;
            case CourseTypeConstant.MAJOR_BASE_COURSE:
                semester.setMajorBaseCourseCredits(semester.getMajorBaseCourseCredits() + course.getCredits());
                break;
            case CourseTypeConstant.MAJOR_ELECTIVE_COURSE:
                semester.setMajorElectiveCourseCredits(semester.getMajorElectiveCourseCredits() + course.getCredits());
                break;
            case CourseTypeConstant.GENERAL_ELECTIVE_COURSE:
                semester.setCommonElectiveCourseCredits(semester.getCommonElectiveCourseCredits() + course.getCredits());
                break;
            default:
                semester.setOtherCourseCredits(semester.getOtherCourseCredits() + course.getCredits());
        }
    }

    /**
     * 平衡各学期学分，使各学期学分尽量均衡
     */
    private void balanceCredits(List<Semester> semesters) {
        int totalCredits = semesters.stream().mapToInt(Semester::getTotalCredits).sum();
        int avgCredits = totalCredits / semesters.size();

        semesters.forEach(currentSemester -> {
            int retry = 3; // 安全阀防止无限循环
            while (currentSemester.getTotalCredits() > avgCredits + 3 && retry-- > 0) {
                Optional<Course> toMove = currentSemester.getCourses().stream()
                        .filter(c -> !isSpecialCourse(c))
                        .min(Comparator.comparingInt(Course::getCredits)); // 优先移动低学分课程

                if (!toMove.isPresent()) break;

                Course course = toMove.get();
                int currentIndex = semesters.indexOf(currentSemester);

                // 寻找合适的目标学期（后续学期中第一个可容纳的）
                Optional<Semester> targetSemester = semesters.subList(currentIndex + 1, semesters.size()).stream()
                        .filter(s -> s.getTotalCredits() + course.getCredits() <= avgCredits + 3)
                        .findFirst();

                targetSemester.ifPresent(s -> {
                    currentSemester.getCourses().remove(course);
                    s.getCourses().add(course);
                    // 更新两个学期的统计信息
                    updateSemesterStats(currentSemester, course, true); // 移除
                    updateSemesterStats(s, course, false); // 添加
                });

                // 如果找不到合适学期，提前终止
                if (!targetSemester.isPresent()) break;
            }
        });
    }

    /**
     * 更新学期统计信息（添加/移除课程时）
     */
    private void updateSemesterStats(Semester semester, Course course, boolean isRemove) {
        int factor = isRemove ? -1 : 1;
        semester.setTotalCredits(semester.getTotalCredits() + factor * course.getCredits());
        semester.setTotalHours(semester.getTotalHours() + factor * course.getTotalHours());

        switch (course.getCourseType()) {
            case CourseTypeConstant.PUBLIC_BASE_COURSE:
                semester.setPublicBaseCourseCredits(
                        semester.getPublicBaseCourseCredits() + factor * course.getCredits());
                break;
            case CourseTypeConstant.MAJOR_BASE_COURSE:
                semester.setMajorBaseCourseCredits(
                        semester.getMajorBaseCourseCredits() + factor * course.getCredits());
                break;
            case CourseTypeConstant.MAJOR_ELECTIVE_COURSE:
                semester.setMajorElectiveCourseCredits(
                        semester.getMajorElectiveCourseCredits() + factor * course.getCredits());
                break;
            case CourseTypeConstant.GENERAL_ELECTIVE_COURSE:
                semester.setCommonElectiveCourseCredits(
                        semester.getCommonElectiveCourseCredits() + factor * course.getCredits());
                break;
            default:
                semester.setOtherCourseCredits(
                        semester.getOtherCourseCredits() + factor * course.getCredits());
        }
    }

    /**
     * 判断是否是特殊课程（毕业设计或体育课）
     */
    private boolean isSpecialCourse(Course course) {
        return course.getCourseType() == CourseTypeConstant.GRADUATE_DESIGN ||
                course.getCourseType() == CourseTypeConstant.PHYSICAL_EDUCATION;
    }
}
