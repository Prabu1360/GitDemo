package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import io.cucumber.java.lu.a.as;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
public static RequestSpecification req;
public static String otherFolder;

	
	public RequestSpecification requestSpecification() throws IOException
	{
		if (req==null) 
		{
		  PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		//  RestAssured.baseURI = "https://rahulshettyacademy.com/";
		  req = new
		  RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
		  .addQueryParam("key", "qaclick123") 
		  .addFilter(RequestLoggingFilter.logRequestTo(log))
		  .addFilter(ResponseLoggingFilter.logResponseTo(log))
		  .build();
		  
		  return req;
		  }
		return req;
	}
	
	public static String readJSONFile(String key) throws IOException, ParseException
	{
		  JSONParser parser = new JSONParser(); 
//		  Object obj = parser.parse(new FileReader("C:\\Users\\a829668\\eclipse-workspace\\Google_PlaceAPI_DemoBACKUP_08_12\\src\\test\\java\\testData\\addPlaceAPI.json"));

		  
		//  Object obj = parser.parse(new FileReader("C:\\Users\\a829668\\eclipse-workspace\\Google_PlaceAPI_DemoBACKUP_08_MAPPPPBACKUP\\src\\test\\java\\testData\\addPlaceAPI.json"));
		  String path = System.getProperty("user.dir");
		   otherFolder = path + "\\src\\test\\java\\testData\\addPlaceAPI.json";
		   Object obj = parser.parse(new FileReader(otherFolder));
		  JSONObject jsonObject = (JSONObject) obj;
		  jsonObject.get(key);
		  return (String) jsonObject.get(key);  	
	}
	
	public static int readJSONFileIntegerValue(String key) throws IOException, ParseException
	{
		  JSONParser parser = new JSONParser(); 
		//  Object obj = parser.parse(new FileReader("C:\\Users\\a829668\\eclipse-workspace\\Google_PlaceAPI_DemoBACKUP_08_12\\src\\test\\java\\testData\\addPlaceAPI.json"));
		//  Object obj = parser.parse(new FileReader("C:\\Users\\a829668\\eclipse-workspace\\Google_PlaceAPI_DemoBACKUP_08_MAPPPPBACKUP\\src\\test\\java\\testData\\addPlaceAPI.json"));
		  
		  Object obj = parser.parse(new FileReader(otherFolder));
		  JSONObject jsonObject = (JSONObject) obj;
		  jsonObject.get(key);
		  int i =Integer.parseInt(jsonObject.get(key).toString());
		  
		return i;	
	}

	public static  double readJSONALOCATINFile(String KeyOuterValue, String KeyInnerValue) throws IOException, ParseException
	{
		  JSONParser parser = new JSONParser(); 
		//  Object obj = parser.parse(new FileReader("C:\\Users\\a829668\\eclipse-workspace\\Google_PlaceAPI_DemoBACKUP_08_12\\src\\test\\java\\testData\\addPlaceAPI.json"));
		//  Object obj = parser.parse(new FileReader("C:\\Users\\a829668\\eclipse-workspace\\Google_PlaceAPI_DemoBACKUP_08_MAPPPPBACKUP\\src\\test\\java\\testData\\addPlaceAPI.json"));
		  
		  Object obj = parser.parse(new FileReader(otherFolder));
		  JSONObject jsonObject = (JSONObject) obj;
		
		  Object obbb=jsonObject.get(KeyOuterValue);
		  JSONObject jsonLocationObj=(JSONObject) obbb;
		  jsonLocationObj.get(KeyInnerValue);
		  
		  Double d = (Double) jsonLocationObj.get(KeyInnerValue);
		
		return d;
	        	
	}
	
	
	public static ArrayList<String> readJSONArray(String key) throws IOException, ParseException
	{
		  JSONParser parser = new JSONParser(); 
//		  Object obj = parser.parse(new FileReader("C:\\Users\\a829668\\eclipse-workspace\\Google_PlaceAPI_DemoBACKUP_08_12\\src\\test\\java\\testData\\addPlaceAPI.json"));
//		  Object obj = parser.parse(new FileReader("C:\\Users\\a829668\\eclipse-workspace\\Google_PlaceAPI_DemoBACKUP_08_MAPPPPBACKUP\\src\\test\\java\\testData\\addPlaceAPI.json"));

		  Object obj = parser.parse(new FileReader(otherFolder));
		  JSONObject jsonObject = (JSONObject) obj;
		
		  ArrayList<String> typesList= new ArrayList<String>();
		  
		 typesList=  (ArrayList<String>) jsonObject.get("types");
		 //System.out.println("the accuracy is "+jsonObject.get("accuracy"));
		  //return  (String) jsonObject.get(key);
		  return typesList;
	        	
	}
	
	public static int checkValueIsValidInteger(String key) throws IOException, ParseException
	{
		int pNumber =0;
		char ch [] =(readJSONFile(key).toCharArray());
		
		for (char c : ch) {
			int ascii = (int) c;
			
			if (ascii>96) {
				System.out.println("the entered phone number is Invalid and should contain only numbers");
				pNumber =(Integer) null;
				break;
			}
			else 
			{
				pNumber =Integer.parseInt(readJSONFile(key));
			}
			
		} 
		return pNumber;
	}
	
	public static String getRandomNumber() throws IOException
	{
		   Random rand = new Random();
	        int num1 = (rand.nextInt(8) * 10) + rand.nextInt(8);
	        int num2 = rand.nextInt(743);
	        int num3 = rand.nextInt(10000);
	        
	        DecimalFormat df2 = new DecimalFormat("00"); // 3 zerosgetRandomNumber()
	        DecimalFormat df3 = new DecimalFormat("000"); // 3 zerosgetRandomNumber()
	        DecimalFormat df4 = new DecimalFormat("0000"); // 4 zeros

	        String phoneNumber = "9"+df2.format(num1) + "-" + df3.format(num2) + "-" + df4.format(num3);

		return phoneNumber;
	}
	
	public static String getGlobalValue(String key) throws IOException
	{
		Properties prop = new Properties();
//		FileInputStream fis = new FileInputStream("C:\\Users\\a829668\\eclipse-workspace\\Google_PlaceAPI_DemoBACKUP_08_12\\src\\test\\java\\resources\\global.properties");
		//FileInputStream fis = new FileInputStream("C:\\Users\\a829668\\eclipse-workspace\\Google_PlaceAPI_DemoBACKUP_08_MAPPPPBACKUP\\src\\test\\java\\resources\\global.properties");
		
		String globa = System.getProperty("user.dir")+"\\src\\test\\java\\resources\\global.properties";
	     	FileInputStream fis = new FileInputStream(globa);
		prop.load(fis);
		return prop.getProperty(key);
		
	}
		
	public static String getRequestedData( String key) throws IOException
	{
		return null;
	}
	
}
