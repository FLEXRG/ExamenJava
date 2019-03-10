package com.examen.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Customer implements Serializable {
	
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("latitude")
	private String latitude;
	
	@JsonProperty("longitud")
	private String longitud;
	
	public Customer() {
		super();
	
	}
	
	public Customer(Integer id, String name, String latitude, String longitud) {
		super();
		this.id = id;
		this.name = name;
		this.latitude = latitude;
		this.longitud = longitud;
	}
	
	public Integer getid() {
		return id;
	}
	public void setid(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitud() {
		return longitud;
	}
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	
	
}
