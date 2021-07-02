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

@WebServlet("/index.do")
public class AllPostServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(AllPostServlet.class.getName());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Class AllPostServlet doGet");
        List<Post> list = AdRepostiroty.instOf().findAllPost();
        req.setAttribute("allPost",  list);
        List<MarkAuto> listMarkAuto = AdRepostiroty.instOf().findAllMarkAuto();
        req.setAttribute("markAuto", listMarkAuto);

        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
