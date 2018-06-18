package com.ifworks.statistics.transactions;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.ifworks.statistics.App;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class TransactionControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void givenTransaction_save_thenStatus201() throws Exception {

		mockMvc.perform(post("/transactions")
				.content("{\"amount\":" + 12.3D + ", \"timestamp\":" + System.currentTimeMillis() + " }")
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status()
				.isCreated());
	}

	@Test
	public void givenTransaction_save_thenStatus204() throws Exception {

		mockMvc.perform(post("/transactions")
				.content("{\"amount\":" + 12.3D + ", \"timestamp\":" + (System.currentTimeMillis() - 70 * 1000) + " }")
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status()
				.isNoContent());
	}

}