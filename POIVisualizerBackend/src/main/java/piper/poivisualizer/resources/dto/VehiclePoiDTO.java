package piper.poivisualizer.resources.dto;

import java.io.Serializable;

public class VehiclePoiDTO implements Serializable {
	
	private static final long serialVersionUID = -419487519620836994L;
	
	private String placa;
	private double minutos;
	
	public String getPlaca() {
		return placa;
	}
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public double getMinutos() {
		return minutos;
	}
	
	public void setMinutos(double minutos) {
		this.minutos = minutos;
	}
}
