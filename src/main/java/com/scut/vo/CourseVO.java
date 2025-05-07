package com.scut.vo;

import lombok.*;

import java.util.List;

/**
 * 课程的数据展示对象
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CourseVO {
    private Long id;// 课程id
    private String courseName; // 课程名称
    private Integer credits; // 学分
    private Integer totalHours; // 总课时
    private Integer courseType; // 课程类型
    private Long userId; // 该课程所属的用户id
    List<Long> preRequisiteCourseIds;// 前置课程id
}
