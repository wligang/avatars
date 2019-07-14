package com.wlgdo.avatar.web.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/7/14 17:33
 */
@WebFilter(filterName = "GZIPFilter", urlPatterns = "/gzip/*")
@Order(1)
public class GZIPFilter implements Filter {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        logger.info("获得请求：{}", request);
        chain.doFilter(new GZIPRequestWrapper((HttpServletRequest) request), response);
    }

    @Override
    public void destroy() {
    }
}
