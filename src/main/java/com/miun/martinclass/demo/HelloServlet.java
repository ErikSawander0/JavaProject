package com.miun.martinclass.demo;

import java.io.*;

import com.miun.martinclass.demo.menu.entity.MenuItem;
import com.miun.martinclass.demo.menu.service.MenuService;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        var m = new MenuService();
        var menu = new MenuItem();
        menu.setName("asdf123");
        m.createMenuItem(menu);
        var items = m.getAllMenuItems();
        if (items.isEmpty()) {
            message = "no data found in menu items table";
        } else {
            message = items.getFirst().getName();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}