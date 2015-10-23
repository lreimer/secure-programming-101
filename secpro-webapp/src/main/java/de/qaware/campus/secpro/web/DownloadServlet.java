package de.qaware.campus.secpro.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

/**
 * A very insecure resource servlet. DO NOT COPY!!!
 * <p>
 * Since the src path request parameter is not normalized or sanitized,
 * this code may be able to break out if the web application into the
 * filesystem and possibly download arbitrary files.
 *
 * @author mario-leander.reimer
 */
@WebServlet(name = "DownloadServlet", urlPatterns = "/download")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String src = req.getParameter("src");

        File file = new File(getServletContext().getRealPath("/"), "/WEB-INF/resources/" + src);
        if (file.exists() && file.canRead() && file.isFile()) {
            FileInputStream inputStream = new FileInputStream(file);
            try {
                Files.copy(file.toPath(), resp.getOutputStream());
            } finally {
                inputStream.close();
            }

        } else {
            resp.sendError(404);
        }
    }
}
