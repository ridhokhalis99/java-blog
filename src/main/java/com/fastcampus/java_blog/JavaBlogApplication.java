package com.fastcampus.java_blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JavaBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaBlogApplication.class, args);
	}

}
