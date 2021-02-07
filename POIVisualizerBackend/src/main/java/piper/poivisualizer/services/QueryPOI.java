package piper.poivisualizer.services;

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
		
		
		for(POI poi : pois) {
			
			double poiLatitude = poi.getLatitude();
			double poiLongitude = poi.getLongitude();
			
			for(Vehicle vehicle : vehicles) {
				
				List<VehiclePosition> vehiclePositions = vehiclePositionEAO.findByVehicle(vehicle);
				
				for(VehiclePosition vehiclePosition : vehiclePositions) {
					
					double vehicleLatitude = vehiclePosition.getLatitude();
					double vehicleLongitude = vehiclePosition.getLongitude();
					
					double distance = CoordinatesUtil
						.getDistanceBetweenTwoPointsInMeters(vehicleLatitude, vehicleLongitude, poiLatitude, poiLongitude);
					
					if(poi.getRaio() > distance) {
						logger.info("Veiculo " + vehicle.getPlaca() + " está dentro da poi " + poi.getName());
					}
				}					
			}	
		}
		
		return null;
	}
}
