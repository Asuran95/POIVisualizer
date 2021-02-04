package piper.poivisualizer.database.eao;

import org.springframework.data.jpa.repository.JpaRepository;

import piper.poivisualizer.database.entities.VehiclePosition;

public interface VehiclePostionEAO extends JpaRepository<VehiclePosition, Long> {

}
