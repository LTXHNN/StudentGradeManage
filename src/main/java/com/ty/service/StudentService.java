package com.ty.service;

import com.ty.mapper.StudentMapper;
import com.ty.pojo.PageBean;
import com.ty.pojo.Student;
import com.ty.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;

/**
 * @author lemon缚
 * @date 2022/07/06
 **/
public class StudentService {
    public Student login(String studentID, String studentPassword){
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        Student student = mapper.selectStudent(studentID, studentPassword);
        sqlSession.close();
        return student;
    }

    public boolean addStudent(Student student){
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student user = mapper.selectByStudentID(student.getStudentID());
        if(user==null){
            mapper.addStudent(student);
            sqlSession.commit();
        }
        sqlSession.close();
        return user==null;
    }

    public List<Student> selectAll() {
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = null;
//        try {
//            inputStream = Resources.getResourceAsStream(resource);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> Students = mapper.selectAll();

        sqlSession.close();
        return Students;
    }


    public boolean checkStudentID(String studentID) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = mapper.selectByStudentID(studentID);
        sqlSession.close();
        return student != null;
    }
    public void deleteByIDs(int[] ids){
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        mapper.deleteByIDs(ids);
        sqlSession.commit();
        sqlSession.close();
    }

    public void deleteByID(int id){
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        mapper.deleteByID(id);
        sqlSession.commit();
        sqlSession.close();
    }

    public void upData(Student student){
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        mapper.update(student);
        sqlSession.commit();
        sqlSession.close();
    }

    public PageBean<Student> selectByPage(int currentPage, int PageSize){
        int begin  = (currentPage-1)*PageSize;
        int size = PageSize;
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectByPage(begin, size);

        int i = mapper.selectTotalCount();

        PageBean<Student> studentPageBean = new PageBean<>();
        studentPageBean.setStudents(students);
        studentPageBean.setTotalPage(i);
        System.out.println(studentPageBean);
        return studentPageBean;

    }

    public PageBean<Student> selectByPageAndCondition(int currentPage, int PageSize,Student student){
        int begin  = (currentPage-1)*PageSize;
        int size = PageSize;
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        if(student.getStudentName().length()>0){
            student.setStudentName("%"+student.getStudentName()+"%");
        }
        if(student.getStudentID().length()>0){
            student.setStudentID("%"+student.getStudentID()+"%");
        }
        System.out.println(student.getStudentID()+"---");
        System.out.println(student);
        List<Student> students = mapper.selectByPageAndCondition(begin, size,student);

        int i = mapper.selectTotalCountByCondition(student);

        PageBean<Student> studentPageBean = new PageBean<>();
        studentPageBean.setStudents(students);
        studentPageBean.setTotalPage(i);
        System.out.println(studentPageBean);
        return studentPageBean;

    }
}
