package com.scut.service.impl;

import com.scut.constant.MessageConstant;
import com.scut.dto.UserDTO;
import com.scut.entity.User;
import com.scut.exception.PasswordErrorException;
import com.scut.exception.UserNotExistException;
import com.scut.mapper.UserMapper;
import com.scut.service.UserService;
import com.scut.exception.MissingInformationException;
import com.scut.exception.UserExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * 用户服务的实现类
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    /**
     * 注册新用户
     * @param userDTO 用户信息
     */
    @Override
    public void register(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        // 检查数据完整性
        if (user.getUsername() == null || user.getPassword() == null) {
            log.info("用户名或密码为空，注册失败");
            throw new MissingInformationException(MessageConstant.MESSING_INFORMATION);
        }
        // 判断用户名是否已经存在
        User existUser = userMapper.getByUsername(user.getUsername());
        if (existUser != null) {
            log.info("用户名已存在，注册失败");
            throw new UserExistException(MessageConstant.USER_ALREADY_EXISTS);
        }
        // 对密码进行md5加密
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        // 插入用户
        userMapper.add(user);
    }

    /**
     * 用户登录
     *
     * @param userDTO 用户信息
     * @return 查询出来的用户信息
     */
    @Override
    public User login(UserDTO userDTO) {
        // 检查数据完整性
        if(userDTO.getUsername() == null || userDTO.getPassword() == null){
            throw new MissingInformationException(MessageConstant.MESSING_INFORMATION);
        }
        User user = userMapper.getByUsername(userDTO.getUsername());
        // 判断用户是否存在，如果不存在，抛出异常
        if(user == null){
            throw new UserNotExistException(MessageConstant.USER_NOT_EXISTS);
        }

        // 校验密码
        if(!user.getPassword().equals(DigestUtils.md5DigestAsHex(userDTO.getPassword().getBytes()))){
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
        return user;
    }
}
