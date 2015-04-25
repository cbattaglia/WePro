/**
 * This file is part of WePro.

    WePro is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    WePro is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with WePro.  If not, see <http://www.gnu.org/licenses/>.
 */

package tests.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import core.WeatherModel;

/**
 * Checks the WeatherModel's operations
 * 
 * @author Forest
 *
 */
public class WeatherModelTester {
	
	/**
	 * Checks the construction
	 */
	@Test
	public void checkConstruction() {
		
		
		//Local Declarations
		WeatherModel model;
		
		//Create nullary model
		model = new WeatherModel();
		
		//Check default values
		//Check temperature
		assertEquals(-459, model.getFahrenheit());
		assertEquals(-459, model.getMaxFahrenheit());
		assertEquals(-459, model.getMinFahrenheit());
		assertEquals(-273, model.getCelsius());
		assertEquals(-273, model.getMaxCelsius());
		assertEquals(-273, model.getMinCelsius());
		
		//Check Information
		assertEquals(0.0, model.getHumidity(), 0.0);
		assertEquals(0.0, model.getPrecipitation(), 0.0);
		assertEquals(0.0, model.getWindSpeedKPH(), 0.0);
		
		//Check Location
		assertTrue("".equals(model.getCity()));
		assertTrue("".equals(model.getState()));
		assertTrue("".equals(model.getCountry()));
		assertEquals(0.0, model.getLongitude(), 0.0);
		assertEquals(0.0, model.getLatitude(), 0.0);
		assertEquals(0, model.getZip());
		
		//Check date
		assertTrue("00/00/0000".equals(model.getDate()));
		assertEquals(0, model.getDayCount());
		
	}
	
	/**
	 * Checks the construction (non nullary)
	 */
	@Test
	public void checkNonNullaryConstruction() {
		
		//Local Declaration
		WeatherModel model;
		ArrayList<String> list;
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		
		//Get keys to work with
		list = WeatherModel.getKeys();

		//Populate the map
		map.put(list.get(0), 4); //Kelvin
		map.put(list.get(1), 3); //Max Kelvin
		map.put(list.get(2), 5); //Min Kelvin
		map.put(list.get(3), "02/12/2008"); //date
		map.put(list.get(4), 7.0); //humidity
		map.put(list.get(5), 8.0); //precipitation
		map.put(list.get(6), 9.0); //windSpeedKPH
		map.put(list.get(7), 10); //dayCount
		map.put(list.get(8), "Cashville"); //City
		map.put(list.get(9), "Awesomeness"); //State
		map.put(list.get(10), 11611); //zip
		map.put(list.get(11), "US OF A!"); //country
		map.put(list.get(12), 11.0); //longitude
		map.put(list.get(13), 12.0); //latitude
		map.put(list.get(14), "boobies"); //imageURL
		
		//Make sure size is correct
		assertEquals(15, map.size());
		
		//Pass through to constructor
		model = new WeatherModel(map);
		
		//Check values
		//Check temperature
		assertEquals(map.get(list.get(0)), model.getKelvin());
		assertEquals(map.get(list.get(1)), model.getMaxKelvin());
		assertEquals(map.get(list.get(2)), model.getMinKelvin());
		
		//Check Information
		assertEquals((Double)map.get(list.get(4)), model.getHumidity(), 0.0);
		assertEquals((Double)map.get(list.get(5)), model.getPrecipitation(), 0.0);
		assertEquals((Double)map.get(list.get(6)), model.getWindSpeedKPH(), 0.0);
		
		//Check Location
		assertTrue(map.get(list.get(8)).equals(model.getCity()));
		assertTrue(map.get(list.get(9)).equals(model.getState()));
		assertEquals(map.get(list.get(10)), model.getZip());
		assertTrue(map.get(list.get(11)).equals(model.getCountry()));
		assertEquals((Double)map.get(list.get(12)), model.getLongitude(), 0.0);
		assertEquals((Double)map.get(list.get(13)), model.getLatitude(), 0.0);
		assertEquals((String)map.get(list.get(14)), model.getImageURL());

		
		//Check date
		assertEquals(map.get(list.get(3)), model.getDate());
		assertEquals(map.get(list.get(7)), model.getDayCount());
		
	}
	
