package com.scut.controller;

import com.scut.dto.PlanQueryDTO;
import com.scut.entity.LearningPlan;
import com.scut.result.Result;
import com.scut.service.PlanningService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 培养计划相关接口
 */
@RestController
@RequestMapping("/plan")
@Slf4j
@Api(tags = "培养计划接口")
public class PlanningController {
    @Autowired
    private PlanningService planningService;
    @PostMapping("/planninng")
    @ApiOperation("获取培养计划")
    public Result<LearningPlan> getPlanning(@RequestBody PlanQueryDTO planQueryDTO){
        return Result.success(planningService.getPlanning(planQueryDTO));
    }


}
