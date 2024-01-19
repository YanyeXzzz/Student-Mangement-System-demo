package com.yanye.springbootdemo;

import com.yanye.springbootdemo.util.GetLocalAddr;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootDemoApplication {

    public static void main(String[] args) {
        //启动项目前获取本地局域网ip
        GetLocalAddr getLocalAddr = new GetLocalAddr();
        getLocalAddr.getLocalIpAddress("src/main/resources/static/text/ipAddress.text");

        SpringApplication.run(SpringbootDemoApplication.class, args);
    }

}
