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

package wedapro;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.net.URLConnection;
import java.util.ArrayList;
//import java.util.Calendar;
import java.util.HashMap;


import java.util.Locale;








//import java.net.*;
//import java.io.*;
import javax.xml.parsers.DocumentBuilder;
//XML stuff
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import request.CityStateRequest;
import request.LongLatRequest;
import request.ZipCodeRequest;
import core.IRequest;
import core.IRequestVisitor;
import core.IWeatherDataProvider;
import core.WeatherModel;

/**
 * An implementation of the IWeatherDataProvider to provide
 * weather data from the yahoo api.
 * 
 * @author Christian
 *
 */
public class Yahoo implements IWeatherDataProvider, IRequestVisitor {
	private static String woeid;
	private static Document doc;
	private static NodeList nXml;
	private static Node nNodeXml;
	private static Element xmlElement;
	private DateFormat df = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
	private ArrayList<WeatherModel> model;
//	private String Day;
	private String Date;
	private String Low;
	private String High;
	private String Condition;
	
	private int iDay;
	private int count;
	private String location;
	private String WindDir;
	private int Faren, lFaren, hFaren;
	private int Cel, lCel, hCel;
	private String name;

	public Yahoo() {

		this.name = "Yahoo";
		this.model = new ArrayList<WeatherModel>();
		this.location = null;
		
	}

	public void visit(CityStateRequest e) {
		this.setLocation(e.getRequest().get("city") + " "
				+ e.getRequest().get("state"));
		setDay(e.getNumberOfDays());
		//Integer.parseInt(e.getRequest().get("day")));
	}

	public void visit(ZipCodeRequest e) {
		this.setLocation(e.getRequest().get("zip"));
		setDay(e.getNumberOfDays());
		//Integer.parseInt(e.getRequest().get("day")));
	}

	public void visit(LongLatRequest e) {
		this.setLocation(e.getRequest().get("latitude") + " "
				+ e.getRequest().get("longitude"));
		setDay(e.getNumberOfDays());
		//Integer.parseInt(e.getRequest().get("day")));
	}

	public boolean submitRequest(IRequest request) {
		request.accept(this);
		return true;
	}

	@Override
	public ArrayList<WeatherModel> getWeather() {

		// Set the model up. Only calls if the location is not null
		if (this.location != null) {

			// Parse the location. If no error found
			// parse the xml.
			if (this.parse(this.location)) {
				this.model = this.createModels(iDay);
			}
		}

		// Return the model
		return this.model;
	}

	public boolean canRefresh() {

		return true;

	}

	public String getName() {
		return this.name;
	}
	
