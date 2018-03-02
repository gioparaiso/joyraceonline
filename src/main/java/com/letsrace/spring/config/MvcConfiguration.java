package com.letsrace.spring.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = "com.letsrace.spring")
@EnableWebMvc
@EnableTransactionManagement
@PropertySource({ "classpath:application.properties" })
public class MvcConfiguration extends WebMvcConfigurerAdapter {

	@Autowired
	private Environment environment;

	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");

		return resolver;
	}

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver getCommonsMultipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(20971520); // 20MB
		multipartResolver.setMaxInMemorySize(1048576); // 1MB

		// @MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, maxFileSize =
		// 1024 * 1024 * 50, maxRequestSize = 1024 * 1024 * 100)

		return multipartResolver;
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");

		return messageSource;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// registry.addResourceHandler("/resources/**").addResourceLocations(
		// "/resources/");
		registry.addResourceHandler(new String[] { "/css/**" })
				.addResourceLocations(new String[] { "/css/" });
		registry.addResourceHandler(new String[] { "/js/**" })
				.addResourceLocations(new String[] { "/js/" });
		registry.addResourceHandler(new String[] { "/img/**" })
				.addResourceLocations(new String[] { "/img/" });
		registry.addResourceHandler(new String[] { "/profilepics/**" })
				.addResourceLocations(new String[] { "/profilepics/" });
	}

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/joyracedb");
		dataSource.setUsername("user7CB");
		dataSource.setPassword("LpkN2LufloqdyJPy");

// 		dataSource.setDriverClassName(this.environment
// 				.getRequiredProperty("jdbc.driverClassName"));
// 		dataSource.setUrl(this.environment.getRequiredProperty("jdbc.url"));
// 		dataSource.setUsername(this.environment
// 				.getRequiredProperty("jdbc.username"));
// 		dataSource.setPassword(this.environment
// 				.getRequiredProperty("jdbc.password"));

		return dataSource;
	}

	// @Bean
	// public UserDAO getUserDAO() {
	// return new UserDAOImpl(getDataSource());
	// }

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory s) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource());
		sessionFactory
				.setPackagesToScan(new String[] { "com.letsrace.spring.model" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect",
				this.environment.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql",
				this.environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql",
				this.environment.getRequiredProperty("hibernate.format_sql"));
		return properties;
	}

}
