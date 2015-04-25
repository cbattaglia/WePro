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
    
    
    Please note this lic agreement does not extend to the World Weather Online.
    Please view their lic agreement on the link in the class description.
 */
package wedapro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
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
 * @author Forest
 * 
 *         Utilizes the World Weather Online website to pool data and create
 *         weather objects.
 * 
 *         Credit to World Weather Online for the limited, yet free, usage of
 *         their service. http://www.worldweatheronline.com/
 * 
 * 
 *         Developer note: When handling the visitor interface, the developer
 *         should add a pre-built string that handles what type of case it is
 *         building from. This will deal with the issue of not storing the
 *         requests as requests, but as their primitive types on the class
 *         itself. That way, we dont have to have a visitor for the visitor in
 *         order to submit requests.
 */
public class WWODataProvider implements IWeatherDataProvider, IRequestVisitor {

	/**
	 * This string will be built to the spec of the WWODataProvider in order to
	 * handle the destination as a string, for when a submit is made, instead of
	 * storing the request as an object and causing a massive maintenance issue
	 * with the IRequest type.
	 */
	private String locationString;

	/**
	 * The base of the url.
	 */
	private final String webURLBase = "http://api.worldweatheronline.com/free/v1/weather.ashx?";

	private long lastRefresh;

	private String key;

	private String name;

	private ArrayList<WeatherModel> model;

	/**
	 * Constructor
	 */
	public WWODataProvider(String key) {

		// Setup defaults
		this.key = "";
		this.name = "World Weather Online";
		lastRefresh = 0;
		model = new ArrayList<WeatherModel>();

		// If key is not null, set it up
		if (key != null)
			this.key = key;

	}

	@Override
	public void visit(CityStateRequest e) {

		// Set the string equal to q=num...
		this.locationString = "num_of_days=" + e.getNumberOfDays() + "&q=";

		// Deal with spaces
		String city = e.getRequest().get("city").replace(' ', '+');
		String state = e.getRequest().get("state").replace(' ', '+');

		// Add location
		this.locationString += city + "," + state;

	}

	@Override
	public void visit(ZipCodeRequest e) {

		// Set the string equal to q=num...
		this.locationString = "num_of_days=" + e.getNumberOfDays() + "&q=";

		// Deal with zip
		String zip = e.getRequest().get("zip");

		// Add location
		this.locationString += zip;

	}

	@Override
	public void visit(LongLatRequest e) {

		// Set the string equal to q=num...
		this.locationString = "num_of_days=" + e.getNumberOfDays() + "&q=";

		// Get long and lat
		String longitude = e.getRequest().get("longitude");
		String latitude = e.getRequest().get("latitude");

		// Add location
		this.locationString += latitude + "," + longitude;

	}

	@Override
	public boolean submitRequest(IRequest request) {

		// Use the visitor pattern to build the location string
		request.accept(this);

		// Everything worked out grand. Returning true
		return true;

	}

	@Override
	public ArrayList<WeatherModel> getWeather() {

		//Local Declarations
		Calendar cal = Calendar.getInstance();

		//If the key's length is 24 and it can be refreshed
		//refresh it!
		if (key.length() == 24 && canRefresh()) {
			
			//Set the model up
			this.model = this.createModel();
			
			//Update time
			this.lastRefresh = cal.getTime().getTime();
		}
			
		
		//Return the model
		return this.model;
	}

	@Override
	public boolean canRefresh() {
		
		//Get the time
		Calendar cal = Calendar.getInstance();
		
		//Return the time
		return cal.getTime().getTime() > this.lastRefresh + 5000;
		
	}

	@Override
	public String getName() {

		return this.name;
	}

	/**
	 * Returns the key
	 * 
	 * @return the key
	 */
	public String getKey() {

		return this.key;

	}

	/**
	 * Creates the list of models.  
	 * The first model is the current weather.
	 * The proceding models are the next day's weather
	 * 
	 * @return The list of models.
	 */
	private ArrayList<WeatherModel> createModel() {

		// Build URL string
		String urlContentString = this.webURLBase + this.locationString
				+ "&key=" + this.key;

		System.out.println(urlContentString);

		try {
			
			//Get the URL and convert it to a xml doc.
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db;
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(new URL(urlContentString).openStream());

			

			// Parse xml file. Return the model
			return this.parseXML(doc);

		} catch (Exception e) {

			// Error caught, return empty list.
			e.printStackTrace();
			return new ArrayList<WeatherModel>();
		}

	}

	/**
	 * Parses an xml doc and creates the model object
	 * 
	 * @param ad document
	 * @return a completed model, or null
	 */

