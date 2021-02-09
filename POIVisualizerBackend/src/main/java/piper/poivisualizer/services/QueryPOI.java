package piper.poivisualizer.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import piper.poivisualizer.database.eao.PoiEAO;
import piper.poivisualizer.database.eao.VehicleEAO;
import piper.poivisualizer.database.eao.VehiclePostionEAO;
import piper.poivisualizer.database.entities.POI;
import piper.poivisualizer.database.entities.Vehicle;
import piper.poivisualizer.database.entities.VehiclePosition;
import piper.poivisualizer.exceptions.InvalidPOIException;
import piper.poivisualizer.resources.dto.POIResultDTO;
import piper.poivisualizer.resources.dto.POIVisualizerFilterDTO;
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
	
	//private Logger logger = LoggerFactory.getLogger(ApplicationMain.class);

	public POIResultDTO queryByVehicleAndBetweenDate(POIVisualizerFilterDTO filter) {
		
		if(filter.getPoidto() == null) {
			throw new InvalidPOIException();
		} 
		
		Optional<POI> poiOptional = poiEAO.findById(filter.getPoidto().getId());
		
		if(!poiOptional.isPresent()) {
			throw new InvalidPOIException();
		}
		
		POI poi = poiOptional.get();
		
		List<Vehicle> vehicles;
		
		if(filter.getPlaca() == null || filter.getPlaca().isBlank()) {
			vehicles = vehicleEAO.findAll();	
		} else {
			vehicles = vehicleEAO.findAllVehicleByPlaca(filter.getPlaca());
		}
			
		POIResultDTO poiDto = new POIResultDTO();
		
		poiDto.setName(poi.getName());
		poiDto.setLatitude(poi.getLatitude());
		poiDto.setLongitude(poi.getLongitude());
		poiDto.setRaio(poi.getRaio());
		
		double poiLatitude = poi.getLatitude();
		double poiLongitude = poi.getLongitude();
		
		List<VehiclePoiDTO> vehiclePoiDTOs = new ArrayList<>();
		
		for(Vehicle vehicle : vehicles) {
			
			List<VehiclePosition> vehiclePositions;
			
			if(filter.getDataInicial() == null || filter.getDataFinal() == null) {
				vehiclePositions = vehiclePositionEAO.findByVehicleOrderByDateAsc(vehicle);
				
			} else {
				vehiclePositions = vehiclePositionEAO
						.findByVehicleAndDateBetweenOrderByDateAsc(vehicle, filter.getDataInicial(), filter.getDataFinal());
			}
				
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
		
		return poiDto;
	}	
}
