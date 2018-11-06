package com.xing.wx.mp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.xing")
public class WxMpSpringbootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxMpSpringbootDemoApplication.class, args);
    }
}
