package cl.duoc.colegio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BffApplication {

    public static void main(String[] args) {
        SpringApplication.run(BffApplication.class, args);
    }

    // ESTE BEAN ES OBLIGATORIO para que funcione @Autowired en el Controller
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}