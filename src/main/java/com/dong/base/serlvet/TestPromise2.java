package com.dong.base.serlvet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/20.
 */
@WebServlet(name = "TestPromise2")
public class TestPromise2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        System.out.println( request.getRemoteAddr()+"********"+request.getParameter("name")+"---"+request.getParameter("age"));
        System.out.println(2+"*********************"+new Date().getTime());
        response.getWriter().print("{Status:1,ResultJson:'asfsfsfsfs'}");
        response.getWriter().close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