	public ArrayList<WeatherModel> parseXML(Document doc) {
		
		// Local Declarations
		WeatherModel model;
		ArrayList<WeatherModel> modelList = new ArrayList<WeatherModel>();
		
		if(doc == null) return modelList;


		// Build two hashMaps. The first one is the current weather.
		HashMap<String, String> currentWeatherMap = new HashMap<String, String>();
		currentWeatherMap.put("temp_C", "");
		currentWeatherMap.put("windspeedKmph", "");
		currentWeatherMap.put("winddirDegree", "");
		currentWeatherMap.put("precipMM", "");
		currentWeatherMap.put("humidity", "");
		currentWeatherMap.put("pressure", "");
		currentWeatherMap.put("weatherIconUrl", "");

		// Second one for iterative weather
		HashMap<String, String> iterativeWeatherMap = new HashMap<String, String>();
		iterativeWeatherMap.put("tempMaxC", "");
		iterativeWeatherMap.put("tempMinC", "");
		iterativeWeatherMap.put("windspeedKmph", "");
		iterativeWeatherMap.put("date", "");

		// Keeps the count. Used to set for dayCount.
		int count = 0;
		
		try {

			// Parse to get model data.

			// Iterate past XML portion first, then grab data section
			NodeList docNodes = doc.getChildNodes().item(0).getChildNodes();

			// The nodes are broken down into 3+ pieces. request,
			// current_condition, and the remaining pieces are weather.

			for (int i = 0; i < docNodes.getLength(); i++) {

				Node node = docNodes.item(i);

				// Handle case for current weather
				if (node.getNodeName().equals("current_condition")) {
					for (int j = 0; j < node.getChildNodes().getLength(); j++) {
						Node childNode = node.getChildNodes().item(j);

						// If the node exists in the weather map, add it.
						if (currentWeatherMap.containsKey(childNode
								.getNodeName())) {

							// Add the key/value pair
							currentWeatherMap.put(childNode.getNodeName(),
									childNode.getChildNodes().item(0)
											.getNodeValue());
						}

					}

				}

				// Check the next set of weather "objects"
				if (node.getNodeName().equals("weather")) {

					// Iterate over the nodes
					for (int j = 0; j < node.getChildNodes().getLength(); j++) {
						Node childNode = node.getChildNodes().item(j);

						// if the key is contained in the map, add it.
						if (iterativeWeatherMap.containsKey(childNode
								.getNodeName())) {

							iterativeWeatherMap.put(childNode.getNodeName(),
									childNode.getChildNodes().item(0)
											.getNodeValue());

						}

					}

					// Build model from map

					// First, check to see if count is equal to 0
					// If it is, then the first weather object is related to the
					// current weather.
					if (count == 0) {
						iterativeWeatherMap.putAll(currentWeatherMap);
					}
					// Build the model
					model = buildModelFromWWOMap(iterativeWeatherMap);

					// Add the model and set the count
					modelList.add(model);
					model.setDayCount(count);
					count++;
					
					//Reset map
					iterativeWeatherMap = new HashMap<String, String>();
					iterativeWeatherMap.put("tempMaxC", "");
					iterativeWeatherMap.put("tempMinC", "");
					iterativeWeatherMap.put("windspeedKmph", "");
					iterativeWeatherMap.put("date", "");

				}

			}

			// Return the completed model.
			return modelList;

		} catch (Exception e) {
			e.printStackTrace();

			return modelList;
		}

	}

	/**
	 * Returns a Weather model generated from the KV pairs of a wwo map file
	 * 
	 * @param wwoMap
	 *            hashmap
	 * @return a model
	 */
	private WeatherModel buildModelFromWWOMap(HashMap<String, String> wwoMap) {

		// Local Declarations
		WeatherModel model;
		ArrayList<String> modelKey = WeatherModel.getKeys();
		HashMap<String, Object> KVPairs = new HashMap<String, Object>();

		// Replace keywords in map with keyWords from model.

		// Temperature
		if (wwoMap.get("temp_C") != null) 
			KVPairs.put(modelKey.get(0),
					Integer.parseInt(wwoMap.get("temp_C")) + 273);

		// Max Temp
		if (wwoMap.get("tempMaxC") != null)
			KVPairs.put(modelKey.get(1),
					Integer.parseInt(wwoMap.get("tempMaxC")) + 273);

		// Min Temp
		if (wwoMap.get("tempMinC") != null)
			KVPairs.put(modelKey.get(2),
					Integer.parseInt(wwoMap.get("tempMinC")) + 273);

		// WindSpeed KPH
		if (wwoMap.get("windspeedKmph") != null)
			KVPairs.put(modelKey.get(6),
					Double.parseDouble(wwoMap.get("windspeedKmph")));

		// PrecipMM
		if (wwoMap.get("precipMM") != null)
			KVPairs.put(modelKey.get(5),
					Double.parseDouble(wwoMap.get("precipMM")));

		// Humidity
		if (wwoMap.get("humidity") != null)
			KVPairs.put(modelKey.get(4),
					Double.parseDouble(wwoMap.get("humidity")));

		// Date
		// Convert date first
		if (wwoMap.get("date") != null) {
			String preConvertDate = wwoMap.get("date");
			String year = preConvertDate.split("-")[0];
			String month = preConvertDate.split("-")[1];
			String date = preConvertDate.substring(preConvertDate
					.lastIndexOf('-') + 1);

			// Convert back to date
			String finalDate = month + "/" + date + "/" + year;

			KVPairs.put(modelKey.get(3), finalDate);

		}
		
		//Weather Icon url
		if(wwoMap.get("weatherIconUrl") != null) {
			KVPairs.put(modelKey.get(14), wwoMap.get("weatherIconUrl"));
		}

		// Create Model
		model = new WeatherModel(KVPairs);

		// Return model
		return model;
	}

}
