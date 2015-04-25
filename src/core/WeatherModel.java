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
package core;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class holds the information concerning the Weather Model Object. This
 * can be used in multiple situations, as one part can be used for the current
 * weather. The other can be used to store information for the following days.
 * As such, some parameters may not be set (you won't know the current weather
 * of tomorrow, for example). Default values for strings will be set to the
 * empty string, numbers set to either a max or min or a zero value.
 * 
 * @author Forest
 * 
 */
public class WeatherModel {

	/**
	 * Stores the kelvin
	 */
	private int kelvin;

	/**
	 * Stores the kelvin
	 */
	private int maxKelvin;

	/**
	 * Stores the kelvin
	 */
	private int minKelvin;

	/**
	 * Stores the month, date, and year as string type: MM/DD/YYYY
	 */
	private java.lang.String date;

	/**
	 * Stores the information about the humidity
	 */
	private double humidity;

	/**
	 * Stores the information about the precipitation
	 */
	private double precipitation;

	/**
	 * Stores the information about the wind speed in kilometers per hr
	 */
	private double windSpeedKph;

	/**
	 * Day count - use for order.
	 */
	private int dayCount;

	/**
	 * The city.
	 */
	private String city;

	/**
	 * The state
	 */
	private String state;

	/**
	 * The zip code
	 */
	private int zip;

	/**
	 * Country
	 */
	private String country;
	
	/**
	 * Logitude
	 */
	private double longitude;
	
	/**
	 * Latitude
	 */
	private double latitude;
	
	/**
	 * A string html url to an image on the internet to represet the weather
	 */
	private String imageURL;
	
	/**
	 * Returns a list of key/value pairs needed to be utilized in the weather model.
	 * @return the ArrayList with keys, but no values
	 */
	public static ArrayList<String> getKeys() {
		
		//Local declarations
		ArrayList<String> list = new ArrayList<String>();
		
		//Create map - set value to null
		list.add("kelvin");
		list.add("maxKelvin");
		list.add("minKelvin");
		list.add("date");
		list.add("humidity");
		list.add("precipitation");
		list.add("windSpeedKPH");
		list.add("dayCount");
		list.add("city");
		list.add("state");
		list.add("zip");
		list.add("country");
		list.add("longitude");
		list.add("latitude");
		list.add("imageURL");
		
		//Return the list
		return list;
		
	}
	/**
	 * The nullary Constructor
	 */
	public WeatherModel() {
		
		//Set default values
		this.kelvin = 0;
		this.maxKelvin = 0;
		this.minKelvin = 0;
		this.humidity = 0;
		this.windSpeedKph = 0;
		this.precipitation = 0;
		this.city = "";
		this.state = "";
		this.country = "";
		this.longitude = 0;
		this.latitude = 0;
		this.dayCount = 0;
		this.date = "00/00/0000";
		this.imageURL = null;
	}

	/**
	 * The non nullary constructor. Creates a model based on specific K/V pairs.
	 * If the Key or value is not correct, then it sets it to default
	 * parameters.
	 * 
	 * @param The map.
	 */
	public WeatherModel(HashMap<String, Object> map) {

		//Call nullary constructor to setup defaults
		this();
		
		//Get the list
		ArrayList<String> list = getKeys();
		
		//Set Kelvin
		if(map.get(list.get(0)) != null)
			this.setKelvin((Integer)map.get(list.get(0)));
		
		//Set max Kelvin
		if(map.get(list.get(1)) != null)
			this.setMaxKelvin((Integer)map.get(list.get(1)));
		
		//Set min Kelvin
		if(map.get(list.get(2)) != null)
			this.setMinKelvin((Integer)map.get(list.get(2)));
		
		//set date
		if(map.get(list.get(3)) != null)
			this.setDate((String)map.get(list.get(3)));
		
		//set humidity
		if(map.get(list.get(4)) != null)
			this.setHumidity((Double)map.get(list.get(4)));
		
		//set precipitation
		if(map.get(list.get(5)) != null)
			this.setPrecipitation((Double)map.get(list.get(5)));
		
		//set wind speed kph
		if(map.get(list.get(6)) != null)
			this.setWindSpeedKPH((Double)map.get(list.get(6)));
		
		//set day count
		if(map.get(list.get(7)) != null)
			this.setDayCount((Integer)map.get(list.get(7)));
		
		//set city
		if(map.get(list.get(8)) != null)
			this.setCity((String)map.get(list.get(8)));
		
		//set state
		if(map.get(list.get(9)) != null)
			this.setState((String)map.get(list.get(9)));
		
		//set zip
		if(map.get(list.get(10)) != null)
			this.setZip((Integer)map.get(list.get(10)));
		
		//set country
		if(map.get(list.get(11)) != null)
			this.setCountry((String)map.get(list.get(11)));
		
		//set longitude
		if(map.get(list.get(12)) != null)
			this.setLongitude((Double)map.get(list.get(12)));
		
		//set latitude
		if(map.get(list.get(13)) != null)
			this.setLatitude((Double)map.get(list.get(13)));
		//set imageURL
		if(map.get(list.get(14)) != null)
			this.setImageURL((String)map.get(list.get(14)));
	}

