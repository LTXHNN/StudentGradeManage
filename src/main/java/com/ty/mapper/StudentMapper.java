package com.ty.mapper;

import com.ty.pojo.Student;

import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author lemon缚
 * @date 2022/07/06
 **/
public interface StudentMapper {

    @Select("select * from student where studentID=#{studentID} and password = #{password}")
    Student selectStudent(@Param("studentID") String studentID, @Param("password") String password);

    @Insert("insert into Student(studentID,studentPassword,studentName,detailMsg,math,english,chinese,java,sex) values(#{studentID},#{studentPassword},#{studentName}" +
            ",#{detailMsg},#{math},#{english},#{chinese},#{java},#{sex})")
    void addStudent(Student student);

    @Select("select * from student where studentID = #{studentID}")
    Student selectByStudentID(String studentID);


    @Select("select * from student where id= #{id}")
    Student selectByID(String id);

    @Select("select * from student")
    List<Student> selectAll();

    @Delete("delete from student where id = #{id}")
    void deleteByID(int id);

    void deleteByIDs(@Param("ids") int[] ids);

    @Update("UPDATE student SET studentID=#{studentID},studentName=#{studentName},detailMsg=#{detailMsg},math=#{math},english=#{english},chinese=#{chinese},java=#{java},sex=#{sex} where id=#{id}")
    void update(Student student);

//    分页查询
    @Select("select * from student limit #{begin},#{size}")
    List<Student> selectByPage(@Param("begin") int begin,@Param("size") int size);

    @Select("select count(*) from student")
    int selectTotalCount();

    List<Student> selectByPageAndCondition(@Param("begin") int begin,@Param("size") int size,@Param("student") Student student);

    int selectTotalCountByCondition(Student student);
}



