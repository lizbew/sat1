package com.viifly.wba.servlet;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class ReqTrackDocumentBuilder {
    public static Map<String, String> buildFromRequest(HttpServletRequest req) {
        Map<String, String> data = new HashMap<>();

        Enumeration<String> enumeration =  req.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = req.getHeader(name);
            data.put(name, value);
        }
        data.put("RequestURL", req.getRequestURL().toString());
        data.put("RemoteAddr", req.getRemoteAddr());
        return data;
    }
}
