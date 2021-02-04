package piper.poivisualizer.database.eao;

import org.springframework.data.jpa.repository.JpaRepository;

import piper.poivisualizer.database.entities.POI;

public interface PoiEAO extends JpaRepository<POI, Long> {

}
