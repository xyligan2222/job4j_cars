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


@WebServlet("/markFilter")
public class MarkFilterServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(MarkFilterServlet.class.getName());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setCharacterEncoding("UTF-8");
        String mark = req.getParameter("mark");
        List<MarkAuto> listMarkAuto = AdRepostiroty.instOf().findAllMarkAuto();
        List<Post> markAutoFilter = AdRepostiroty.instOf().findCarMark(Integer.parseInt(mark));
        LOG.info("in doGET markFilter = " + markAutoFilter.size());
        req.setAttribute("allPost",  markAutoFilter);
        req.setAttribute("markAuto", listMarkAuto);
        req.setAttribute("carBody", AdRepostiroty.instOf().findAllCarBody());
        req.getRequestDispatcher("index.jsp").forward(req, resp);

    }
}
