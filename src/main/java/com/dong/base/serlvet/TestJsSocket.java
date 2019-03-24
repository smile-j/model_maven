package com.dong.base.serlvet;

import org.I0Itec.zkclient.ZkLock;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/8/20.
 */
@WebServlet(name = "TestJsSocket")
public class TestJsSocket extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        ZkLock zkLock = new ZkLock();

        System.out.println( request.getRemoteAddr()+"********"+request.getParameter("name")+"---"+request.getParameter("age"));
        response.getWriter().print( (request.getParameter("callback")!=null?request.getParameter("callback"):"")+"({name:'sbsb',age:21})");
        response.getWriter().close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
