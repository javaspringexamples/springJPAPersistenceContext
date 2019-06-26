package javaspringexamples.springJPA.PersistenceContext;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @author mounir.sahrani@gmail.com
 *
 */
@Configuration
@EnableTransactionManagement
public class Conf {

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:tcp://localhost/~/javaspringexamples");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;
	}

	private Map<String, ?> jpaProperties() {
		Map<String, String> jpaPropertiesMap = new HashMap<String, String>();
		jpaPropertiesMap.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		jpaPropertiesMap.put("hibernate.hbm2ddl.auto", "update");
		return jpaPropertiesMap;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		factoryBean.setDataSource(dataSource());
		factoryBean.setPackagesToScan("javaspringexamples.springJPA.PersistenceContext");
		factoryBean.setJpaPropertyMap(jpaProperties());
		return factoryBean;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

	@Bean
	public PersonDAO personDao() {
		PersonDAOImpl dao = new PersonDAOImpl();
		return dao;
	}

}
