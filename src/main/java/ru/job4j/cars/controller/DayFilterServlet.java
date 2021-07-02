package ru.job4j.cars.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.cars.models.MarkAuto;
import ru.job4j.cars.models.Post;
import ru.job4j.cars.store.AdRepostiroty;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/dayFilter")
public class DayFilterServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(DayFilterServlet.class.getName());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setCharacterEncoding("UTF-8");

        String filter = req.getParameter("filter");
        List<MarkAuto> listMarkAuto = AdRepostiroty.instOf().findAllMarkAuto();
        List<Post> list = null;
        if (filter.equals("lastDay")) {
            list = AdRepostiroty.instOf().findCarLastDay();
        }
        if (filter.equals("all")) {
            list = AdRepostiroty.instOf().findAllPost();
        }
        if (filter.equals("withPhoto")) {
            list = AdRepostiroty.instOf().findCarWithPhoto();
        }
        req.setAttribute("allPost",  list);
        req.setAttribute("markAuto", listMarkAuto);
        req.setAttribute("carBody", AdRepostiroty.instOf().findAllCarBody());
        req.getRequestDispatcher("index.jsp").forward(req, resp);

    }
}
