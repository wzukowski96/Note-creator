package wz.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class NoteCreatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(com.wz.demo.NoteCreatorApplication.class, args);
    }

}