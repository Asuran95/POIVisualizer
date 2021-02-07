package piper.poivisualizer.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import piper.poivisualizer.resources.dto.POIResultDTO;
import piper.poivisualizer.services.QueryPOI;

@RestController
@CrossOrigin
@RequestMapping("/poivisualizer")
public class POIVisualizerResource {
	
	@Autowired
	private QueryPOI queryPoi;
	
	@GetMapping("/test")
	public String test() {
		return "Sucesso";
	}
	
	@GetMapping("/getallvehiclespoi")
	public POIResultDTO getPOIVehicles() {
		return queryPoi.queryAllVehicles();
	}
	
	
}
