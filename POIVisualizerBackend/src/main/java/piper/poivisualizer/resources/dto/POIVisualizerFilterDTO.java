package piper.poivisualizer.resources.dto;

import java.io.Serializable;
import java.util.Date;

public class POIVisualizerFilterDTO implements Serializable {

	private static final long serialVersionUID = 5488840321891278748L;
	
	private POIDTO poidto;
	private String placa;
	private Date dataInicial;
	private Date dataFinal;
	
	public String getPlaca() {
		return placa;
	}
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public Date getDataInicial() {
		return dataInicial;
	}
	
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	
	public Date getDataFinal() {
		return dataFinal;
	}
	
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public POIDTO getPoidto() {
		return poidto;
	}

	public void setPoidto(POIDTO poidto) {
		this.poidto = poidto;
	}
	
	
}
