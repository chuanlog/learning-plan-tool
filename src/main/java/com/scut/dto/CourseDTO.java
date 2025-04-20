package com.scut.dto;

import lombok.*;

import java.util.List;

/**
 * 课程的数据传输对象
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CourseDTO {
    private String courseName; // 课程名称
    private Integer credits; // 学分
    private Integer totalHours; // 总课时
    private Integer courseType; // 课程类型
    private List<Long> preRequisiteCourseIds;//先修课程的id列表
}
