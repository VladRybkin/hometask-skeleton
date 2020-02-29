package ua.training.spring.hometask.testconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


@Configuration
@Import(TestDataSourceBeans.class)
public class TestJdbcTemplateBeans {
    
    @Autowired
    @Bean
    public JdbcTemplate testJdbcTemplate(@Qualifier("h2DataSource") DataSource testDataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(testDataSource);

        return jdbcTemplate;
    }
}
