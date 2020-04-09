package ua.training.spring.hometask.testconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@Import({TestDataSourceBeans.class,})
public class TestsSessionFactoryBeans {

    @Autowired
    @Bean
    public LocalSessionFactoryBean testSessionFactory(@Qualifier("h2DataSource") DataSource testDataSourceDataSource) {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(testDataSourceDataSource);
        localSessionFactoryBean.setPackagesToScan("ua.training.spring.hometask.domain");

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.show_sql", Boolean.TRUE.toString());
        properties.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.jcache.JCacheRegionFactory");
        properties.setProperty("hibernate.javax.cache.provider", "org.ehcache.jsr107.EhcacheCachingProvider");
        properties.setProperty("hibernate.cache.use_query_cache", Boolean.TRUE.toString());
        properties.setProperty("hibernate.cache.use_second_level_cache",Boolean.TRUE.toString());
        properties.setProperty("hibernate.generate_statistics", Boolean.TRUE.toString());

        localSessionFactoryBean.setHibernateProperties(properties);

        return localSessionFactoryBean;
    }


    @Autowired
    @Bean
    public PlatformTransactionManager testHibernateTransactionManager(@Qualifier("h2DataSource") DataSource testDataSource) {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(testSessionFactory(testDataSource).getObject());

        return transactionManager;
    }

}
