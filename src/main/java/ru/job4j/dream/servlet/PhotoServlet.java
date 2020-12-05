package ru.job4j.dream.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.dream.model.Photo;
import ru.job4j.dream.store.PsqlStore;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class PhotoServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(PsqlStore.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (!id.equals("0")) {
            resp.setContentType("name=" + id);
            resp.setContentType("image/png");
            resp.setHeader("Content-Disposition", "attachment; filename=\"" + id + "\"");
            Photo photo = PsqlStore.instOf().findPhotoById(Integer.parseInt(id));
            try (ByteArrayInputStream in = new ByteArrayInputStream(photo.getImage())) {
                resp.getOutputStream().write(in.readAllBytes());
            } catch (IOException e) {
                LOGGER.error("Error during downloading photo", e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        byte[] image = null;
        try {
            List<FileItem> items = upload.parseRequest(req);
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    image = item.getInputStream().readAllBytes();
                }
            }
        } catch (FileUploadException e) {
            LOGGER.error("Error during uploading photo", e);
        }
        Photo photo = new Photo(image);
        PsqlStore.instOf().save(photo, Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect(req.getContextPath() + "/candidates.do");
    }
}
