package piper.poivisualizer.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationMain {

	public static void main(String[] args) {

		Logger logger = LoggerFactory.getLogger(ApplicationMain.class);

		for (String arg : args) {
			logger.info("Argument: " + arg);
		}

		SpringApplication.run(ApplicationMain.class, args);
	}
}
