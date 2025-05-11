package com.scut.service.impl;

import com.scut.algorithm.CycleJudge;
import com.scut.algorithm.impl.PlanningAlgorithmImpl2;
import com.scut.constant.MessageConstant;
import com.scut.context.BaseContext;
import com.scut.dto.PlanQueryDTO;
import com.scut.entity.Course;
import com.scut.entity.CoursePrerequisite;
import com.scut.entity.LearningPlan;
import com.scut.exception.BaseException;
import com.scut.mapper.CourseMapper;
import com.scut.mapper.CoursePrerequisiteMapper;
import com.scut.service.PlanningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PlanningServiceImpl implements PlanningService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CoursePrerequisiteMapper coursePrerequisiteMapper;
    @Autowired
    private CycleJudge cycleJudge;

    private final PlanningAlgorithmImpl2 planningAlgorithm2 = new PlanningAlgorithmImpl2();

    /**
     * 返回培养计划编制
     *
     * @param planQueryDTO 编制设计信息
     * @return 培养计划
     */
    @Override
    public LearningPlan getPlanning(PlanQueryDTO planQueryDTO) {
        Long userId = planQueryDTO.getUserId();
        //鉴权
        Long currentUserId = BaseContext.getCurrentId();
        if (!Objects.equals(currentUserId, userId)) {
            // 权限错误
            throw new BaseException(MessageConstant.PERMISSION_ERROR);
        }
        //查询该用户所有课程以及先修关系,并进行一次判圈
        List<Course> courses = courseMapper.getByUserId(userId);
        List<Long> courseIds = new ArrayList<>();
        for (Course cours : courses) {
            Long id = cours.getId();
            courseIds.add(id);
        }
        List<CoursePrerequisite> coursePrerequisites = coursePrerequisiteMapper.getByCourseIds(courseIds);
        List<Long> cycleCourseIds = cycleJudge.judgeGlobally(coursePrerequisites, courseIds);
        if (cycleCourseIds != null && !cycleCourseIds.isEmpty()) {
            // 存在判圈课程，拼接错误信息
            StringBuilder message = new StringBuilder();
            for (Long cycleCourseId : cycleCourseIds) {
                message.append(cycleCourseId).append(",");
            }
            message.deleteCharAt(message.length() - 1);
            throw new BaseException(MessageConstant.COURSE_HAS_CYCLE + "  id为:" + message);
        }
        //获取培养计划
        return planningAlgorithm2.getLearningPlan(courses,
                coursePrerequisites,
                planQueryDTO.getCreditsOfProfessionalElectiveCourses(),
                planQueryDTO.getCreditsOfCommonElectiveCourses()
        );

    }
}