	/**
	 * Checks the setters for temperature
	 */
	@Test
	public void checkTemperature() {
		
		//Local Declarations
		WeatherModel model;
		
		int kelvin = 33;
		int minKelvin = 0;
		int maxKelvin = 373;
		
		//Get Celsius
		int celsius = kelvin - 273;
		int minCelsius = minKelvin - 273;
		int maxCelsius = maxKelvin - 273;
		
		//Get Fahrenheit
		int fah = (int) (celsius * 1.8 + 32);
		int minFah = (int) (minCelsius * 1.8 + 32);
		int maxFah = (int) (maxCelsius * 1.8 + 32);
				
		//Create nullary model
		model = new WeatherModel();
		
		//Set Kelvin
		model.setKelvin(kelvin);		
		model.setMaxKelvin(maxKelvin);
		model.setMinKelvin(minKelvin);
		
		//Check values - Celsius
		assertEquals(maxCelsius, model.getMaxCelsius());
		assertEquals(minCelsius, model.getMinCelsius());
		assertEquals(celsius, model.getCelsius());
		
		//Check values - Fahrenheit
		assertEquals(maxFah, model.getMaxFahrenheit());
		assertEquals(minFah, model.getMinFahrenheit());
		assertEquals(fah, model.getFahrenheit());
		
	}
	
	/**
	 * Checks the setters for humidity, precipitation, and pressure
	 */
	@Test
	public void checkInformation() {
		
		//Local Declaration
		WeatherModel model;
		double humidity = 12;
		double precipitation = 10;
		double speed = 11;
		
		double maxHumidity = 100;
		double minHumidity = 0;
		double maxPrecipitation = 100;
		double minPrecipitation = 0;
		double minSpeed = 0;
		double maxSpeed = 9999;
		double adjustment = .00000001;
		
		
		//Create nullary model
		model = new WeatherModel();
		
		//Set information
		model.setHumidity(humidity);
		model.setPrecipitation(precipitation);
		model.setWindSpeedKPH(speed);
		
		//Check values
		assertEquals(humidity, model.getHumidity(), 0.0);
		assertEquals(precipitation, model.getPrecipitation(), 0.0);
		assertEquals(speed, model.getWindSpeedKPH(), 0.0);
		
		//Error check information - test boundaries min and max
		
		//Set information - max
		model.setHumidity(maxHumidity);
		model.setPrecipitation(maxPrecipitation);
		model.setWindSpeedKPH(maxSpeed);
		
		//Check values
		assertEquals(maxHumidity, model.getHumidity(), 0.0);
		assertEquals(maxPrecipitation, model.getPrecipitation(), 0.0);
		assertEquals(maxSpeed, model.getWindSpeedKPH(), 0.0);
	
		//Set information - min
		model.setHumidity(minHumidity);
		model.setPrecipitation(minPrecipitation);
		model.setWindSpeedKPH(minSpeed);
		
		//Check values
		assertEquals(minHumidity, model.getHumidity(), 0.0);
		assertEquals(minPrecipitation, model.getPrecipitation(), 0.0);
		assertEquals(minSpeed, model.getWindSpeedKPH(), 0.0);
		
		//Check max and min
		model.setHumidity(minHumidity-adjustment);
		model.setPrecipitation(minPrecipitation-adjustment);
		model.setWindSpeedKPH(minSpeed-adjustment);
		model.setHumidity(maxHumidity+adjustment);
		model.setPrecipitation(maxPrecipitation+adjustment);
		model.setWindSpeedKPH(maxSpeed+adjustment);
		
		//Check values - nothing changes from last value
		assertEquals(minHumidity, model.getHumidity(), 0.0);
		assertEquals(minPrecipitation, model.getPrecipitation(), 0.0);
		assertEquals(minSpeed, model.getWindSpeedKPH(), 0.0);
		
	}
	
