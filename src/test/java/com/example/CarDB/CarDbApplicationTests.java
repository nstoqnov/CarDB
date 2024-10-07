package com.example.CarDB;

import com.example.CarDB.Model.Trip;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;
import net.minidev.json.JSONArray;

import java.net.URI;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarDbApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;

//	@Test
//	void contextLoads() {
//	}
	@Test
	void shouldReturnAllTripsWhenListIsRequested() {
		ResponseEntity<String> response = restTemplate.getForEntity("/trips", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(response.getBody());
		int cashCardCount = documentContext.read("$.length()");
		assertThat(cashCardCount).isEqualTo(11);

		JSONArray ids = documentContext.read("$..id");
		assertThat(ids).containsExactlyInAnyOrder(1, 2, 3,4,5,6,7,8,9,10,11);

	}
	@Test
	void shouldReturnAPageOfTrips() {
		ResponseEntity<String> response = restTemplate.getForEntity("/trips?page=0&size=1", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(response.getBody());
		JSONArray page = documentContext.read("$[*]");
		assertThat(page.size()).isEqualTo(1);
	}
	@Test
	void shouldReturnASortedPageOfTrips() {
		ResponseEntity<String> response = restTemplate.getForEntity("/trips?page=0&size=3&sort=id,desc", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(response.getBody());
		JSONArray read = documentContext.read("$[*]");
		assertThat(read.size()).isEqualTo(3);

		//using all ids
		JSONArray ids = documentContext.read("$..id");
		assertThat(ids).containsExactlyInAnyOrder(11, 10 ,9);

		//using array of ids
		int id = documentContext.read("$[0].id");
		assertThat(id).isEqualTo(11);

		int id2 = documentContext.read("$[1].id");
		assertThat(id2).isEqualTo(10);

		int id3 = documentContext.read("$[2].id");
		assertThat(id3).isEqualTo(9);
	}
	@Test
	void shouldReturnATripWhenIdIsRequested() {
		ResponseEntity<String> response = restTemplate.getForEntity("/trips/1", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(response.getBody());
		int params = documentContext.read("$.length()");
		assertThat(params).isEqualTo(6);

		Number id = documentContext.read("$.id");
		assertThat(id).isEqualTo(1);

		String name = documentContext.read("$.name");
		assertThat(name).isEqualTo("Mountain Adventure");
	}

	@Test
	void shouldNotReturnATripWhenInvalidIdIsRequested() {
		ResponseEntity<String> response = restTemplate.getForEntity("/trips/99", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

	@Test
	void shouldCreateATrip(){
		Trip newTrip = new Trip(11L,"Trip to Sofia", "Plovdiv","Sofia",(double)150,"Nick");
		ResponseEntity<Void> createResponse = restTemplate.postForEntity("/trips", newTrip, Void.class);
		assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

		URI locationOfNewCashCard = createResponse.getHeaders().getLocation();
		ResponseEntity<String> getResponse = restTemplate.getForEntity(locationOfNewCashCard, String.class);
		assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
		Number id = documentContext.read("$.id");
		//get name ...

		assertThat(id).isNotNull();
		//assert Name ...
	}
}
