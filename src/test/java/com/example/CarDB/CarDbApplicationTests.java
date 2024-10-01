package com.example.CarDB;

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
		assertThat(cashCardCount).isEqualTo(10);

		JSONArray ids = documentContext.read("$..id");
		assertThat(ids).containsExactlyInAnyOrder(1, 2, 3,4,5,6,7,8,9,10);

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
		assertThat(ids).containsExactlyInAnyOrder(10, 9 ,8);

		//using array of ids
		int id = documentContext.read("$[0].id");
		assertThat(id).isEqualTo(10);

		int id2 = documentContext.read("$[1].id");
		assertThat(id2).isEqualTo(9);

		int id3 = documentContext.read("$[2].id");
		assertThat(id3).isEqualTo(8);
	}

}
