package com.yanye.springbootdemo.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.FileChannel;

public class SpiderUtil {

    /**
     *
     * @param songUrl 歌曲链接
     * @param savePath 保存路径
     * @return 0-下载成功 -1-下载失败
     */
    public static int saveSongs(String songUrl, String savePath) {
        try {
            URL url = new URL(songUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStream inputStream = connection.getInputStream();
            FileOutputStream outputStream = new FileOutputStream(savePath);

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            FileChannel channel = outputStream.getChannel();
            if (channel.size() == 0) {
                return -1;
            }

            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
