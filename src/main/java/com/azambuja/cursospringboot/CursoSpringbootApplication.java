package com.azambuja.cursospringboot;

import com.azambuja.cursospringboot.domain.*;
import com.azambuja.cursospringboot.domain.enums.ClientType;
import com.azambuja.cursospringboot.domain.enums.PaymentState;
import com.azambuja.cursospringboot.repository.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursoSpringbootApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(CursoSpringbootApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {}
}
