package piper.poivisualizer.resources.dto;

import java.io.Serializable;

public class POIDTO implements Serializable {

	private static final long serialVersionUID = 1534068452042632321L;
	
	private long id;
	private String name;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
