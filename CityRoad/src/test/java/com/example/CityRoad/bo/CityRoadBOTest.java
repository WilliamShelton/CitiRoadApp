package com.example.CityRoad.bo;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.CityRoad.dao.CityRoadFileDAO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityRoadBOTest {
	@Autowired
	private CityRoadBO cityRoadBO;
	@Mock
	private CityRoadFileDAO cityRoadFileDAO;
	private static final String YES = "YES";
	private static final String NO = "NO";
	
	@Test
	public void shouldReturnNoWhenParamsIsNull(){
		String origin = null;
		String destination = null;
		String result = cityRoadBO.connected(origin, destination);
		assertEquals(NO, result);
	}
	@Test
	public void shouldReturnNoWhenParamsIsEmpty(){
		String origin = "";
		String destination = "";
		String result = cityRoadBO.connected(origin, destination);
		assertEquals(NO, result);
	}
	@Test
	public void shouldReturnNoWhenCitiesIsNotConnected(){
		String origin = "Boston";
		String destination = "Trenton";
		HashMap<String,String> map = new HashMap<>();
		map.put(origin, "New York");
		map.put(destination, "Albany");
		when(cityRoadFileDAO.getCityRoadMap(anyString())).thenReturn(map);
		String result = cityRoadBO.connected(origin, destination);
		assertEquals(NO, result);
	}
	@Test
	public void shouldReturnYesWhenCitiesAreConnected(){
		String origin = "Boston";
		String destination = "New York";
		HashMap<String,String> map = new HashMap<>();
		map.put(origin, destination);
		when(cityRoadFileDAO.getCityRoadMap(anyString())).thenReturn(map);
		String result = cityRoadBO.connected(origin, destination);
		assertEquals(YES, result);
	}
	@Test
	public void shouldReturnYesWhenCitiesHaveAConnection(){
		String origin = "Newark";
		String destination = "New York";
		HashMap<String,String> map = new HashMap<>();
		map.put(origin, "Boston");
		map.put("Boston", destination);
		when(cityRoadFileDAO.getCityRoadMap(anyString())).thenReturn(map);
		String result = cityRoadBO.connected(origin, destination);
		assertEquals(YES, result);
	}
}
