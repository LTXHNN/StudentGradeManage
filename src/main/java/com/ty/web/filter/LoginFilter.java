package com.ty.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 李天翔
 * @date 2022/07/02
 **/
@WebFilter("/*")
public class LoginFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest hsp = (HttpServletRequest)req;

        HttpSession session = hsp.getSession();
        String[] urls = {"/login.html","/css/","/admin/login","/js/"};
        String s = ((HttpServletRequest) req).getRequestURL().toString();
        for (String url : urls) {
            if(s.contains(url)){
                chain.doFilter(req, resp);

                return;
            }
        }
        Object user = session.getAttribute("user");

        if(user!=null){
            chain.doFilter(req, resp);
        }
    }

    public void destroy() {
    }


    public void init(FilterConfig config) throws ServletException {

    }

}
