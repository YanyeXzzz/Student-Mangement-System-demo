package com.yanye.springbootdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.FileChannel;

@SpringBootTest
class SpringbootDemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void saveMusic() {
        String songId = "1959528822";
        String songUrl = "http://music.163.com/song/media/outer/url?id=" + songId;
        String savePath = "songs";

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

            }

            outputStream.close();
            inputStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
