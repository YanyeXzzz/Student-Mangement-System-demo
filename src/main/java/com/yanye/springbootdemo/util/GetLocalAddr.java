package com.yanye.springbootdemo.util;

import java.io.FileWriter;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class GetLocalAddr {

    public void getLocalIpAddress(String pathName) {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            String ipAddress = localHost.getHostAddress();

            FileWriter fileWriter = new FileWriter("src/main/resources/static/text/ipAddress.text", false);
            fileWriter.write("");
            System.out.println("文本文件已清空");

            Path path = Path.of(pathName);
            Files.writeString(path, ipAddress, StandardOpenOption.CREATE);
            System.out.println("内容已成功写入文件");
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
