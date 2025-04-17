package com.scut.controller;

import com.scut.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Slf4j
@Api(tags = "TestController")
public class TestController {
    @GetMapping
    @ApiOperation(value = "测试接口")
    public Result<String> test() {
        log.info("调用测试接口");
        String str = "test";
        return Result.success(str);
    }
}
