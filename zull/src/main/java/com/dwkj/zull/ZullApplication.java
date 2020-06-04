package com.dwkj.zull;

import com.dwkj.zull.filter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
public class ZullApplication {


    /*@Bean
	public AccessFilter accessFilter(){
		return new AccessFilter();
	}*/

    public static void main(String[] args) {
        SpringApplication.run(ZullApplication.class, args);
    }

}
