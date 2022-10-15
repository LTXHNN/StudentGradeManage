package com.ty.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/**
 * @author lemon缚
 * @date 2022/07/06
 **/
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        int index = requestURI.lastIndexOf('/');
        String method = requestURI.substring(index+1);
        System.out.println(method);
        Class<? extends BaseServlet> cls = this.getClass();
        try {
            Method method1 = cls.getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            method1.invoke(this,req,resp);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


    }
}
