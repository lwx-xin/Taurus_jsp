package org.taurus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"org.taurus.dao"}) //扫描DAO
public class TaurusApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaurusApplication.class, args);
	}

}
