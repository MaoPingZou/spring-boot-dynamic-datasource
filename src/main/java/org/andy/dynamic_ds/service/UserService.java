package org.andy.dynamic_ds.service;

import org.andy.dynamic_ds.annotation.DataSource;
import org.andy.dynamic_ds.mapper.UserMapper;
import org.andy.dynamic_ds.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MaoPing Zou
 * @date 2022/5/21 16:38
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @DataSource("slave")
    public List<User> getAllUsers() {
        return userMapper.getAllUser();
    }
}
