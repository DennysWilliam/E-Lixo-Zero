package br.fai.e.lixo.zero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ELixoZeroApplication {

    public static void main(String[] args) {
        SpringApplication.run(ELixoZeroApplication.class, args);
    }

}