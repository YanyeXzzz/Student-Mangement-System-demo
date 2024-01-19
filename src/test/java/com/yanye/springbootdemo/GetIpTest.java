package com.yanye.springbootdemo;

import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class GetIpTest {


    @Test
    public void GetLocalIpAddress() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            String ipAddress = localHost.getHostAddress();

            FileWriter fileWriter = new FileWriter("src/main/resources/static/text/ipAddress.text", false);
            fileWriter.write("");
            System.out.println("文本文件已清空");

            Path path = Path.of("src/main/resources/static/text/ipAddress.text");
            Files.writeString(path, "aaa", StandardOpenOption.CREATE);
            System.out.println("内容已成功写入文件");
        } catch (UnknownHostException e) {
            e.printStackTrace();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
