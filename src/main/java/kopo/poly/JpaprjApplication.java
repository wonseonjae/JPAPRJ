package kopo.poly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class JpaprjApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaprjApplication.class, args);
    }

}
