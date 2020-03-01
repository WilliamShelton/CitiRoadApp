package com.example.CityRoad.dao;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CityRoadFileDAOTest {
	
	private CityRoadFileDAO cityRoadFileDAO;
	private String cityRoadMapFileName = "City.txt";
	
	@Before
	public void setup(){
		cityRoadFileDAO = new CityRoadFileDAO();
	}
	@Test
	public void shouldThrowExceptionWhenParamIsNull(){
		cityRoadMapFileName = null;
		Assertions.assertThatThrownBy(() -> cityRoadFileDAO.getCityRoadMap(cityRoadMapFileName))
				.isInstanceOf(IllegalArgumentException.class);
	}
	@Test
	public void shouldReturnEmptyMapWhenParamIsEmpty(){
		cityRoadMapFileName = "";
		HashMap<String,String> map = new HashMap<>();
		HashMap<String,String> resultMap = cityRoadFileDAO.getCityRoadMap(cityRoadMapFileName);
		assertEquals(map, resultMap);
	}
	@Test
	public void shouldReturnPopulatedMap(){
		HashMap<String,String> resultMap = cityRoadFileDAO.getCityRoadMap(cityRoadMapFileName);
		assertTrue(!resultMap.isEmpty());
	}
}
