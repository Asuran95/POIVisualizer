package piper.poivisualizer.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import piper.poivisualizer.database.eao.VehicleEAO;
import piper.poivisualizer.database.entities.Vehicle;

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
