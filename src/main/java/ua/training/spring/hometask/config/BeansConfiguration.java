package ua.training.spring.hometask.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableAspectJAutoProxy
@PropertySource("classpath:local.properties")
@ComponentScan(basePackages = "ua.training.spring.hometask")
public class BeansConfiguration {

}
