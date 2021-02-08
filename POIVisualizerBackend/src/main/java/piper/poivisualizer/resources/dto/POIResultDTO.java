package piper.poivisualizer.resources.dto;

import java.io.Serializable;
import java.util.List;

public class POIResultDTO implements Serializable {
	
	private static final long serialVersionUID = -5071121871205251168L;
	
	private String name;
	private int raio;
	private double longitude;
	private double latitude;
	
	private List<VehiclePoiDTO> vehicles;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getRaio() {
		return raio;
	}
	
	public void setRaio(int raio) {
		this.raio = raio;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public List<VehiclePoiDTO> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<VehiclePoiDTO> vehicles) {
		this.vehicles = vehicles;
	}
}
