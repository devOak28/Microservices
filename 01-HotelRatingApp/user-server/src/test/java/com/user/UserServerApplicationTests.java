package com.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.user.entitys.Rating;
import com.user.external_service.Rating_Service;

@SpringBootTest
class UserServerApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private Rating_Service rating;
	
	/*
	 * @Test public void createRating() { Rating
	 * res=Rating.builder().rating(10).userId("").hotelId("").
	 * feedback("this is created using feign client").build(); Rating saveRating=
	 * rating.createRating(res); System.out.println("Rating is created!!"); }
	 */
}
