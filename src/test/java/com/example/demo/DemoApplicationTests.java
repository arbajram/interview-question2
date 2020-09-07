package com.example.demo;

import com.example.demo.configuration.RibbonConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ContextConfiguration(classes = {DemoApplication.class, RibbonConfig.class})
class DemoApplicationTests {

	private final String STORE_PATH = "/store";
	private final String PERMUTATION_PATH = "/permutation";
	private final Integer[] numbers = new Integer[]{1,3,5,7,9};

	private int idCurrent = 0;

	@LocalServerPort
	int port;

	/**
	 * integration test: store (persist) array
	 * Should return 200 and array id
	 */
	@Test
	public void integration_store_array_return_id()
	{
		String response = RestAssured.given()
				.port(port)
				.contentType(ContentType.JSON)
				.when()
				.queryParam("numbers", numbers)
				.post(STORE_PATH)
				.prettyPeek()
				.then()
				.assertThat()
				.statusCode(200)
				.extract().asString();

		assertTrue(NumberUtils.isCreatable(response));
	}

	/**
	 * integration test: try to store (persist) array with one string member
	 * Should return 400 and NumberFormatException
	 */
	@Test
	public void store_array_with_string_member_fails()
	{
		String response = RestAssured.given()
				.port(port)
				.contentType(ContentType.JSON)
				.when()
				.queryParam("numbers", numbers)
				.queryParam("numbers", "XY")
				.post(STORE_PATH)
				.prettyPeek()
				.then()
				.assertThat()
				.statusCode(400)
				.extract().asString();

		assertTrue(response.contains("NumberFormatException"));
	}

	/**
	 * integration test: store (persist) array and try its permutation
	 * Should return statsu code 200 and array of 5 shuffled integers in response
	 */
	@Test
	public void integration_permutation_with_existing_id()
	{
		//Store array and get its id
		String responseTemp = RestAssured.given()
				.port(port)
				.contentType(ContentType.JSON)
				.when()
				.queryParam("numbers", numbers)
				.post(STORE_PATH)
				.prettyPeek()
				.then()
				.assertThat()
				.statusCode(200)
				.extract().asString();

		int id = Integer.parseInt(responseTemp);

		//permutation by array id
		Integer[] integers = RestAssured.given()
				.port(port)
				.contentType(ContentType.JSON)
				.when()
				.get(PERMUTATION_PATH + "?id=" + id)
				.prettyPeek()
				.then()
				.assertThat()
				.statusCode(200)
				.extract().as(Integer[].class);

		assertTrue(integers.length == 5);
		assertFalse(integers == numbers);

	}

	/**
	 * integration test: store (persist) array and try permutation with next id (non-existing)
	 * Should return 500 and appropriate error message
	 */
	@Test
	public void integration_permutation_with_non_existing_id()
	{
		//Store array and get its id
		String responseTemp = RestAssured.given()
				.port(port)
				.contentType(ContentType.JSON)
				.when()
				.queryParam("numbers", numbers)
				.post(STORE_PATH)
				.prettyPeek()
				.then()
				.assertThat()
				.statusCode(200)
				.extract().asString();

		 int nextId = Integer.parseInt(responseTemp) + 1;

		//permutation od non-existing id (next one))
		String response = RestAssured.given()
				.port(port)
				.contentType(ContentType.JSON)
				.when()
				.get(PERMUTATION_PATH + "?id=" + nextId)
				.prettyPeek()
				.then()
				.assertThat()
				.statusCode(500)
				.extract().asString();

		assertTrue(response.contains(String.format("Array with id = %s does not exist", nextId)));
	}

}
