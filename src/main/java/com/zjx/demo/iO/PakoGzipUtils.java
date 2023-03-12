package com.zjx.demo.iO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author zjx
 */
public class PakoGzipUtils {

    /**

     * @param str：正常的字符串

     * @return 压缩字符串 类型为：  ³)°K,NIc i£_`Çe#  c¦%ÂXHòjyIÅÖ`

     * @throws IOException

     */

    public static String compress(String str)throws IOException {

        if (str ==null || str.length() ==0) {

            return str;

        }

        ByteArrayOutputStream out =new ByteArrayOutputStream();

        GZIPOutputStream gzip =new GZIPOutputStream(out);

        gzip.write(str.getBytes());

        gzip.close();

        return out.toString("ISO-8859-1");

    }

    public static byte[] uncompress(byte[] bytes) {

        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            GZIPInputStream ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];

            int n;
            while ((n = ungzip.read(buffer)) >= 0) {

                out.write(buffer, 0, n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }


}

