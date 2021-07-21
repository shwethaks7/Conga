package stations;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;

import pojoPack.StationGetPojo;
import pojoPack.StationRegPojo;
import resources.JasonInputFile;

public class RegisterStations {
	
	String apiKey;
	String baseUri;
	JasonInputFile j1;
	Properties prop;
	
	ArrayList<StationRegPojo> stationId = new ArrayList<StationRegPojo>();
	
	@BeforeSuite
	public void beforeSuite() throws IOException {
		String userDir = System.getProperty("user.dir");
		FileInputStream fp = new FileInputStream(userDir+"\\src\\main\\java\\resources\\dataproperties.properties");
		prop = new Properties();
		prop.load(fp);
		apiKey = prop.getProperty("apiKey");
		baseUri = prop.getProperty("baseUri");
		
		j1= new JasonInputFile();
		RestAssured.baseURI=baseUri;
		
	}
	
	@Test(priority =1)
	public void registWithoutAPI() {
		System.out.println("registWithoutAPI");
		given()
			.relaxedHTTPSValidation().urlEncodingEnabled(false)
			//.log().all()
			.header("Content-Type", "application/json")
			.body(j1.getStationRegBody1())
		.when()
			.post("data/3.0/stations")
		.then()
			.assertThat()
			.statusCode(401)
			.body("message", equalTo("Invalid API key. Please see http://openweathermap.org/faq#error401 for more info."));
							
	}
	@Test(priority =2)
	public void registWithAPI() {
		
		System.out.println("registWithAPI");
		StationRegPojo responseObject;
		
		//adding for first input
		 responseObject =  given()
			.relaxedHTTPSValidation().urlEncodingEnabled(false)
			//.log().all()
			.queryParam("appid", apiKey)
			.header("Content-Type", "application/json")
			.body(j1.getStationRegBody2())
		.when()
		//.log().all()
			.post("data/3.0/stations")			
			
		.then()
		
		.assertThat()
		
		.statusCode(201)
		.extract()
		.as(StationRegPojo.class);
					
		stationId.add(responseObject);
		
		//adding for second input
		responseObject =given()
			.relaxedHTTPSValidation().urlEncodingEnabled(false)
			//.log().all()
			.queryParam("appid", apiKey)
			.header("Content-Type", "application/json")
			.body(j1.getStationRegBody3())
		.when()
			.post("data/3.0/stations")
		.then()
			.assertThat()
			.statusCode(201)
			.extract()
			.as(StationRegPojo.class);
				
		stationId.add(responseObject);
		
										
	}
	
	@Test(priority =3)
	public void VerifyStationAdded() {
		
		System.out.println("VerifyStationAdded");
		
		StationGetPojo 	responseObject;	
		SoftAssert assert1 = new SoftAssert();
		//get details from arraylist
		for(StationRegPojo obj : stationId) {
			
			responseObject = given()
				.relaxedHTTPSValidation().urlEncodingEnabled(false)
				//.log().all()
				.queryParam("appid", apiKey)
				.header("Content-Type", "application/json")
			.when()
				.get("data/3.0/stations/"+obj.getId())
			.then()
				.assertThat()
				.statusCode(200)
				.extract()
				.as(StationGetPojo.class);
			
			assert1.assertEquals(responseObject.getExternal_id(), obj.getExternal_id());
			assert1.assertEquals(responseObject.getName(), obj.getName());
			assert1.assertEquals(responseObject.getLatitude(), obj.getLatitude());
			assert1.assertEquals(responseObject.getLongitude(), obj.getLongitude());
			assert1.assertEquals(responseObject.getAltitude(), obj.getAltitude());
			
		}
		assert1.assertAll();
		
	}
	
	
	@Test(priority =4)
	public void DeleteStationAdded() {
		
		System.out.println("DeleteStationAdded");
		//get details from arraylist
		for(StationRegPojo obj : stationId) {
			given()
				.relaxedHTTPSValidation().urlEncodingEnabled(false)
				//.log().all()
				.queryParam("appid", apiKey)
				.header("Content-Type", "application/json")
			.when()
				.delete("data/3.0/stations/"+obj.getId())
			.then()
				.assertThat()
				.statusCode(204);
			
		}
		
		
	}
	
	@Test(priority =5)
	public void DeleteStationDeleted() {
		System.out.println("DeleteStationDeleted");
		RestAssured.baseURI=baseUri;
		
		//get details from arraylist
		for(StationRegPojo obj : stationId) {
			given()
				.relaxedHTTPSValidation().urlEncodingEnabled(false)
				//.log().all()
				.queryParam("appid", apiKey)
				.header("Content-Type", "application/json")
			.when()
				.delete("data/3.0/stations/"+obj.getId())
			.then()
				.assertThat()
				.statusCode(404)
				.body("message", equalTo("Station not found"));
			
		}
		
		System.out.println(stationId);
		
		
	}
	
}
