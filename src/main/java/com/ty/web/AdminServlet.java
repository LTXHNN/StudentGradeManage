package com.ty.web;

import com.ty.pojo.Admin;
import com.ty.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * @author lemon缚
 * @date 2022/07/06
 **/
@WebServlet("/admin/*")
public class AdminServlet extends BaseServlet{
    AdminService adminService = new AdminService();
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("1234");
        try{
            String username = req.getParameter("username");

            String password = req.getParameter("password");
            System.out.println(username);
            Admin admin = adminService.login(username, password);
            HttpSession session = req.getSession();
            session.setAttribute("user",admin);
            System.out.println(admin);
            if(admin!=null){
                resp.getWriter().write("success");
            }else {
                resp.getWriter().write("failed");
            }
        }catch (Exception e){
            System.out.println(e);
            resp.getWriter().write("failed");
        }
    }


}
