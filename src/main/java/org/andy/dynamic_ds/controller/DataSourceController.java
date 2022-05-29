package org.andy.dynamic_ds.controller;

import org.andy.dynamic_ds.datasource.DataSourceType;
import org.andy.dynamic_ds.model.User;
import org.andy.dynamic_ds.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author MaoPing Zou
 * @date 2022/5/29 09:55
 */
@RestController
public class DataSourceController {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceController.class);

    @Autowired
    private UserService userService;

    /**
     * 修改数据源
     *
     * @param dsType  数据源类型
     * @param session session 用于存放一些值
     */
    @PostMapping("/dsType")
    public void setDsType(String dsType, HttpSession session) {
        session.setAttribute(DataSourceType.DS_SESSION_KEY, dsType);
        // 打印出info的日志出来
        logger.info("数据源切换为 {}", dsType);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
