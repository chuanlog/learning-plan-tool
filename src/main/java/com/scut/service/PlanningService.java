package com.scut.service;

import com.scut.dto.PlanQueryDTO;
import com.scut.entity.LearningPlan;

public interface PlanningService {
    LearningPlan getPlanning(PlanQueryDTO planQueryDTO);
}
