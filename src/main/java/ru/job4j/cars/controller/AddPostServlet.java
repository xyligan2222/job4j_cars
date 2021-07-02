package ru.job4j.cars.controller;

import com.google.gson.Gson;
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

import java.util.List;
@WebServlet("/addPost.do")
public class AddPostServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(AddPostServlet.class.getName());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        resp.setCharacterEncoding("UTF-8");
        List<MarkAuto> listMarkAuto = AdRepostiroty.instOf().findAllMarkAuto();
        LOG.info("List size= " + listMarkAuto.size());
        req.setAttribute("markAuto", listMarkAuto);
        req.setAttribute("carBody", AdRepostiroty.instOf().findAllCarBody());
        req.getRequestDispatcher("create.jsp").forward(req, resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String descText = req.getParameter("desc");
        String mark = req.getParameter("mark");
        String carBody = req.getParameter("carBody");
        String run = req.getParameter("run");
        String money = req.getParameter("money");
        String photoCar = (String) req.getSession().getAttribute("photoCar");
        User user = (User) req.getSession().getAttribute("user");
        LocalDateTime localDate = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(localDate);
        CarBody body = AdRepostiroty.instOf().findCarBodyById(Integer.parseInt(carBody));
        MarkAuto markAuto = AdRepostiroty.instOf().findMarkAutoById(Integer.parseInt(mark));
        PhotoCar photo = AdRepostiroty.instOf().findPhotoCarByName(photoCar);
        Car carSave = new Car(descText,user,markAuto,body);
        AdRepostiroty.instOf().saveCar(carSave);
        Post post = new Post(descText,timestamp,carSave,photo,money,run);
        AdRepostiroty.instOf().savePost(post);
        System.out.println("post " + post);

        resp.sendRedirect(req.getContextPath() + "/index.jsp");

    }
}
