package com.example.houseofprayerlogistics.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BootstrapFactory {

  @Bean
  public ModelMapper getModelMapper(){
    return new ModelMapper();
  }
}
