package com.acorcs.wni;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
@MapperScan("com.acorcs.wni.mybatis.mapper")
public class AcorcsWniApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcorcsWniApplication.class, args);
	}
}
