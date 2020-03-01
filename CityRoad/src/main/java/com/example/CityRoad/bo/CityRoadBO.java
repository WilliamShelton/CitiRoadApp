package com.example.CityRoad.bo;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CityRoad.dao.CityRoadFileDAO;

@Service("cityRoadBO")
public class CityRoadBO {
	private final static String sYes = "YES";
	private final static String sNo = "NO";
	private final static String sCityRoadMapFileName = "City.txt";
	private static HashMap <String, String> cityRoadMap = null;
	@Autowired
	private CityRoadFileDAO cityRoadFileDAO;
	
	public String connected(String sCityA, String sCityB)
	{
		if(sCityA == null || sCityB == null){
			return sNo;
		}
		sCityA = sCityA.trim();
		sCityB = sCityB.trim();
		String sValue = null;
		if (cityRoadMap == null )
			 getCityRoadMap(sCityRoadMapFileName);
		sValue = cityRoadMap.get(sCityA);
		if ( ( sValue != null ) && (sValue.equals(sCityB) ))
				return sYes;
		// Flip Around
		sValue = cityRoadMap.get(sCityB);
		if ( ( sValue != null ) && (sValue.equals(sCityA) ))
			return sYes;
		// Find road through another City
		String sCityPassThrough = null;  //  sCityA -- sCityPassThrough -- sCityB
		sCityPassThrough = cityRoadMap.get(sCityA);
		sValue = cityRoadMap.get(sCityPassThrough);
		if ( ( sValue != null ) && (sValue.equals(sCityB) ))
				return sYes;		
		sCityPassThrough = cityRoadMap.get(sCityB);  //  sCityB -- sCityPassThrough -- sCityA
		sValue = cityRoadMap.get(sCityPassThrough);
		if ( ( sValue != null ) && (sValue.equals(sCityA) ))
				return sYes;				
		return sNo;
	}
	private synchronized void getCityRoadMap(String sCityRoadMapFileName)
	{
		if (cityRoadMap != null )
			return;  // Kind of double check design pattern, 
		cityRoadMap  = cityRoadFileDAO.getCityRoadMap(sCityRoadMapFileName);
	}
	public void printCityRoadMap()
	{ //  just to test incrementally
		if (cityRoadMap == null )
			getCityRoadMap(sCityRoadMapFileName);		
		cityRoadMap.forEach((key ,value)->System.out.println( key + " , " + value));
	}
}