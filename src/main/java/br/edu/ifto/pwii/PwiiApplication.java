package br.edu.ifto.pwii;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PwiiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PwiiApplication.class, args);
    }

}
