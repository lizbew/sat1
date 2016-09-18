package com.viifly.wba.servlet;

import com.viifly.wba.service.CounterService;
import com.viifly.wba.service.MongoService;
import com.viifly.wba.service.ServiceManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class PageViewCounterServlet extends HttpServlet {
    public static final String HEADER_REFERER = "Referer";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageUrl = req.getHeader(HEADER_REFERER);
        if (pageUrl == null) {
            resp.setContentType("application/x-javascript; charset=utf-8");
            resp.getOutputStream().write("window.papa_pv=1".getBytes());
            return;
        }

        MongoService mongoService = ServiceManager.getInstance().getMongoService();
        //int count = mongoService.increasePageView(pageUrl);
        mongoService.saveMap("test", ReqTrackDocumentBuilder.buildFromRequest(req));
        CounterService counterService = ServiceManager.getInstance().getCounterService();
        long count = counterService.increaseAndGetCount(pageUrl);

        resp.setContentType("application/x-javascript; charset=utf-8");
        resp.getOutputStream().write(("window.papa_pv="+ count).getBytes());
    }
}