	/**
	 * Parses the information from the Yahoo weather API website.
	 * 
	 * @param location
	 * 			gathered from visit.
	 * @return true if information has been parsed
	 */
	private boolean parse(String location) {
		try {
			String mod_input = location.replace(" ", "%20");

			URL woeid_url = null;
			woeid_url = new URL(
					"http://query.yahooapis.com/v1/public/yql?q=select%20*%20from"
							+ "%20geo.places%20where%20text%3D%22" + mod_input
							+ "%22&format=xml");
			BufferedReader XMLin = null;
			XMLin = new BufferedReader(new InputStreamReader(
					woeid_url.openStream()));

			String inputLine;
			while ((inputLine = XMLin.readLine()) != null) {
				if (inputLine.startsWith("<query")) {
					String query = inputLine;
					int start = query.indexOf("<woeid>");
					int end = query.indexOf("</woeid>");
					woeid = query.substring(start + 7, end);
				}
			}

			URL weather_info_url = null;
			weather_info_url = new URL(
					"http://weather.yahooapis.com/forecastrss?w=" + woeid);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = null;
			db = dbf.newDocumentBuilder();
			doc = db.parse(weather_info_url.openStream());

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Creates a list of models based on the number of days requested.
	 * 
	 * @param day
	 * 
	 * @return a list of models
	 */
	private ArrayList<WeatherModel> createModels(int day) {
		ArrayList<WeatherModel> modelList = new ArrayList<WeatherModel>();
		WeatherModel model;
		iDay = day;
		for(count = 0; count < iDay; count++) {
			if(count == 0) {
				model = buildFromYahooMap(buildCurrentDayYahooMap());
			} else {
				model = buildFromYahooMap(buildForecastMap(count));
			}
			modelList.add(model);
			model.setDayCount(count);
		}
		
		return modelList;
	}
	
	/**
	 * Creates a HashMap of the current day
	 * 
	 * @return a HashMap of the current day
	 */
	private HashMap<String, String> buildCurrentDayYahooMap() {
		// current weather hashmap
		HashMap<String, String> currentWeatherMap = new HashMap<String, String>();
		
		currentWeatherMap.put("temp_C", getTemp());
		currentWeatherMap.put("windspeedKmph", getWindSpeed());
		currentWeatherMap.put("winddir", getWindDir());
		currentWeatherMap.put("precipMM", getCondition());
		currentWeatherMap.put("humidity", getHumid());
		currentWeatherMap.put("pressure", getPressure());
		currentWeatherMap.put("date", getDate());
		currentWeatherMap.put("description", getDescription());
		
		return currentWeatherMap;
	}

	/**
	 * Returns the weather icon url.
	 * @return the icon url
	 */
	private String getDescription() {
		
		nXml = doc.getElementsByTagName("description");
		nNodeXml = nXml.item(1);
		xmlElement = (Element) nNodeXml;
		
		//Get the core text data
		String data = xmlElement.getTextContent();
		
		//Strip the content from the string
		String data1 = data.split("<img src=\"")[1];
		String data2 = data1.split("\"")[0];
		
		return data2;
		
	}

	/**
	 * Creates a HashMap of the forecast based on the day provided
	 * NB: forecast goes up to 5 days, including the current day
	 * 
	 * @param day
	 * 			chooses what day in the forecast to grab data for
	 * 
	 * @return a HashMap of the forecast for the day provided
	 */
	@SuppressWarnings("deprecation")
	private HashMap<String, String> buildForecastMap(int day) {
		HashMap<String, String> forecastWeather = new HashMap<String, String>();
		// xml part
		nXml = doc.getElementsByTagName("yweather:forecast");
		nNodeXml = nXml.item(day);
		xmlElement = (Element) nNodeXml;
		//Day = xmlElement.getAttribute("day");
		
		lFaren = Integer.parseInt(xmlElement.getAttribute("low"));
		lCel = (((lFaren-32)*5)/9);
		
		hFaren = Integer.parseInt(xmlElement.getAttribute("high"));
		hCel = (((hFaren-32)*5)/9);		
		Date = df.format(java.util.Date.parse(xmlElement.getAttribute("date")));
		Low = String.valueOf(lCel);
		High = String.valueOf(hCel);
		Condition = xmlElement.getAttribute("text");

		forecastWeather.put("date", Date);
		forecastWeather.put("temp_low", Low);
		forecastWeather.put("temp_high", High);
		forecastWeather.put("condition", Condition);
		
		return forecastWeather;
	}

	/**
	 * Creates a WeatherModel for the given HashMap
	 * @param yMap
	 * 			the weather model to convert.
	 * @return a model of the provided weather.
	 */
	private WeatherModel buildFromYahooMap(HashMap<String, String> yMap) {
		WeatherModel model;
		ArrayList<String> modelKey = WeatherModel.getKeys();
		HashMap<String, Object> KVPairs = new HashMap<String, Object>();
		HashMap<String, String> forecast = new HashMap<String, String>();
		if(count == 0) {
			forecast = buildForecastMap(0);
			yMap.put("temp_high", forecast.get("temp_high"));
			yMap.put("temp_low", forecast.get("temp_low"));
		}
		if (yMap.get("temp_C") != null) {
			KVPairs.put(modelKey.get(0),
					Integer.parseInt(yMap.get("temp_C")) + 273);
		}
		if (yMap.get("temp_high") != null) {
			KVPairs.put(modelKey.get(1),
					Integer.parseInt(yMap.get("temp_high")) + 273);
		}
		if (yMap.get("temp_low") != null) {
			KVPairs.put(modelKey.get(2),
					Integer.parseInt(yMap.get("temp_low")) + 273);
		}
		if (yMap.get("date") != null) {
			KVPairs.put(modelKey.get(3), yMap.get("date"));
		}
		if (yMap.get("humidity") != null) {
			KVPairs.put(modelKey.get(4), Double.parseDouble(yMap.get("humidity")));
		}
		if (yMap.get("windspeedKmph") != null) {
			KVPairs.put(modelKey.get(6), Double.parseDouble(yMap.get("windspeedKmph")));
		}
		if(yMap.get("description") != null) {
			KVPairs.put(modelKey.get(14), yMap.get("description"));
		}

		model = new WeatherModel(KVPairs);
		return model;
	}

	/**
	 * Grab Wind Direction from XML data
	 * @return direction of wind as String 
	 */
	private String getWindDir() {
		nXml = doc.getElementsByTagName("yweather:wind");
		nNodeXml = nXml.item(0);
		xmlElement = (Element) nNodeXml;
		String angle = xmlElement.getAttribute("direction");
		int iAngle = Integer.parseInt(angle);
		// N to E
		if ((iAngle >= 0 & iAngle < 23) | (iAngle >= 350)) {
			WindDir = "N";
		}
		if (iAngle >= 23 & iAngle < 45) {
			WindDir = "NNE";
		}
		if (iAngle >= 45 & iAngle < 68) {
			WindDir = "NE";
		}
		if (iAngle >= 68 & iAngle < 90) {
			WindDir = "ENE";
		}

		// E to S
		if (iAngle >= 90 & iAngle < 113) {
			WindDir = "E";
		}
		if (iAngle >= 113 & iAngle < 135) {
			WindDir = "ESE";
		}
		if (iAngle >= 135 & iAngle < 158) {
			WindDir = "SE";
		}
		if (iAngle >= 158 & iAngle < 180) {
			WindDir = "SSE";
		}

		// S to W
		if (iAngle >= 180 & iAngle < 203) {
			WindDir = "S";
		}
		if (iAngle >= 203 & iAngle < 225) {
			WindDir = "SSW";
		}
		if (iAngle >= 225 & iAngle < 248) {
			WindDir = "SW";
		}
		if (iAngle >= 248 & iAngle < 270) {
			WindDir = "WSW";
		}

		// N to W
		if (iAngle >= 270 & iAngle < 293) {
			WindDir = "W";
		}
		if (iAngle >= 293 & iAngle < 315) {
			WindDir = "WNW";
		}
		if (iAngle >= 315 & iAngle < 338) {
			WindDir = "NW";
		}
		if (iAngle >= 338 & iAngle < 350) {
			WindDir = "NNW";
		}
		return WindDir;
	}
/*
	private String getLoc() {
		nXml = doc.getElementsByTagName("yweather:location");
		nNodeXml = nXml.item(0);
		xmlElement = (Element) nNodeXml;
		return xmlElement.getAttribute("city");
	}

	private String getRegion() {
		nXml = doc.getElementsByTagName("yweather:location");
		nNodeXml = nXml.item(0);
		xmlElement = (Element) nNodeXml;
		return xmlElement.getAttribute("region");
	}

	private String getCountry() {
		nXml = doc.getElementsByTagName("yweather:location");
		nNodeXml = nXml.item(0);
		xmlElement = (Element) nNodeXml;
		return xmlElement.getAttribute("country");
	}
*/
	/**
	 * Grabs the pressure from XML data
	 * @return pressure in inches
	 */
	private String getPressure() {
		nXml = doc.getElementsByTagName("yweather:atmosphere");
		nNodeXml = nXml.item(0);
		xmlElement = (Element) nNodeXml;
		return xmlElement.getAttribute("pressure");
	}

	/**
	 * Grabs the wind speed from XML data
	 * @return speed of wind in mph
	 */
	private String getWindSpeed() {
		nXml = doc.getElementsByTagName("yweather:wind");
		nNodeXml = nXml.item(0);
		xmlElement = (Element) nNodeXml;
		return xmlElement.getAttribute("speed");
	}
/*
	// units "mi"
	private String getVisibility() {
		nXml = doc.getElementsByTagName("yweather:atmosphere");
		nNodeXml = nXml.item(0);
		xmlElement = (Element) nNodeXml;
		return xmlElement.getAttribute("visibility");
	}
*/
	/**
	 * Grabs the temperature from XML data in Fahrenheit and converts it to Celsius
	 * @return Celsius reading of temperature
	 */
	private String getTemp() {
		nXml = doc.getElementsByTagName("yweather:wind");
		nNodeXml = nXml.item(0);
		xmlElement = (Element) nNodeXml;
		Faren = Integer.parseInt(xmlElement.getAttribute("chill"));
		Cel = (((Faren-32)*5)/9);//((Faren - 32) * (5.0 / 9.0));
		return String.valueOf(Cel);
	}

	/**
	 * Grabs the humidity from XML data
	 * @return the humidity as a %
	 */
	private String getHumid() {
		nXml = doc.getElementsByTagName("yweather:atmosphere");
		nNodeXml = nXml.item(0);
		xmlElement = (Element) nNodeXml;
		return xmlElement.getAttribute("humidity");
	}

	/**
	 * Grabs the condition from XML data
	 * @return the condition
	 */
	private String getCondition() {
		nXml = doc.getElementsByTagName("yweather:condition");
		nNodeXml = nXml.item(0);
		xmlElement = (Element) nNodeXml;
		return xmlElement.getAttribute("text");
	}
	
	/**
	 * Grabs the date from XML data 
	 * @return the date as Wed, 04 Dec 2013 11:51 am EST
	 */
	@SuppressWarnings("deprecation")
	private String getDate() {
		nXml = doc.getElementsByTagName("yweather:condition");
		nNodeXml = nXml.item(0);
		xmlElement = (Element) nNodeXml;
		String testString = xmlElement.getAttribute("date");
		String sDay = testString.substring(5, 7);
		String sMonth = testString.substring(8, 11);
		String sYear = testString.substring(12, 16);
		String pseudoTime = sDay + " " + sMonth + " " + sYear;
		Date = df.format(java.util.Date.parse(pseudoTime));

		//Return the date
		return Date;
	}
/*
	public String getSunrise() {
		nXml = doc.getElementsByTagName("yweather:astronomy");
		nNodeXml = nXml.item(0);
		xmlElement = (Element) nNodeXml;
		return xmlElement.getAttribute("sunrise");
	}

	public String getSunset() {
		nXml = doc.getElementsByTagName("yweather:astronomy");
		nNodeXml = nXml.item(0);
		xmlElement = (Element) nNodeXml;
		return xmlElement.getAttribute("sunset");
	}

	public String get5dayForecast(int day) {
		nXml = doc.getElementsByTagName("yweather:forecast");
		nNodeXml = nXml.item(day);
		xmlElement = (Element) nNodeXml;
		Day = xmlElement.getAttribute("day");
		Date = xmlElement.getAttribute("date");
		Low = xmlElement.getAttribute("low");
		High = xmlElement.getAttribute("high");
		Condition = xmlElement.getAttribute("text");
		return (Day + " " + Date + " " + Low + " " + High + " " + Condition);
	}
*/
	
	/**
	 * Sets the location either from CityState, Zip or Long, Lat
	 * @param location
	 * 			to be set
	 */
	private void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Sets the day passed through from CityState, Zip or LongLat requests
	 * @param day
	 * 			to be set
	 */
	private void setDay(int day) {
		this.iDay = day;
	}
}