	/**
	 * Sets the image URL.  Can not be set to null
	 * @param imageURL the image url
	 */
	public void setImageURL(String imageURL) {
		
		if(imageURL != null)
			this.imageURL = imageURL;
		
	}
	
	/**
	 * Returns the image url.
	 * @return the url or null
	 */
	public String getImageURL() {
		
		return this.imageURL;
		
	}
	
	/**
	 * Sets the Kelvin
	 * 
	 * @param Kelvin
	 *            the Kelvin to be set.
	 */
	public void setKelvin(int kelvin) {
		this.kelvin = kelvin;
	}

	/**
	 * Returns the Kelvin
	 * 
	 * @return the Kelvin
	 */
	public int getKelvin() {

		return this.kelvin;
	}

	/**
	 * Sets the kelvin Max
	 * 
	 * @param Kelvin
	 *            the max kelvin to be set.
	 */
	public void setMaxKelvin(int kelvin) {
		this.maxKelvin = kelvin;
	}

	/**
	 * Returns the max kelvin
	 * 
	 * @return the max kelvin
	 */
	public int getMaxKelvin() {

		return this.maxKelvin;
	}

	/**
	 * Sets the min kelvin
	 * 
	 * @param Kelvin
	 *            the min kelvin to be set.
	 */
	public void setMinKelvin(int kelvin) {
		
		this.minKelvin = kelvin;
	}

	/**
	 * Returns the min kelvin
	 * 
	 * @return the min kelvin
	 */
	public int getMinKelvin() {

		return this.minKelvin;
	}

	/**
	 * Returns the Fahrenheit
	 * 
	 * @return the Fahrenheit
	 */
	public int getFahrenheit() {

		return (int) (this.getCelsius()*1.8+32.0);
	}

	/**
	 * Returns the max Fahrenheit
	 * 
	 * @return the max Fahrenheit
	 */
	public int getMaxFahrenheit() {

		return (int) (this.getMaxCelsius()*1.8+32.0);
	}

	/**
	 * Returns the min Fahrenheit
	 * 
	 * @return the min Fahrenheit
	 */
	public int getMinFahrenheit() {

		return (int) (this.getMinCelsius()*1.8+32.0);
	}
	
	/**
	 * Returns the celsius
	 * 
	 * @return the celsius
	 */
	public int getCelsius() {

		return this.kelvin - 273;
	}

	/**
	 * Returns the max celsius
	 * 
	 * @return the max celsius
	 */
	public int getMaxCelsius() {

		return this.maxKelvin - 273;
	}

	/**
	 * Returns the min celsius
	 * 
	 * @return the min celsius
	 */
	public int getMinCelsius() {

		return this.minKelvin - 273;
	}

	/**
	 * Sets the date. Must be in MM/DD/YYYY format.
	 * 
	 * @param date
	 *            the date to be set
	 */
	public void setDate(String date) {

		//Check the date
		if(date != null && date.length() == 10 && date.toCharArray()[2] == '/' && date.toCharArray()[5] == '/' ) {
			String firstNumber = date.split("/")[0];
			String secondNumber = date.substring(3, 5);
			String thirdNumber = date.substring(6, 10);
			
			//Setup the numbers
			int firstNumberInt = 0;
			int secondNumberInt = 0;
			int thirdNumberInt = 0;
			
			//Try to convert to numbers
			try  {
				firstNumberInt = Integer.parseInt(firstNumber);
				secondNumberInt = Integer.parseInt(secondNumber);
				thirdNumberInt = Integer.parseInt(thirdNumber);
			} catch (Exception e) {
				return;
			}
			
			//If the date is empty, thats okay
			if("00/00/0000".equals(date)) {
				this.date = date;
			}
			
			if(firstNumberInt >= 1 && firstNumberInt <= 12 
					&& secondNumberInt >= 1 && secondNumberInt <=31 
					&& thirdNumberInt >= 1400 && thirdNumberInt <= 9999 )
				this.date = date;
		}
		
		
		
	}

	/**
	 * Returns the date. Returns in MM/DD/YYYY format.
	 * 
	 * @return the date
	 */
	public String getDate() {

		return this.date;
	}

	/**
	 * Sets the date count. Sets up the order
	 * 
	 * @param dateCount
	 *            the date count to set
	 */
	public void setDayCount(int dayCount) {
		if(dayCount >= 0)
			this.dayCount = dayCount;
	}

	/**
	 * Returns the date count
	 * 
	 * @return the date count
	 */
	public int getDayCount() {
		return this.dayCount;
	}

