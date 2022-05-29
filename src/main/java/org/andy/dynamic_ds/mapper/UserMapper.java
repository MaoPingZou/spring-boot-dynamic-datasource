package org.andy.dynamic_ds.mapper;

import org.andy.dynamic_ds.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author MaoPing Zou
 * @date 2022/5/21 16:37
 */
@Mapper
public interface UserMapper {

    @Select("select * from user")
    List<User> getAllUser();

}
