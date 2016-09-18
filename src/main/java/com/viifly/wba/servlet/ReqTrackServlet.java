package com.viifly.wba.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

import com.viifly.wba.service.MongoService;
import com.viifly.wba.service.ServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReqTrackServlet extends HttpServlet{
    final static Logger logger = LoggerFactory.getLogger(ReqTrackServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.getCookies();
        Enumeration<String> enumeration =  req.getHeaderNames();

        StringBuilder sb = new StringBuilder();
        sb.append("getRequestURI: ").append(req.getRequestURI()).append("\n");
        sb.append("getPathInfo: ").append(req.getPathInfo()).append("\n");
        sb.append("getContextPath: ").append(req.getContextPath()).append("\n");
        sb.append("getServletPath: ").append(req.getServletPath()).append("\n");
        sb.append("getRequestURL: ").append(req.getRequestURL()).append("\n");
        sb.append("getRemoteAddr: ").append(req.getRemoteAddr()).append("\n");

        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = req.getHeader(name);
            sb.append(name).append(":").append(value).append("\n");
        }
        logger.debug("Request information: {}", sb.toString());

        MongoService mongoService = ServiceManager.getInstance().getMongoService();
        mongoService.saveMap("test", ReqTrackDocumentBuilder.buildFromRequest(req));

        // check refer
        String ref = req.getHeader("Referer");
        if (ref != null && !ref.equalsIgnoreCase(req.getRequestURL().toString())) {
            // redirect to refer page
            resp.sendRedirect(ref);
        } else {
            resp.setContentType("text/plain;charset=utf-8");
            resp.getOutputStream().write(sb.toString().getBytes());
        }

    }
}
