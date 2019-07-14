package com.wlgdo.avatar.web.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/7/14 17:30
 */
public class GZIPRequestWrapper extends HttpServletRequestWrapper {

    private HttpServletRequest request;
    private static final Logger LOGGER = LoggerFactory.getLogger(GZIPRequestWrapper.class);

    public GZIPRequestWrapper(HttpServletRequest request) {
        super(request);
        this.request = request;
    }


    @Override
    public ServletInputStream getInputStream() throws IOException {

        ServletInputStream stream = request.getInputStream();

        String contentEncoding = request.getHeader("Content-Encoding");
        // 如果对内容进行了压缩，则解压
        if (null != contentEncoding && contentEncoding.indexOf("gzip") != -1) {
            try {
                final GZIPInputStream gzipInputStream = new GZIPInputStream(
                        stream); //将body的流通过GZIPInputStream解压
                // 因为请求body中的流已经被读完了，所以这里需要将流重新写回去，不然在Controller接收RequestBody时就因为流被读完获取不到数据
                ServletInputStream newStream = new ServletInputStream() {

                    @Override
                    public int read() throws IOException {
                        return gzipInputStream.read();
                    }

                    @Override
                    public boolean isFinished() {
                        return false;
                    }

                    @Override
                    public boolean isReady() {
                        return false;
                    }

                    @Override
                    public void setReadListener(ReadListener arg0) {
                        // TODO Auto-generated method stub

                    }
                };
                return newStream;
            } catch (Exception e) {
                LOGGER.debug("ungzip fail", e);
            }
        }
        return stream;
    }

}
