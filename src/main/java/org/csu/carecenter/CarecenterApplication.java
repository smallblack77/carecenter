package org.csu.carecenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.csu.carecenter.Persistence")
public class CarecenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarecenterApplication.class, args);
    }


}
