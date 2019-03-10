package com.examen.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.examen.domain.Customer;
import com.examen.domain.Person;
import com.examen.util.ClientPostCustomer;
import com.examen.util.ClientPostPerson;
import com.examen.util.Tabla;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;

@Controller
public class IndexController {
	
	private List<Tabla> datos;
	
	@GetMapping("/")
	public String Examen(Model model)
	{		
		this.datos =new ArrayList<Tabla>();
		
		ClientPostCustomer cPC = new ClientPostCustomer();
		ArrayList<Customer> listCustomer= (ArrayList<Customer>) cPC.getCustomers();
		
		ClientPostPerson cPP = new ClientPostPerson();
		ArrayList<Person> listPerson= (ArrayList<Person>) cPP.getPersons();
		
		GeoApiContext context = new GeoApiContext();
		DistanceMatrixApiRequest req=DistanceMatrixApi.newRequest(context);
				
		for(int x=0;x<=3;x++)
		{
			String Distancia = "D( ("+listCustomer.get(x).getLatitude()+","+listCustomer.get(x).getLongitud()+"), ("+listPerson.get(x).getLatitude()+","+listCustomer.get(x).getLongitud()+") )";
			String Tiempo = "T( ("+listCustomer.get(x).getLatitude()+","+listCustomer.get(x).getLongitud()+"), ("+listPerson.get(x).getLatitude()+","+listCustomer.get(x).getLongitud()+") )";

			Tabla p = new Tabla(listCustomer.get(x).getName(),listPerson.get(x).getName(),Distancia,Tiempo);

			this.datos.add(p);
		}	
	
	
		
		model.addAttribute("mensaje","Examen JAVA");
		model.addAttribute("nombre","Ricardo Octavio Rojas Garcia");
		model.addAttribute("tabla",datos);
		return "Examen";
	}

}
