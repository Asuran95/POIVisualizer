package piper.poivisualizer.application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import piper.poivisualizer.database.eao.CSVHashEAO;
import piper.poivisualizer.database.eao.PoiEAO;
import piper.poivisualizer.database.eao.VehicleEAO;
import piper.poivisualizer.database.eao.VehiclePostionEAO;
import piper.poivisualizer.database.entities.CSVHash;
import piper.poivisualizer.database.entities.POI;
import piper.poivisualizer.database.entities.Vehicle;
import piper.poivisualizer.database.entities.VehiclePosition;
import piper.poivisualizer.utils.FileHashUtil;

@Component
public class CSVLoader {
	
	@Autowired
	private VehicleEAO vehicleEao;
	
	@Autowired
	private VehiclePostionEAO vehiclePostionEAO;
	
	@Autowired
	private PoiEAO poiEAO;
	
	@Autowired
	private CSVHashEAO csvHashEAO;
	
	private Logger logger = LoggerFactory.getLogger(ApplicationMain.class);
	
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() throws FileNotFoundException, IOException, CsvException, NoSuchAlgorithmException, ParseException {
		
		try {
			String fileNamePoi = "csv/base_pois_def.csv";
			
			String hash = FileHashUtil.getHashFile(fileNamePoi);
			
			CSVHash csvHash = new CSVHash();
			
			csvHash.setFileName(fileNamePoi);
			csvHash.setHash(hash);
			
			csvHashEAO.save(csvHash);
			
			loadCSVtoPOIJpa(fileNamePoi);
				
		} catch(DataIntegrityViolationException ex) {
			logger.info("CSV Já foi carregado - POI");
		}
		
		try {
			String fileNameVehiclePostion = "csv/posicoes.csv";
			
			String hash = FileHashUtil.getHashFile(fileNameVehiclePostion);
			
			CSVHash csvHash = new CSVHash();
			
			csvHash.setFileName(fileNameVehiclePostion);
			csvHash.setHash(hash);
			
			csvHashEAO.save(csvHash);
			
			loadCSVtoVehiclePostionJpa(fileNameVehiclePostion);
				
		} catch(DataIntegrityViolationException ex) {
			logger.info("CSV Já foi carregado - Veiculos e Posições");
		}
		
	}
	
	private void loadCSVtoPOIJpa(String fileNamePoi) throws FileNotFoundException, IOException, CsvException {
		List<String[]> csvFile;
		
		try (CSVReader reader = new CSVReader(new FileReader(fileNamePoi))) {
            csvFile = reader.readAll();
            csvFile.forEach(x -> System.out.println(Arrays.toString(x)));
        }
		
		for(int i=1; i<csvFile.size(); i++) {
			String name = csvFile.get(i)[0];
			
			int raio = Integer.parseInt(csvFile.get(i)[1]);
			
			double latitude = Double.parseDouble(csvFile.get(i)[2]);
			
			double longitude = Double.parseDouble(csvFile.get(i)[3]);
			
			POI poi = new POI();
			
			poi.setName(name);
			poi.setRaio(raio);
			poi.setLatitude(latitude);
			poi.setLongitude(longitude);
			
			poiEAO.save(poi);
		}
	}
	
	private void loadCSVtoVehiclePostionJpa(String fileNameVehiclePostion) throws FileNotFoundException, IOException, CsvException, ParseException {
		List<String[]> csvFile;
		
		try (CSVReader reader = new CSVReader(new FileReader(fileNameVehiclePostion))) {
            csvFile = reader.readAll();
            csvFile.forEach(x -> System.out.println(Arrays.toString(x)));
        }
		
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy hh:mm:ss", Locale.ENGLISH);
		
		for(int i=1; i<csvFile.size(); i++) {
			String placa = csvFile.get(i)[0];
			
			Vehicle veiculo = vehicleEao.findVehicleByPlaca(placa);
			
			if(veiculo == null) {
				
				veiculo = new Vehicle();
				
				veiculo.setPlaca(placa);
				
				vehicleEao.save(veiculo);
			}
			
			String dataGmt = csvFile.get(i)[1];		
			
			Date dataParsed = sdf.parse(dataGmt);
			
			int speed = Integer.parseInt(csvFile.get(i)[2]);
			
			double longitude = Double.parseDouble(csvFile.get(i)[3]);
			
			double latitude = Double.parseDouble(csvFile.get(i)[4]);
			
			boolean ignition = Boolean.parseBoolean(csvFile.get(i)[5]);
			
			VehiclePosition posicaoVeiculo = new VehiclePosition();
			
			posicaoVeiculo.setVehicle(veiculo);
			posicaoVeiculo.setDate(dataParsed);
			posicaoVeiculo.setSpeed(speed);
			posicaoVeiculo.setLatitude(latitude);
			posicaoVeiculo.setLongitude(longitude);
			posicaoVeiculo.setIgnition(ignition);
			
			vehiclePostionEAO.save(posicaoVeiculo);
		}
	}
}
