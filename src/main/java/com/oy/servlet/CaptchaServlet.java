package com.oy.servlet;

import com.oy.java.Captcha;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CaptchaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/jpeg");
        resp.setHeader("Pragma","no-cache");
        resp.setHeader("Cache-Control","no-cache");
        resp.setDateHeader("Expires",0);
        Captcha captcha = new Captcha();
        req.getSession().setAttribute("captcha",captcha.getCode());
        captcha.write(resp.getOutputStream());
    }
}
