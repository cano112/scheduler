package pl.edu.agh.wiet.studiesplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("pl.edu.agh.wiet.studiesplanner")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
