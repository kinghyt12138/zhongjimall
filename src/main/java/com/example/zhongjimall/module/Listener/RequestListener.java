package com.example.zhongjimall.module.Listener;


import com.example.zhongjimall.entity.sys.RequestLog;
import com.example.zhongjimall.repository.sys.RequestLogRepository;
import com.example.zhongjimall.util.IpUtils;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@WebListener
public class RequestListener implements ServletRequestListener {
    @Autowired
    private RequestLogRepository requestLogRepository;
@Autowired
        private HttpSession session  ;

    @Override
    public void requestDestroyed(ServletRequestEvent arg0) {
        System.out.println("监听器初始化...");
        Integer count = null;//请求数量
        Object requestCount = arg0.getServletContext().getAttribute("requestCount");
        if (requestCount == null) {
            count = 0;
        } else {
            count = Integer.valueOf(requestCount.toString());
        }
        count++;
        System.out.println("当前请求数：" + count.toString());
        arg0.getServletContext().setAttribute("requestCount", count);
        /*用户信息*/





        /*用户信息*/
        HttpServletRequest servletRequest = (HttpServletRequest) arg0.getServletRequest();
        //requestURL
        String requestURL = servletRequest.getRequestURL().toString();
        //queryString
        String queryString = servletRequest.getQueryString();

        RequestLog requestLog = new RequestLog();
        requestLog.setIpaddr(IpUtils.getIpAddr(servletRequest));
        requestLog.setSessionId(servletRequest.getRequestedSessionId());
        requestLog.setReferer(servletRequest.getHeader("Referer"));
        requestLog.setAccept(servletRequest.getHeader("accept"));
        requestLog.setMethod(servletRequest.getMethod());
        requestLog.setUrl(requestURL);
        requestLog.setQuerystring(queryString);
        /*UserAgent 工具类*/
        UserAgent userAgent = UserAgent.parseUserAgentString(servletRequest.getHeader("User-Agent"));
        Browser browser = userAgent.getBrowser();
        Version version = browser.getVersion(servletRequest.getHeader("User-Agent"));

        String browserInfo = null;
        try {
            browserInfo = browser.getName() + "/" + version.getVersion();
        } catch (Exception e) {
            e.printStackTrace();
        }

        OperatingSystem os = userAgent.getOperatingSystem();

        /*UserAgent 工具类*/
        requestLog.setOs(os.getName());
        requestLog.setBrowser(browserInfo);
        requestLog.setCreationTimes(System.currentTimeMillis());


/*        SecurityContextImpl securityContextImpl = (SecurityContextImpl) servletRequest.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        requestLog.setLoginName( securityContextImpl.getAuthentication().getName());*/
        requestLogRepository.save(requestLog);
    }

    @Override
    public void requestInitialized(ServletRequestEvent sce) {
        System.out.println("监听器销毁...");
    }


}
