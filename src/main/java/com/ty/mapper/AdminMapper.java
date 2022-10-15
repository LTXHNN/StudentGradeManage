package com.ty.mapper;

import com.ty.pojo.Admin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author lemon缚
 * @date 2022/07/06
 **/
public interface AdminMapper {
    @Select("select * from superuser where username=#{username} and password = #{password}")
    Admin selectUser(@Param("username") String username, @Param("password") String password);
}
