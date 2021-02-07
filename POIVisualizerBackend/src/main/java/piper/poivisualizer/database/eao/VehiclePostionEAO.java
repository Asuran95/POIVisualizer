package piper.poivisualizer.database.eao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import piper.poivisualizer.database.entities.Vehicle;
import piper.poivisualizer.database.entities.VehiclePosition;

public interface VehiclePostionEAO extends JpaRepository<VehiclePosition, Long> {
	
	List<VehiclePosition> findByVehicleOrderByDateAsc(Vehicle vehicle);
	List<VehiclePosition> findByVehicleAndDateBetweenOrderByDateAsc(Vehicle vehicle, Date dateInitial, Date dateFinal);

}
