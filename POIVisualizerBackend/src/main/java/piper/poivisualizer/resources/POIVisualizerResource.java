package piper.poivisualizer.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import piper.poivisualizer.database.eao.PoiEAO;
import piper.poivisualizer.database.entities.POI;
import piper.poivisualizer.resources.dto.POIDTO;
import piper.poivisualizer.resources.dto.POIResultDTO;
import piper.poivisualizer.resources.dto.POIVisualizerFilterDTO;
import piper.poivisualizer.services.QueryPOI;

@RestController
@CrossOrigin
@RequestMapping("/poivisualizer")
public class POIVisualizerResource {
	
	@Autowired
	private QueryPOI queryPoi;
	
	@Autowired
	private PoiEAO poiEAO;
	
	@GetMapping("/test")
	public String test() {
		return "Sucesso";
	}
	
	@GetMapping("/getpois")
	public List<POIDTO> getPois(){
		return convertPoiEntityToDTO(poiEAO.findAll());
	}
	
	@PostMapping("/getbyvehicleanddate")
	public POIResultDTO getByVehicleAndDate(@RequestBody POIVisualizerFilterDTO filter) {		
		return queryPoi.queryByVehicleAndBetweenDate(filter);
	}
	
	private List<POIDTO> convertPoiEntityToDTO(List<POI> pois){
		
		List<POIDTO> poiDtos = new ArrayList<POIDTO>();
		
		for(POI poi : pois) {
			POIDTO dto = new POIDTO();
			
			dto.setId(poi.getId());
			dto.setName(poi.getName());
			
			poiDtos.add(dto);	
		}
		return poiDtos;	
	}
}
