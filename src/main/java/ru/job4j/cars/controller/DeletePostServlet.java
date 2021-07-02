package ru.job4j.cars.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.cars.models.*;
import ru.job4j.cars.store.AdRepostiroty;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@WebServlet("/deletePost.do")
public class DeletePostServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(DeletePostServlet.class.getName());
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String id = req.getParameter("delete");
        Post delete = AdRepostiroty.instOf().deletePost(Integer.parseInt(id));
        LOG.info("DeleteServlet " + delete);
        resp.sendRedirect(req.getContextPath() + "/index.jsp");

    }
}
