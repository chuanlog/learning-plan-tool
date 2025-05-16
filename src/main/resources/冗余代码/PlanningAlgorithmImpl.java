package 冗余代码;

import com.scut.algorithm.PlanningAlgorithm;
import com.scut.entity.Course;
import com.scut.entity.CoursePrerequisite;
import com.scut.entity.LearningPlan;
import com.scut.entity.Semester;

import java.util.*;

import static com.scut.constant.CourseTypeConstant.*;


/**
 * 培养计划编制的相关算法
 */

public class PlanningAlgorithmImpl implements PlanningAlgorithm {

    /**
     * 获取培养计划
     * @param courses 课程列表
     * @param relations 先修关系列表
     * @param creditsOfProfessionalElectiveCourses 专选课学分要求
     * @param creditsOfCommonElectiveCourses 通选课学分要求
     * @return 培养计划
     */
    @Override
    public LearningPlan getLearningPlan(List<Course> courses, List<CoursePrerequisite> relations, int creditsOfProfessionalElectiveCourses, int creditsOfCommonElectiveCourses) {
        // 建立课程依赖关系图
        Map<Long, List<Long>> graph = new HashMap<>();
        for (CoursePrerequisite cp : relations) {
            graph.computeIfAbsent(cp.getPrerequisiteId(), k -> new ArrayList<>()).add(cp.getCourseId());
        }

        //创建每个学期
        List<Semester> allSemesters = new ArrayList<>();

        //提取体育课，并优先安排进每个学期
        int semesterIndex = 0;
        for (Course c : courses) {
            if (Objects.equals(c.getCourseType(), PHYSICAL_EDUCATION)) {
                if (semesterIndex < 8) {
                    Semester semester = allSemesters.get(semesterIndex);
                    //加入课程之后更新学期属性
                    UpdateSemester(semester, c);

                    semesterIndex++;
                }
                else {
                    System.out.println("体育课超过8门，无法全部分配到不同学期！");
                }
            }
        }

        //安排毕业设计
        int graduationSemester = 7;
        for (Course c : courses) {
            if (Objects.equals(c.getCourseType(), GRADUATE_DESIGN)) {
                    Semester semester = allSemesters.get(graduationSemester);
                    UpdateSemester(semester, c);
            }
        }

        //安排军训
        int trainingSemester = 2;
        for (Course c : courses) {
            if (Objects.equals(c.getCourseType(), MILITARY_TRAINING)) {
                Semester semester = allSemesters.get(trainingSemester);
                UpdateSemester(semester, c);
            }
        }

        //返回已经处理好的培养计划
        LearningPlan learningPlan = LearningPlan.builder()
                .AllSemesters(allSemesters)
                .build();

        return learningPlan;
    }

    /**
     * 加入课程后更新学期属性
     * @param semester
     * @param c
     */
    public void UpdateSemester(Semester semester, Course c) {
        semester.getCourses().add(c);

        //更新总学分
        Integer totalCredits = c.getCredits() + semester.getTotalCredits();
        semester.setTotalCredits(totalCredits);

        //更新其它学分
        Integer otherCourseCredits = c.getCredits() + semester.getOtherCourseCredits();
        semester.setOtherCourseCredits(otherCourseCredits);

        //更新总学时
        Integer totalHours = c.getTotalHours() + semester.getTotalHours();
        semester.setTotalHours(totalHours);

    }
}
