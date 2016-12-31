package jtk.springboot.mysql.testdB.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Created by jubin on 29/12/16.
 */
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager",
        basePackages = {"jtk.springboot.mysql.testdB.entities"})
@Configuration
public class MySqlTestDbDataSourceConfig {

    @Value("${spring.ds_mysql.testdb.url}")
    private String url;
    @Value("${spring.ds_mysql.username}")
    private String username;
    @Value("${spring.ds_mysql.password}")
    private String password;
    @Value("${spring.ds_mysql.driverClassName}")
    private String driverClassName;

    @Autowired
    private JpaVendorAdapter jpaVendorAdapter;

    @Primary
    @Bean(name = "mysqlDb")
    public DataSource mysqlDataSource() throws Exception {
        ComboPooledDataSource ds = new ComboPooledDataSource();
        ds.setJdbcUrl(url+"?user="+username+"&password="+password);
        ds.setDriverClass(driverClassName);
        return ds;
    }
    @Bean(name = "mysqlJdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("mysqlDb") DataSource dsMySQL) {
        return new JdbcTemplate(dsMySQL);
    }


    @Bean(name = "entityManager")
    public EntityManager entityManager() throws Exception {
        return entityManagerFactory().createEntityManager();
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory entityManagerFactory() throws Exception {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(mysqlDataSource());
        emf.setJpaVendorAdapter(jpaVendorAdapter);
        emf.setPackagesToScan("jtk.springboot.mysql.testdB.entities");
        emf.setPersistenceUnitName("default");   // <- giving 'default' as name
        emf.afterPropertiesSet();
        return emf.getObject();
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager() throws Exception {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(entityManagerFactory());
        return tm;
    }

}
