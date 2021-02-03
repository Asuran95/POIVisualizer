package piper.poivisualizer.resources;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/poivisualizer")
public class POIVisualizerResource {
	
	@GetMapping("/test")
	public String test() {
		return "Sucesso";
	}
	
}
