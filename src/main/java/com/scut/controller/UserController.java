package com.scut.controller;

import com.scut.constant.JwtClaimsConstant;
import com.scut.dto.UserDTO;
import com.scut.entity.User;
import com.scut.properties.JwtProperties;
import com.scut.result.Result;
import com.scut.service.UserService;
import com.scut.util.JwtUtil;
import com.scut.vo.UserLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户接口，这里用户主要用来确认哪些课程是属于哪些用户的
 */
@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags = "用户接口")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 注册新用户
     */
    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Result register(@RequestBody UserDTO userDTO) {
        log.info("用户注册，用户名：{}", userDTO.getUsername());
        userService.register(userDTO);
        return Result.success();
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Result<UserLoginVO> login(@RequestBody UserDTO userDTO) {
        log.info("用户登录，用户名：{}", userDTO.getUsername());
        // 调用service完成登录
        User user = userService.login(userDTO);
        // 登录成功，生成jwt令牌并返回
        // 登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.ID, user.getId());
        claims.put(JwtClaimsConstant.USERNAME, user.getUsername());
        String token = JwtUtil.createJWT(
                jwtProperties.getSecretKey(),
                jwtProperties.getTtl(),
                claims);
        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .token(token)
                .build();
        return Result.success(userLoginVO);
    }
}
