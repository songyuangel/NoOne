package pers.song.NoOne;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication(scanBasePackages={"pers.*.*"})
@RestController
@MapperScan({"pers.song.NoOne.dao","pers.song.NoOne.Blog.dao"})
public class SpringBootBaseApplication extends SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication.run(SpringBootBaseApplication.class, args);
	}

	@RequestMapping(value = "/",produces = "text/plain;charset=UTF-8")
    String index(){
        return "Hello Spring Boot!";
    }
	
	 @Override
	    protected SpringApplicationBuilder configure(
	            SpringApplicationBuilder application) {
	        return application.sources(SpringBootBaseApplication.class);
	    }
}
