package com.intrasoft.extrahardexercise;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SanityController.class)
class SanityControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void pingShouldPong() throws Exception {
		this.mockMvc.perform(get("/ping")).andExpect(content().string(containsString("pong")));
	}
}
