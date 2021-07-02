package ru.job4j.cars.controller;

import ru.job4j.cars.models.User;
import ru.job4j.cars.store.AdRepostiroty;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/reg.do")
public class RegServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        System.out.println(" do Post Reg ");
            User user = new User(
                    req.getParameter("name"),
                    req.getParameter("email"),
                    req.getParameter("password"),
                    req.getParameter("phone"));
            System.out.println(user);
            AdRepostiroty.instOf().saveUser(user);
            HttpSession sc = req.getSession();
            sc.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }
}