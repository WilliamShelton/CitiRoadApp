package com.example.CityRoad.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.CityRoad.CityRoadApplication;
import com.example.CityRoad.bo.CityRoadBO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CityRoadApplication.class, webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class CityRoadControllerITTest {
	@Autowired
	private CityRoadBO cityRoadBO;
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void shouldReturnYesWithDirectConnection() throws Exception{
		String expectedResult = "YES";
		
		mockMvc.perform(get("/connected")
				.param("origin", "Boston")
				.param("destination", "New York"))
				.andExpect(status().isOk())
				.andExpect(content().string(expectedResult));
	}
	@Test
	public void shouldReturnYesWithIndirectConnection() throws Exception{
		String expectedResult = "YES";
		
		mockMvc.perform(get("/connected")
				.param("origin", "New York")
				.param("destination", "Newark"))
				.andExpect(status().isOk())
				.andExpect(content().string(expectedResult));
	}
	@Test
	public void shouldReturnNoWithInvalidParams() throws Exception{
		String expectedResult = "NO";
		
		mockMvc.perform(get("/connected")
				.param("origin", "123456")
				.param("destination", "New York"))
				.andExpect(status().isOk())
				.andExpect(content().string(expectedResult));
	}
	@Test
	public void shouldReturnNoWithEmptyParams() throws Exception{
		String expectedResult = "NO";
		
		mockMvc.perform(get("/connected")
				.param("origin", "")
				.param("destination", ""))
				.andExpect(status().isOk())
				.andExpect(content().string(expectedResult));
	}
	@Test
	public void shouldReturnNoWhenNoConnection() throws Exception{
		String expectedResult = "NO";
		
		mockMvc.perform(get("/connected")
				.param("origin", "Boston")
				.param("destination", "Trenton"))
				.andExpect(status().isOk())
				.andExpect(content().string(expectedResult));
	}
}
