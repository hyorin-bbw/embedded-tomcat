package com.hyorin.embed_tomcat;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/")
public class Testservlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //设置相应类型
        resp.setContentType("text/html");
        System.out.println("服务器名称：" + req.getServerName());
        String username = req.getParameter("name");
        System.out.println("请求的参数部分：" + username);
        //获取输出流
        PrintWriter pw = resp.getWriter();
        if (username == null) {
            //写入响应
            username = "world!";
            pw.write("<h2>hello  " + username + "</h2>");
            pw.flush();
        } else {
            //写入响应
            pw.write("<h2>hello " + username + "</h2>");
            pw.flush();
        }
    }
}
