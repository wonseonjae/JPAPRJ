package kopo.poly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages={"kopo.poly.service"})
@SpringBootApplication
public class JpaprjApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaprjApplication.class, args);
    }

}
