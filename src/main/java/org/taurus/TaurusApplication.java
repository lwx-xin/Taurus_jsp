package org.taurus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan(basePackages = {"org.taurus.dao"}) //扫描DAO
@ServletComponentScan
public class TaurusApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaurusApplication.class, args);
	}

}
