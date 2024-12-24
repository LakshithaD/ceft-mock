package elysiancrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NsbCoreMain {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SpringApplication.run( NsbCoreMain.class, args);
    }
}