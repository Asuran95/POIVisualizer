package piper.poivisualizer.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import piper.poivisualizer.database.eao.PoiEAO;
import piper.poivisualizer.database.eao.VehicleEAO;
import piper.poivisualizer.database.eao.VehiclePostionEAO;
import piper.poivisualizer.database.entities.Vehicle;

@Component
public class CSVLoader {
	
	@Autowired
	private VehicleEAO vehicleEao;
	
	@Autowired
	private VehiclePostionEAO vehiclePostionEAO;
	
	@Autowired
	private PoiEAO poiEAO;
	
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
	    System.out.println("hello world, I have just started up");
	    
	    Vehicle vehicle = new Vehicle();
	    
	    
	    //Vehicle v = vehicleEao.findVehicleByPlaca("teste");
	    
	    //System.out.println(v.getPlaca());
	    
	    vehicle.setPlaca("teste");
	    
	    
	    try {
	    	vehicleEao.save(vehicle);
	    } catch(DataIntegrityViolationException ex) {
	    	System.out.println("Veiculo já cadastrado! a");
	    }	    
	}
}
