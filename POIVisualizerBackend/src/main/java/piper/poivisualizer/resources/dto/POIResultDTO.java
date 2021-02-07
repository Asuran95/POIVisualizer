package piper.poivisualizer.resources.dto;

import java.io.Serializable;
import java.util.List;

public class POIResultDTO implements Serializable {
	
	private static final long serialVersionUID = -9154949267616051144L;

	private List<PoiDTO> poisDto;

	public List<PoiDTO> getPoisDto() {
		return poisDto;
	}

	public void setPoisDto(List<PoiDTO> poisDto) {
		this.poisDto = poisDto;
	}
}
