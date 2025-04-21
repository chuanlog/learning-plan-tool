package com.scut.service;

import com.scut.dto.UserDTO;
import com.scut.entity.User;

/**
 * 用户服务类
 */
public interface UserService {
    /**
     * 注册新用户
     * @param userDTO 用户信息
     */
    void register(UserDTO userDTO);

    /**
     * 用户登录
     * @param userDTO 用户信息
     * @return 查询出来的用户信息
     */
    User login(UserDTO userDTO);
}
