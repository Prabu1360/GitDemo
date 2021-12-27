package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.ADD_Place;

import pojo.Location;

public class TestDataBuild {
		
	
	public ADD_Place addPlacePayload(String name, int phone_number, String language, String websiteField, String addressField, String typesList, double lat, double lng, int accuracyField)
	{
		 ADD_Place p = new ADD_Place(); 
		 
		 p.setAccuracy(accuracyField);
		  
		  p.setName(name); 
		  p.setPhone_number(phone_number);
		  p.setLanguage(language);
		  p.setWebsite(websiteField);
		  p.setAddress(addressField);
		  
		  List<String> typesObj = new ArrayList<String>(); 
		  typesObj.add("shoe park");
		  typesObj.add("shop");
		  
		  p.setTypes(typesList);
		  Location locObj = new Location(); 
		  locObj.setLat(lat);
		  locObj.setLng(lng);
		  
		  p.setLocation(locObj);
		  return p;
	}
	
	public String deletePlacePayload(String place_id)
	{
		return "{\r\n"
				+ "    \"place_id\":\""+place_id+"\" \r\n"
				+ "}";
		
	}

	
	
	
}