	/**
	 * Sets the precipitation
	 * 
	 * @param precipitation
	 *            the precipitation to set
	 */
	public void setPrecipitation(double precipitation) {
		
		//Check for proper humidity
		if(precipitation >= 0 && precipitation <= 100)
			this.precipitation = precipitation;
	}

	/**
	 * Returns the precipitation
	 * 
	 * @return the precipitation
	 */
	public double getPrecipitation() {

		return this.precipitation;
	}

	/**
	 * Sets the humidity
	 * 
	 * @param humidity
	 *            to set
	 */
	public void setHumidity(double humidity) {
		
		//Check for proper humidity
		if(humidity >= 0 && humidity <= 100)
			this.humidity = humidity;
	}

	/**
	 * Returns the humidity
	 * 
	 * @return the humidity
	 */
	public double getHumidity() {

		return this.humidity;
	}

	/**
	 * Sets the wind speed.
	 * 
	 * @param windspeed
	 *            to set
	 */
	public void setWindSpeedKPH(double windspeed) {
		if (windspeed >= 0 && windspeed <= 9999)
			this.windSpeedKph = windspeed;
	}

	/**
	 * Gets the wind speed in KPH
	 * 
	 * @return the wind speed in KPH
	 */
	public double getWindSpeedKPH() {

		return this.windSpeedKph;
	}

	/**
	 * Sets the city
	 * 
	 * @param city
	 *            city to set
	 */
	public void setCity(String city) {
		
		//If the city is not null or empty, set city
		if(city != null)
			this.city = city;
	}

	/**
	 * Returns the city
	 * 
	 * @return the city
	 */
	public String getCity() {
		
		return this.city;
	}

	/**
	 * Sets the state
	 * 
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		//If the city is not null or empty, set city
		if(state != null)
			this.state = state;
		
	}

	/**
	 * Returns the state
	 * 
	 * @return the state
	 */
	public String getState() {
		
		return this.state;
	}

	/**
	 * Sets the zip
	 * 
	 * @param zip
	 *            the zip to set
	 */
	public void setZip(int zip) {
		
		if(zip >= 10000 && zip < 100000)
			this.zip = zip;
	}
	
	/**
	 * Returns the zip
	 * @return the zip
	 */
	public int getZip() {

		return this.zip;
	}
	
	/**
	 * Sets the longitude
	 * 
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(double longitude) {
		if(longitude >= -180 && longitude <=180)
			this.longitude = longitude;
	}

	/**
	 * Returns the longitude
	 * 
	 * @return the longitude
	 */
	public double getLongitude() {

		return this.longitude;
	}
	
	/**
	 * Sets the latitude
	 * 
	 * @param longitude
	 *            the latitude to set
	 */
	public void setLatitude(double latitude) {
		if(latitude >= -90 && latitude <=90)
			this.latitude = latitude;
	}

	/**
	 * Returns the latitude
	 * 
	 * @return the latitude
	 */
	public double getLatitude() {

		return this.latitude;
	}

	/**
	 * Sets the country
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		//If the city is not null or empty, set city
		if(country != null)
			this.country = country;
	}

	/**
	 * Returns the country
	 * @return the country
	 */
	public String getCountry() {
		return this.country;
	}

	/**
	 * Checks the equality
	 * 
	 * @param true if equal.  False otherwise
	 */
	public boolean equals(Object model) {
		
		WeatherModel compareTo;
		if(model != null && model instanceof WeatherModel ) {
			compareTo = (WeatherModel) model;
			
			//Check comparisons
			if( this.city.equals(compareTo.city) 
					&& this.country.equals(compareTo.country) 
					&& this.date.equals(compareTo.date)
					&& this.dayCount == compareTo.dayCount
					&& this.kelvin == compareTo.kelvin
					&& this.humidity == compareTo.humidity
					&& this.latitude == compareTo.latitude
					&& this.longitude == compareTo.longitude
					&& this.maxKelvin == compareTo.maxKelvin
					&& this.minKelvin == compareTo.minKelvin
					&& this.precipitation == compareTo.precipitation
					&& this.state.equals(compareTo.state)
					&& this.windSpeedKph == compareTo.windSpeedKph
					&& this.zip == compareTo.zip) 
				return true;
		}
		
		return false;
	}
	
	/**
	 * Performs a deep copy of the weather model.
	 * @param model to be copied from.
	 */
	public void copy(WeatherModel model) {
		
		if(model != null) {
			this.city =  model.city; 
			this.country = model.country;
			this.date = model.date;
			this.dayCount = model.dayCount;
			this.kelvin = model.kelvin;
			this.humidity = model.humidity;
			this.latitude = model.latitude;
			this.longitude = model.longitude;
			this.maxKelvin = model.maxKelvin;
			this.minKelvin = model.minKelvin;
			this.precipitation = model.precipitation;
			this.state = model.state;
			this.windSpeedKph = model.windSpeedKph;
			this.zip = model.zip;
		}
	}

}
