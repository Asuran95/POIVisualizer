package piper.poivisualizer.application;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import piper.poivisualizer.BasePackageClass;

@EnableJpaRepositories(basePackageClasses = BasePackageClass.class)
@EntityScan(basePackageClasses = BasePackageClass.class)
@Configuration
public class DataSourceSetup {
	
	@Bean
	public DataSource dataSource() {
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUsername("wildfly");
		dataSource.setPassword("teste");
		dataSource.setUrl("jdbc:mysql://localhost:3306/POIVisualizerDB?createDatabaseIfNotExist=true&Timezone=true&serverTimezone=America/Sao_Paulo");

		return dataSource;
	}
}
