package pojo;

import groovyjarjarantlr.collections.List;

public class ADD_Place {

static	private int accuracy;
static	private String name;
static	private int phone_number;
static	private String address;
static	private String website;
static	private String language;
static	private Location location;
static	private String types;
	

	
	
	public int getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(int phone_number) {
		
		this.phone_number = phone_number;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String  getTypes() {
		return types;
	}
	public void setTypes(String  types) {
		this.types = types;
	}
	
	/*
	 * static private String status; static private String place_id; static private
	 * String scope; static private String reference; static private String id;
	 * 
	 * public String getStatus() { return status; } public void setStatus(String
	 * status) { this.status = status; } public String getPlace_id() { return
	 * place_id; } public void setPlace_id(String place_id) { this.place_id =
	 * place_id; } public String getScope() { return scope; } public void
	 * setScope(String scope) { this.scope = scope; } public String getReference() {
	 * return reference; } public void setReference(String reference) {
	 * this.reference = reference; } public String getId() { return id; } public
	 * void setId(String id) { this.id = id; }
	 */

	public ADD_Place addReq()
	{
		 ADD_Place p = new ADD_Place(); 
		 
		 p.getAccuracy();
		 p.getName();
		 p.getPhone_number();
		 p.getAddress();
		 p.getTypes();
		 p.getWebsite();
	//	 p.getLocation().getLat();
	//	 p.getLocation().getLng();
		 p.getLanguage();
		 
		return p;
	}
	
	
}
