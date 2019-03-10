package com.examen.util;

import java.io.Serializable;

public class Tabla implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String Persona;
	private String Cliente;
	private String Distancia;
	private String Tiempo;
	
	
	
	
	public Tabla() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Tabla(String persona, String cliente, String distancia, String tiempo) {
		super();
		Persona = persona;
		Cliente = cliente;
		Distancia = distancia;
		Tiempo = tiempo;
	}
	public String getPersona() {
		return Persona;
	}
	public void setPersona(String persona) {
		Persona = persona;
	}
	public String getCliente() {
		return Cliente;
	}
	public void setCliente(String cliente) {
		Cliente = cliente;
	}
	public String getDistancia() {
		return Distancia;
	}
	public void setDistancia(String distancia) {
		Distancia = distancia;
	}
	public String getTiempo() {
		return Tiempo;
	}
	public void setTiempo(String tiempo) {
		Tiempo = tiempo;
	}
	
	

}
