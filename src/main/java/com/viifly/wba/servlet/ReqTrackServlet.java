package com.viifly.wba.servlet;

import com.viifly.wba.service.MongoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class ReqTrackServlet extends HttpServlet{
    final static Logger logger = LoggerFactory.getLogger(ReqTrackServlet.class);

    @Autowired
    private MongoService mongoService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

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

        saveRequestInformation(req);

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

    private void saveRequestInformation(final HttpServletRequest req) {
        //mongoService.saveMap("test", ReqTrackDocumentBuilder.buildFromRequest(req));

        Observable<Map<String, String>> myObservable =  Observable.create(new Observable.OnSubscribe<Map<String, String>>(){

            @Override
            public void call(Subscriber<? super Map<String, String>> subscriber) {
                Map<String, String> data = ReqTrackDocumentBuilder.buildFromRequest(req);
                subscriber.onNext(data);
                subscriber.onCompleted();
            }
        });

        Action1<Map<String, String>> saveMongodbAction = new Action1<Map<String, String>>() {

            @Override
            public void call(Map<String, String> data) {
                mongoService.saveMap("test", data);
            }
        };

        myObservable.subscribe(saveMongodbAction);

    }
}
