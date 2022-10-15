package com.ty.service;

import com.ty.mapper.AdminMapper;
import com.ty.pojo.Admin;
import com.ty.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * @author lemon缚
 * @date 2022/07/06
 **/
public class AdminService {

    public Admin login(String username, String password){
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        Admin admin = mapper.selectUser(username, password);
        sqlSession.close();
        return admin;
    }
}
