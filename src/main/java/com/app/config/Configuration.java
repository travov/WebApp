package com.app.config;

import dao.BooksDao;
import dao.BooksDaoImpl;
import model.Book;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import service.BookService;
import service.BookServiceImpl;
import javax.sql.DataSource;
import java.util.Properties;

@org.springframework.context.annotation.Configuration
@ComponentScans(value = {
    @ComponentScan("com"),
    @ComponentScan("dao"),
   @ComponentScan("service")})
@EnableTransactionManagement
@EnableWebMvc
public class Configuration extends WebMvcConfigurerAdapter{

    @Bean
    public ViewResolver getViewResolver(){
        InternalResourceViewResolver vr = new InternalResourceViewResolver();
        vr.setViewClass(JstlView.class);
        vr.setPrefix("/WEB-INF/");
        vr.setSuffix(".jsp");
        return vr;
    }


    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/js/**").addResourceLocations("/static/js/");
    }

    @Bean
    public DataSource getDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;

    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory(){
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(getDataSource());
        Properties properties = new Properties();
        properties.put(AvailableSettings.SHOW_SQL, true);
        properties.put(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQLDialect");
        properties.put(AvailableSettings.HBM2DDL_AUTO, "update");
        factoryBean.setHibernateProperties(properties);
        factoryBean.setAnnotatedClasses(Book.class);

        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager(){
        HibernateTransactionManager manager = new HibernateTransactionManager();
        manager.setSessionFactory(getSessionFactory().getObject());
        return manager;
    }

    @Bean
    public BooksDao getBooksDao(){
        BooksDao booksDao = new BooksDaoImpl();
        return booksDao;
    }

    @Bean
    public BookService getBookService(){
        BookService bookService = new BookServiceImpl();
        bookService.setBooksDao(getBooksDao());
        return bookService;
    }
}
