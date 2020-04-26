package ua.training.spring.hometask.testconfig;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

@Configuration
@Import(TestDataSourceBeans.class)
@MapperScan(value = {"ua.training.spring.hometask.dao.impl.mybatis.mybatisrepos"}, sqlSessionFactoryRef="testSqlSessionFactory")
public class TestMyBatisBeans {

    @Bean
    @Autowired
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("h2DataSource")DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);

        return factoryBean.getObject();
    }
}
