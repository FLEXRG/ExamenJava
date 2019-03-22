package com.examen.controller;


import java.io.IOException;
import java.text.DecimalFormat;
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
import com.google.maps.DirectionsApi.RouteRestriction;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;

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
		
	
		
		GeoApiContext context = new GeoApiContext.Builder()
				.apiKey("AIzaSyCfcpje5YTJS9c5LBiZpzSmjeu7hQLiqrM")
				.build();
		
		DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
		
		LatLng Cx[] = new LatLng[4];
		LatLng Px[] = new LatLng[4];
		
		Long distancias[] = new Long[4];
		String tiempos[] = new String[4];
		
		String coordenadasCustomer[] = new String[4];
		String coordenadasPersona[] = new String[4];
		
		for(int y=0;y<4;y++)
		{
			
			Cx[y] = new LatLng( Double.parseDouble(listCustomer.get(y).getLatitude() ),Double.parseDouble(listCustomer.get(y).getLongitud() ));
			Px[y] = new LatLng( Double.parseDouble(listPerson.get(y).getLatitude() ),Double.parseDouble(listPerson.get(y).getLongitud() ));
		
			coordenadasCustomer[y]=listCustomer.get(y).getLatitude()+","+listCustomer.get(y).getLongitud();
			coordenadasPersona[y]=listPerson.get(y).getLatitude()+","+listPerson.get(y).getLongitud();
			
		}	
			
			
		try {
				DistanceMatrix result = req.origins(Cx[0],Cx[1],Cx[2],Cx[3])
				           .destinations(Px[0],Px[1],Px[2],Px[3])
				           .mode(TravelMode.DRIVING)
				           .avoid(RouteRestriction.TOLLS)
				           .language("en-US")
				           .await();
				
				 distancias[0] = result.rows[0].elements[0].distance.inMeters;
				 distancias[1] = result.rows[1].elements[1].distance.inMeters;
				 distancias[2] = result.rows[2].elements[2].distance.inMeters;
				 distancias[3] = result.rows[3].elements[3].distance.inMeters;
				 
				 tiempos[0] =result.rows[0].elements[0].duration.humanReadable;
				 tiempos[1] =result.rows[1].elements[1].duration.humanReadable;
				 tiempos[2] =result.rows[2].elements[2].duration.humanReadable;
				 tiempos[3] =result.rows[3].elements[3].duration.humanReadable;


				
			} catch (ApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		DecimalFormat formato = new DecimalFormat("#,###");

		for(int x=0;x<=3;x++)
		{
			Tabla p = new Tabla(listCustomer.get(x).getName(),listPerson.get(x).getName(),formato.format(distancias[x])+" Metros",tiempos[x]);

			this.datos.add(p);
		}	
	
	
		
		model.addAttribute("mensaje","Examen JAVA");
		model.addAttribute("nombre","Ricardo Octavio Rojas Garcia");
		model.addAttribute("tabla",datos);
		model.addAttribute("coordenadasCustomer",coordenadasCustomer);
		model.addAttribute("coordenadasPersona",coordenadasPersona);
		return "Examen";
	}

}
