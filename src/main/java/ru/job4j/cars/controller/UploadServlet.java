package ru.job4j.cars.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.cars.models.PhotoCar;
import ru.job4j.cars.store.AdRepostiroty;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet ("/upload.do")
public class UploadServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(UploadServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        System.out.println(req.getParameterNames());
        PhotoCar photo = null;
        if (Integer.parseInt(id) != 0) {
            photo = AdRepostiroty.instOf().findPhotoCarById(Integer.parseInt(id));
        }
        if (photo != null) {
            resp.setContentType("name=" + photo.getName());
            resp.setContentType("image/png");
            resp.setHeader("Content-Disposition", "attachment; filename=\"" + photo.getName() + "\"");
            File file = new File("images" + File.separator + photo.getName());
            try (FileInputStream in = new FileInputStream(file)) {
                resp.getOutputStream().write(in.readAllBytes());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String photoName = null;
        DiskFileItemFactory factory = new DiskFileItemFactory();
        PhotoCar photo = null;
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        LOG.info("file " + repository.getName());
            factory.setRepository(repository);
            ServletFileUpload upload = new ServletFileUpload(factory);
            label : try {
                List<FileItem> items = upload.parseRequest(req);
                if (items.get(0).getName() == "") {
                    photoName = "null.jpg";
                    AdRepostiroty.instOf().savePhotoCar(new PhotoCar(photoName));
                    break label;
                }
                File folder = new File("images");
                if (!folder.exists()) {
                    folder.mkdir();
                }

                for (FileItem item : items) {
                    photo = AdRepostiroty.instOf().savePhotoCar(new PhotoCar(item.getName()));
                    photoName = photo.getName();

                    if (!item.isFormField()) {
                        File file = new File(folder + File.separator + item.getName());
                        try (FileOutputStream out = new FileOutputStream(file)) {
                            out.write(item.getInputStream().readAllBytes());
                        }
                    }
                }


            } catch (FileUploadException e) {
                e.printStackTrace();
            }

        req.getSession().setAttribute("photoCar", photoName);
        resp.sendRedirect(req.getContextPath() + "/addPost.do");
    }

}



