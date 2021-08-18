package com.example.restservicetest;

import com.example.restservicetest.Utility.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RestServiceTestApplicationTests {

	@LocalServerPort
	private int port;

	Response response;
	List<Object> floorList;
	int carId;

	@Test
	@Order(1)
	public void getWholeParking() {
		response = RestAssured.given().port(port).when().get("/api/parking").then().statusCode(200).extract().response();
		floorList= response.jsonPath().getList("data");
	}

	@Test
	@Order(2)
	public void getRandomFloorNumber() {
		Random rand = new Random();
		RestAssured.given().port(port).when().get("/api/parking/floor/" + rand.nextInt(floorList.size())).then().statusCode(200);
	}

	@Test
	@Order(3)
	public void parkACar() {
		int height = (int) ((LinkedHashMap) floorList.get(0)).get("floorHeight");
		int weight = (int) ((LinkedHashMap) floorList.get(0)).get("floorWeight");
		response = RestAssured.given().port(port).when().post("/api/parking/car?height=" + (height-1) + "&weight=" + (weight-1)).then().statusCode(201).extract().response();
		carId= response.jsonPath().getInt("data.carId");
	}

	@Test
	@Order(4)
	public void canNotParkACar() {
		int height = (int) ((LinkedHashMap) floorList.get(0)).get("floorHeight");
		int weight = (int) ((LinkedHashMap) floorList.get(0)).get("floorWeight");
		RestAssured.given().port(port).when().post("/api/parking/car?height=" + (height*Constants.MAX_HEIGHT_OF_FLOORS) + "&weight=" + (weight*Constants.MAX_WEIGHT_OF_FLOORS)).then().statusCode(406);
	}

	@Test
	@Order(5)
	public void deleteACar() {
		RestAssured.given().port(port).when().delete("/api/parking/car/"+carId).then().statusCode(200);
	}

}
