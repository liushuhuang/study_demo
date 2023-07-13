package com.cn.liu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication

@ImportResource(locations = "classpath:applicationContext.xml")
@ComponentScan(basePackages = {"com.cn.liu.*","com.dyms.encrypt.*"})
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }

}
