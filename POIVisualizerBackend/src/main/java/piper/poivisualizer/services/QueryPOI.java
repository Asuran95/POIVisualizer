package piper.poivisualizer.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import piper.poivisualizer.application.ApplicationMain;
import piper.poivisualizer.database.eao.PoiEAO;
import piper.poivisualizer.database.eao.VehicleEAO;
import piper.poivisualizer.database.eao.VehiclePostionEAO;
import piper.poivisualizer.database.entities.POI;
import piper.poivisualizer.database.entities.Vehicle;
import piper.poivisualizer.database.entities.VehiclePosition;
import piper.poivisualizer.resources.dto.POIResultDTO;
import piper.poivisualizer.resources.dto.POIVisualizerFilterDTO;
import piper.poivisualizer.resources.dto.PoiDTO;
import piper.poivisualizer.resources.dto.VehiclePoiDTO;
import piper.poivisualizer.utils.CoordinatesUtil;

@Component
public class QueryPOI {

	@Autowired
	private PoiEAO poiEAO;
	
	@Autowired
	private VehicleEAO vehicleEAO;
	
	@Autowired
	private VehiclePostionEAO vehiclePositionEAO;
	
	private Logger logger = LoggerFactory.getLogger(ApplicationMain.class);

	public POIResultDTO queryAllVehicles() {
		
		List<POI> pois = poiEAO.findAll();
		
		List<Vehicle> vehicles = vehicleEAO.findAll();
		
		List<PoiDTO> poiDTOs = new ArrayList<>();
			
		for(POI poi : pois) {
			
			PoiDTO poiDto = new PoiDTO();
			
			poiDto.setName(poi.getName());
			poiDto.setLatitude(poi.getLatitude());
			poiDto.setLongitude(poi.getLongitude());
			poiDto.setRaio(poi.getRaio());
			
			double poiLatitude = poi.getLatitude();
			double poiLongitude = poi.getLongitude();
			
			List<VehiclePoiDTO> vehiclePoiDTOs = new ArrayList<>();
			
			for(Vehicle vehicle : vehicles) {
				
				List<VehiclePosition> vehiclePositions = vehiclePositionEAO.findByVehicleOrderByDateAsc(vehicle);
				
				long firstCaptureInPoi = 0;
				long lastCapture = 0;
				
				long totalTimeInPoi = 0;
				
				for(VehiclePosition vehiclePosition : vehiclePositions) {
					
					double vehicleLatitude = vehiclePosition.getLatitude();
					double vehicleLongitude = vehiclePosition.getLongitude();
					
					double distance = CoordinatesUtil
						.getDistanceBetweenTwoPointsInMeters(vehicleLatitude, vehicleLongitude, poiLatitude, poiLongitude);
					
					lastCapture = vehiclePosition.getDate().getTime();
					
					if(poi.getRaio() > distance) {
						
						if(firstCaptureInPoi == 0) {
							firstCaptureInPoi = lastCapture;
						}
					} else {
						
						if(firstCaptureInPoi != 0) {
							totalTimeInPoi += (lastCapture - firstCaptureInPoi);
							
							firstCaptureInPoi = 0;
						}
					}
				}
				
				if(firstCaptureInPoi != 0) {
					totalTimeInPoi += (lastCapture - firstCaptureInPoi);
					
					firstCaptureInPoi = 0;
				}
				
				VehiclePoiDTO vehiclePoiDTO = new VehiclePoiDTO();
				
				vehiclePoiDTO.setPlaca(vehicle.getPlaca());
				vehiclePoiDTO.setTempoMilliseconds(totalTimeInPoi);
				
				vehiclePoiDTOs.add(vehiclePoiDTO);
			}
			
			poiDto.setVehicles(vehiclePoiDTOs);
			
			poiDTOs.add(poiDto);
		}
		
		POIResultDTO poiResultDto = new POIResultDTO();
		
		poiResultDto.setPoisDto(poiDTOs);
		
		return poiResultDto;
	}
	
	public POIResultDTO queryByVehicleAndBetweenDate(POIVisualizerFilterDTO filter) {
		
		List<POI> pois = poiEAO.findAll();
		
		List<Vehicle> vehicles = vehicleEAO.findAllVehicleByPlaca(filter.getPlaca());
		
		List<PoiDTO> poiDTOs = new ArrayList<>();
			
		for(POI poi : pois) {
			
			PoiDTO poiDto = new PoiDTO();
			
			poiDto.setName(poi.getName());
			poiDto.setLatitude(poi.getLatitude());
			poiDto.setLongitude(poi.getLongitude());
			poiDto.setRaio(poi.getRaio());
			
			double poiLatitude = poi.getLatitude();
			double poiLongitude = poi.getLongitude();
			
			List<VehiclePoiDTO> vehiclePoiDTOs = new ArrayList<>();
			
			for(Vehicle vehicle : vehicles) {
				
				List<VehiclePosition> vehiclePositions = vehiclePositionEAO.findByVehicleAndDateBetweenOrderByDateAsc(vehicle, filter.getDataInicial(), filter.getDataFinal());
				
				long firstCaptureInPoi = 0;
				long lastCapture = 0;
				
				long totalTimeInPoi = 0;
				
				for(VehiclePosition vehiclePosition : vehiclePositions) {
					
					double vehicleLatitude = vehiclePosition.getLatitude();
					double vehicleLongitude = vehiclePosition.getLongitude();
					
					double distance = CoordinatesUtil
						.getDistanceBetweenTwoPointsInMeters(vehicleLatitude, vehicleLongitude, poiLatitude, poiLongitude);
					
					lastCapture = vehiclePosition.getDate().getTime();
					
					if(poi.getRaio() > distance) {
						
						if(firstCaptureInPoi == 0) {
							firstCaptureInPoi = lastCapture;
						}
					} else {
						
						if(firstCaptureInPoi != 0) {
							totalTimeInPoi += (lastCapture - firstCaptureInPoi);
							
							firstCaptureInPoi = 0;
						}
					}
				}
				
				if(firstCaptureInPoi != 0) {
					totalTimeInPoi += (lastCapture - firstCaptureInPoi);
					
					firstCaptureInPoi = 0;
				}
				
				VehiclePoiDTO vehiclePoiDTO = new VehiclePoiDTO();
				
				vehiclePoiDTO.setPlaca(vehicle.getPlaca());
				vehiclePoiDTO.setTempoMilliseconds(totalTimeInPoi);
				
				vehiclePoiDTOs.add(vehiclePoiDTO);
			}
			
			poiDto.setVehicles(vehiclePoiDTOs);
			
			poiDTOs.add(poiDto);
		}
		
		POIResultDTO poiResultDto = new POIResultDTO();
		
		poiResultDto.setPoisDto(poiDTOs);
		
		return poiResultDto;
	}
	
}
