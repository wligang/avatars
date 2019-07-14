package com.wlgdo.avatar.common.utils;

import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/7/14 17:27
 */
public class GZIPUtils {
    public static final String GZIP_ENCODE_UTF_8 = "UTF-8";

    public static byte[] compress(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes(GZIP_ENCODE_UTF_8));
            gzip.close();
        } catch ( Exception e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

}
