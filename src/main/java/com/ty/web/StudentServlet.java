package com.ty.web;

import com.alibaba.fastjson.JSON;
import com.ty.pojo.PageBean;
import com.ty.pojo.Student;
import com.ty.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
/**
 * @author lemon缚
 * @date 2022/07/06
 **/
@WebServlet("/student/*")
public class StudentServlet extends BaseServlet {
    StudentService studentService = new StudentService();
    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            List<Student> students = studentService.selectAll();

            String s = JSON.toJSONString(students);
            System.out.println(s);
            resp.setContentType("text/json;charset=utf-8");
            resp.getWriter().write(s);
        }catch(Exception e){
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("failed");
        }
    }
    public void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String _currentPage = req.getParameter("currentPage");
            String _pageSize = req.getParameter("pageSize");

//            int currentPage = Integer.parseInt(req.getParameter("currentPage"));
//            int pageSize = Integer.parseInt(req.getParameter("pageSize"));
            int currentPage = Integer.parseInt(_currentPage);
            int pageSize = Integer.parseInt(_pageSize);

            System.out.println(currentPage+","+pageSize);
            PageBean<Student> studentPageBean = studentService.selectByPage(currentPage, pageSize);

            String s = JSON.toJSONString(studentPageBean);
            System.out.println(s);
            resp.setContentType("text/json;charset=utf-8");
            resp.getWriter().write(s);
        }catch(Exception e){
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("failed");
            System.out.println(e);
        }
    }
    public void selectByPageAndCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String _currentPage = req.getParameter("currentPage");
            String _pageSize = req.getParameter("pageSize");
            String _student = req.getReader().readLine();
//            int currentPage = Integer.parseInt(req.getParameter("currentPage"));
//            int pageSize = Integer.parseInt(req.getParameter("pageSize"));
            int currentPage = Integer.parseInt(_currentPage);
            int pageSize = Integer.parseInt(_pageSize);

            Student student = JSON.parseObject(_student,Student.class);
            System.out.println(currentPage+","+pageSize);
            System.out.println(student);
            PageBean<Student> studentPageBean = studentService.selectByPageAndCondition(currentPage, pageSize,student);

            String s = JSON.toJSONString(studentPageBean);
            System.out.println(s);
            resp.setContentType("text/json;charset=utf-8");
            resp.getWriter().write(s);
        }catch(Exception e){
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("failed");
            System.out.println(e);
        }
    }
    public void addStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        boolean b = false;
        try {
            BufferedReader reader = req.getReader();
            String s = reader.readLine();
            System.out.println(s);
            Student student = JSON.parseObject(s, Student.class);
            System.out.println(student);
            b = studentService.addStudent(student);
            System.out.println(b);
            if(b){
                resp.getWriter().write("success");
            }else {
                resp.getWriter().write("failed");
            }
        }catch (Exception e){
            System.out.println(e);
            resp.getWriter().write("failed");
        }

    }
    public void deleteById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String s = req.getParameter("id");

            int id = Integer.parseInt(s);
            studentService.deleteByID(id);

            resp.getWriter().write("success");
        } catch (Exception e) {
            System.out.println(e);
            resp.getWriter().write("failed");
        }
    }

    public void deleteByIds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            BufferedReader reader = req.getReader();

            String s = reader.readLine();
            int[] ids = JSON.parseObject(s, int[].class);
            studentService.deleteByIDs(ids);

            resp.getWriter().write("success");
        }catch (Exception e){
            System.out.println(e);
            resp.getWriter().write("failed");
        }
    }

    public void upData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            BufferedReader reader = req.getReader();
            String s = reader.readLine();
            System.out.println(s);

            Student student = JSON.parseObject(s,Student.class);
            studentService.upData(student);


        }catch(Exception e){
            System.out.println(e+"up");
            resp.getWriter().write("failed");
            return;
        }

        resp.getWriter().write("success");
    }

}