	/**
	 * Checks the setters for location
	 */
	@Test
	public void checkLocation() {
		
		//Local Declaration
		WeatherModel model;
		
		//Instantiate
		model = new WeatherModel();
		
		//Zip
		int maxZip = 99999;
		int minZip = 10000;
		int zip = 33333;
		
		//City, state, and country
		String city = "asdf";
		String state = "asdf2";
		String country = "asdf3";
		
		//Will it allow the empty string?
		String emptyString = "";
		
		double longitude = 0;
		double latitude = 0;
		double maxLongitude = 180;
		double maxLatitude = 90;
		double minLongitude = -180;
		double minLatitude = -90;
		double adjustment = .00000001;
		
		//Check zip
		model.setZip(zip);
		assertEquals(zip, model.getZip());
		model.setZip(maxZip);
		assertEquals(maxZip, model.getZip());
		model.setZip(minZip);
		assertEquals(minZip, model.getZip());
		
		//Check max and min boundaries - should not exceed
		model.setZip(maxZip+1);
		assertEquals(minZip, model.getZip());
		model.setZip(minZip-1);
		assertEquals(minZip, model.getZip());
		
		//Set city, state, country
		model.setCity(city);
		model.setState(state);
		model.setCountry(country);
		
		//Check values
		assertEquals(city, model.getCity());
		assertEquals(state, model.getState());
		assertEquals(country, model.getCountry());
		
		//Try to set to null
		model.setCity(null);
		model.setState(null);
		model.setCountry(null);
		
		//Check values - doesnt change
		assertEquals(city, model.getCity());
		assertEquals(state, model.getState());
		assertEquals(country, model.getCountry());
		
		//Try to set to empty
		model.setCity(emptyString);
		model.setState(emptyString);
		model.setCountry(emptyString);
		
		//Check values - does change
		assertEquals(emptyString, model.getCity());
		assertEquals(emptyString, model.getState());
		assertEquals(emptyString, model.getCountry());
		
		//Check longitude and Latitude
		model.setLatitude(latitude);
		model.setLongitude(longitude);
		
		//Check values
		assertEquals(latitude, model.getLatitude(), 0.0);
		assertEquals(longitude, model.getLongitude(), 0.0);
		
		//Check max
		model.setLatitude(maxLatitude);
		model.setLongitude(maxLongitude);
		
		//Check values
		assertEquals(maxLatitude, model.getLatitude(), 0.0);
		assertEquals(maxLongitude, model.getLongitude(), 0.0);
		
		//Check min
		model.setLatitude(minLatitude);
		model.setLongitude(minLongitude);
		
		//Check values
		assertEquals(minLatitude, model.getLatitude(), 0.0);
		assertEquals(minLongitude, model.getLongitude(), 0.0);
		
		//Check max + adjustment
		model.setLatitude(maxLatitude+adjustment);
		model.setLongitude(maxLongitude+adjustment);
		
		//Check values - doesnt change
		assertEquals(minLatitude, model.getLatitude(), 0.0);
		assertEquals(minLongitude, model.getLongitude(), 0.0);
		
		//Check min - adjustment
		model.setLatitude(minLatitude - adjustment);
		model.setLongitude(minLongitude - adjustment);
		
		//Check values - doesnt change
		assertEquals(minLatitude, model.getLatitude(), 0.0);
		assertEquals(minLongitude, model.getLongitude(), 0.0);
		
	}
	
