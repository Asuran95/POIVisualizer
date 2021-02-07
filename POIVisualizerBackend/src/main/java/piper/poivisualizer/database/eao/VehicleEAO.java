package piper.poivisualizer.database.eao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import piper.poivisualizer.database.entities.Vehicle;

public interface VehicleEAO extends JpaRepository<Vehicle, Long> {
	
	Vehicle findVehicleByPlaca(String placa);
	List<Vehicle> findAllVehicleByPlaca(String placa);
	
	
}
