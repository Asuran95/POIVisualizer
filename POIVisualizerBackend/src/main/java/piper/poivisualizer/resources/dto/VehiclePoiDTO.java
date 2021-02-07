package piper.poivisualizer.resources.dto;

import java.io.Serializable;

public class VehiclePoiDTO implements Serializable {
	
	private static final long serialVersionUID = -419487519620836994L;
	
	private String placa;
	private long tempoMilliseconds;
	
	public String getPlaca() {
		return placa;
	}
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public long getTempoMilliseconds() {
		return tempoMilliseconds;
	}

	public void setTempoMilliseconds(long tempoMilliseconds) {
		this.tempoMilliseconds = tempoMilliseconds;
	}
}