	/**
	 * Checks the setters for date and count.
	 */
	@Test
	public void checkDate() {
		
		//Local declaration
		WeatherModel model;
		
		//Instantiate
		model = new WeatherModel();
		
		String date1 = "02/12/2008";
		String emptyDate = "00/00/0000";
		String wrongMonth = "13/12/0000";
		String wrongDate = "12/00/0000";
		String wrongSize1 = "120/12/0000";
		String wrongSize2 = "12/120/0000";
		String wrongSize3 = "12/12/00000";
		String wrongSize4 = "1/12/00000";
		String nullDate = null;
		
		int dateCount = 3;
		int negDateCount = -1;
		int zeroCount = 0;
		
		//Set the date normally
		model.setDate(date1);
		assertEquals(date1, model.getDate());
		
		//Try to set the date with empty, sets correctly
		model.setDate(emptyDate);
		assertEquals(emptyDate, model.getDate());
		
		//Set wrong date, does not change
		model.setDate(wrongMonth);
		assertEquals(emptyDate, model.getDate());
		
		//Set wrong date, does not change
		model.setDate(wrongDate);
		assertEquals(emptyDate, model.getDate());
		
		//Try to set wrong date, does not change
		model.setDate(nullDate);
		assertEquals(emptyDate, model.getDate());
		
		//Try to set wrong date, does not change
		model.setDate(wrongSize1);
		assertEquals(emptyDate, model.getDate());
		
		//Try to set wrong date, does not change
		model.setDate(wrongSize2);
		assertEquals(emptyDate, model.getDate());
		
		//Try to set wrong date, does not change
		model.setDate(wrongSize3);
		assertEquals(emptyDate, model.getDate());
		
		//Try to set wrong date, does not change
		model.setDate(wrongSize4);
		assertEquals(emptyDate, model.getDate());
		
		//Set count
		model.setDayCount(dateCount);
		assertEquals(dateCount, model.getDayCount());
		
		//Set count - doesn't change
		model.setDayCount(negDateCount);
		assertEquals(dateCount, model.getDayCount());
		
		//Set count to zero - does change
		model.setDayCount(zeroCount);
		assertEquals(zeroCount, model.getDayCount());
		
	}
	
	/**
	 * Check equality and copying
	 */
	@Test
	public void checkEqualCopying() {
	
		//Check Equality
		//Local Declaration
		WeatherModel model, equalModel, unEqualModel;
		ArrayList<String> list;
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		
		//Get keys to work with
		list = WeatherModel.getKeys();

		//Get keys to work with
		list = WeatherModel.getKeys();

		//Populate the map
		map.put(list.get(0), 4); //Kelvin
		map.put(list.get(1), 3); //Max Kelvin
		map.put(list.get(2), 5); //Min Kelvin
		map.put(list.get(3), "02/12/2008"); //date
		map.put(list.get(4), 7.0); //humidity
		map.put(list.get(5), 8.0); //precipitation
		map.put(list.get(6), 9.0); //windSpeedKPH
		map.put(list.get(7), 10); //dayCount
		map.put(list.get(8), "Cashville"); //City
		map.put(list.get(9), "Awesomeness"); //State
		map.put(list.get(10), 11611); //zip
		map.put(list.get(11), "US OF A!"); //country
		map.put(list.get(12), 11.0); //longitude
		map.put(list.get(13), 12.0); //latitude
		
		//Make sure size is correct
		assertEquals(14, map.size());
		
		//Pass through to constructor
		model = new WeatherModel(map);
		
		equalModel = new WeatherModel(map);
		
		map.put(list.get(13), 13.0);
		
		unEqualModel = new WeatherModel(map);
		
		//Check equality
		assertTrue(model.equals(equalModel));
		
		//Check unEquality
		assertFalse(model.equals(unEqualModel));
		
		//Check null equality
		assertFalse(model.equals(null));
		
		//Check copy
		unEqualModel.copy(model);
		
		//Check they are equal
		assertTrue(model.equals(unEqualModel));
		assertTrue(unEqualModel.equals(unEqualModel));
		assertTrue(unEqualModel.equals(model));
		
		//Try to copy null
		unEqualModel.copy(null);
		
		//Show nothing has changed
		assertTrue(model.equals(unEqualModel));
		assertTrue(unEqualModel.equals(unEqualModel));
		assertTrue(unEqualModel.equals(model));
		
		
	}
	
}
