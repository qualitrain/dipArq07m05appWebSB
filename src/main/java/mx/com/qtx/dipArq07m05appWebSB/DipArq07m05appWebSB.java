package mx.com.qtx.dipArq07m05appWebSB;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.autoconfigure.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class DipArq07m05appWebSB {

	private static final Logger log = LoggerFactory.getLogger(DipArq07m05appWebSB.class);

	public static void main(String[] args) {
		SpringApplication.run(DipArq07m05appWebSB.class, args);
	}

	@Bean
	@Primary
	@ConfigurationProperties("spring.datasource.negocio")
	public DataSourceProperties negocioProperties() {
		return new DataSourceProperties();
	}

	@Bean
	@Primary
	public DataSource negocioDataSource(DataSourceProperties negocioProperties) {
		log.info("Creando DataSource para negocio");
		return negocioProperties.initializeDataSourceBuilder().build();
	}

}