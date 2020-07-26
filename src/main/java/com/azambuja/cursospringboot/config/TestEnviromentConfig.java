package com.azambuja.cursospringboot.config;

import com.azambuja.cursospringboot.services.DatabaseService;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestEnviromentConfig {
  @Autowired
  private DatabaseService databaseService;

  @Bean
  public boolean instantiateDatabase() throws ParseException {
    databaseService.instantiateTestDatabase();

    return true;
  }
}
