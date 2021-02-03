package piper.poivisualizer.database.eao;

import org.springframework.data.jpa.repository.JpaRepository;

import piper.poivisualizer.database.entities.Vehicle;

public interface VehicleEAO extends JpaRepository<Vehicle, Long> {

}
