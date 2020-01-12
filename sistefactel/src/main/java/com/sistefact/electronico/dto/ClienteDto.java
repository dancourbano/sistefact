package com.sistefact.electronico.dto;

public class ClienteDto {
	private String CODDNI;
	private String Data;
	private String razon_social;
	private String ruc;
	private String nombre_comercial;
	private String domicilio_fiscal;
	
	public String getCODDNI() {
		return CODDNI;
	}
	public void setCODDNI(String cODDNI) {
		CODDNI = cODDNI;
	}
	
	 
	public String getData() {
		return Data;
	}
	public void setData(String data) {
		Data = data;
	}
	public String getRazon_social() {
		return razon_social;
	}
	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String getNombre_comercial() {
		return nombre_comercial;
	}
	public void setNombre_comercial(String nombre_comercial) {
		this.nombre_comercial = nombre_comercial;
	}
	public String getDomicilio_fiscal() {
		return domicilio_fiscal;
	}
	public void setDomicilio_fiscal(String domicilio_fiscal) {
		this.domicilio_fiscal = domicilio_fiscal;
	}
	
	
	
}
