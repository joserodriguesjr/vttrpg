package com.vttrpg.RPG;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
@EnableJpaAuditing
public class RpgApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpgApplication.class, args);
    }

}
