package com.example.CityRoad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.CityRoad.bo.CityRoadBO;

@RestController
public class CityRoadController {
	@Autowired
	private CityRoadBO cityRoadBO;
	@RequestMapping(value = "/connected", method = RequestMethod.GET)
	public String connected(@RequestParam(value = "origin") String origin, 
							@RequestParam(value = "destination") String destination)
	{
		String result = cityRoadBO.connected(origin, destination);
		return result;
	}
}
