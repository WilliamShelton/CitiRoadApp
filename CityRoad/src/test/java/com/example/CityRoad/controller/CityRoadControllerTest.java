package com.example.CityRoad.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.example.CityRoad.bo.CityRoadBO;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CityRoadController.class)
public class CityRoadControllerTest {
	
	@MockBean
	private CityRoadBO cityRoadBO;
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void shouldReturnYesWithValidParams() throws Exception{
		String expectedResult = "Yes";
		when(cityRoadBO.connected(anyString(), anyString())).thenReturn(expectedResult);
		
		mockMvc.perform(get("/connected")
				.param("origin", "Boston")
				.param("destination", "New York"))
				.andExpect(status().isOk())
				.andExpect(content().string(expectedResult));
	}
	@Test
	public void shouldReturnNoWithInvalidParams() throws Exception{
		String expectedResult = "No";
		when(cityRoadBO.connected(anyString(), anyString())).thenReturn(expectedResult);
		
		mockMvc.perform(get("/connected")
				.param("origin", "")
				.param("destination", "New York"))
				.andExpect(status().isOk())
				.andExpect(content().string(expectedResult));
	}
}
