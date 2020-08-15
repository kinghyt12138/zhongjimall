package com.example.zhongjimall.util;

import javax.servlet.http.HttpServletRequest;


public class MemberOnlineUtils {
    public static void memberOnline(HttpServletRequest request) throws Exception
    {

        String jsessionId = request.getRequestedSessionId();
        String ip = IpUtils.getIpAddr(request);
        String accept = request.getHeader("accept");
        String userAgent = request.getHeader("User-Agent");
        String url = request.getRequestURI();


        StringBuilder s = new StringBuilder();
        s.append(jsessionId);
        s.append(ip);
        s.append(accept);
        s.append(userAgent);
        s.append(url);
        s.append(request.getHeader("Referer"));

    }

}
