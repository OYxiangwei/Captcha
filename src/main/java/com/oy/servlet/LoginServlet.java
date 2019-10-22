package com.oy.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Content-type","text/html;charset=UTF-8");
        String captcha = (String) req.getSession().getAttribute("captcha");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        PrintWriter printWriter = resp.getWriter();
        if(!req.getParameter("captcha").equals(captcha.toLowerCase())){
            printWriter.println("验证码输入错误");
            return;
        }
        if("yxw".equals(username) && "123".equals(password)){
            printWriter.write("登录成功");
        }else {
            printWriter.write("用户名或者密码错误");
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
