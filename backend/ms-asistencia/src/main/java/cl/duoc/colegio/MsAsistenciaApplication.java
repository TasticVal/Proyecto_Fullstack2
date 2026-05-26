package cl.duoc.colegio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "cl.duoc.colegio")
public class MsAsistenciaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsAsistenciaApplication.class, args);
    }
}