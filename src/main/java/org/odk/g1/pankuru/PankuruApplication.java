package org.odk.g1.pankuru;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//exclude = SecurityAutoConfiguration.class
@SpringBootApplication()
@EntityScan(basePackages = "org.odk.g1.pankuru.Entity")
@EnableJpaRepositories(basePackages = "org.odk.g1.pankuru.Repository")
public class PankuruApplication {

    public static void main(String[] args) {
        SpringApplication.run(PankuruApplication.class, args);
    }


}
