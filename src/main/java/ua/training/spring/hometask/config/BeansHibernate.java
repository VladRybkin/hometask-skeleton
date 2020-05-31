package ua.training.spring.hometask.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@Profile("HIBERNATE")
public class BeansHibernate {

    @Value("${hibernate.dialect}")
    private String hibernateDialect;

    @Value("${hibernate.cache.region.factory_class}")
    private String hibernateCacheRegionFactoryClass;

    @Value("${hibernate.javax.cache.provider}")
    private String hibernateJavaxCacheProvider;

    @Value("${hibernate.cache.use_query_cache}")
    private String hibernateCacheUseQueryCache;

    @Value("${hibernate.cache.use_second_level_cache}")
    private String hibernateCasheUseSecondLevelCache;

    @Value("${hibernate.generate_statistics}")
    private String hibernateGenerateStatistics;

    @Autowired
    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setPackagesToScan("ua.training.spring.hometask.domain");

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", hibernateDialect);
        properties.setProperty("hibernate.cache.region.factory_class", hibernateCacheRegionFactoryClass);
        properties.setProperty("hibernate.javax.cache.provider", hibernateJavaxCacheProvider);
        properties.setProperty("hibernate.cache.use_query_cache", hibernateCacheUseQueryCache);
        properties.setProperty("hibernate.cache.use_second_level_cache", hibernateCasheUseSecondLevelCache);
        properties.setProperty("hibernate.generate_statistics", hibernateGenerateStatistics);
        localSessionFactoryBean.setHibernateProperties(properties);

        return localSessionFactoryBean;
    }

    @Autowired
    @Bean
    @Primary
    public PlatformTransactionManager hibernateTransactionManager(DataSource dataSource) {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory(dataSource).getObject());

        return transactionManager;
    }
}
