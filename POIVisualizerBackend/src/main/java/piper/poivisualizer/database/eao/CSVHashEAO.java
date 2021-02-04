package piper.poivisualizer.database.eao;

import org.springframework.data.jpa.repository.JpaRepository;

import piper.poivisualizer.database.entities.CSVHash;

public interface CSVHashEAO extends JpaRepository<CSVHash, Long> {
	
	CSVHash findCSVHashByHash(String hash);

}
