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

package util;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * This class is designed to convert locations to other types of locations.
 * 
 * @author Forest
 *
 */
public class LocationConversionUtil {
	
	/**
	 * Returns a size two array that contains the city state.
	 * or the empty string if there was an error.
	 * @param zipCode The zip code.  Must be a valid 5 digit.
	 * @return the city state or empty.
	 */
	public static String convertToCityStateFromZipCode(int zipCode) {
		 
		
		//Error check input
		if(zipCode < 10000 || zipCode >= 100000)
			return "";
		
		//Build URL
		String baseURL = "http://maps.googleapis.com/maps/api/geocode/xml?";
		String addressPrefix = "address=";
		String sensor = "&sensor=true";
		Document doc;
		
		//Build URL string
		String urlString = baseURL + addressPrefix + zipCode + sensor;
		
		//Connect to URL
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			doc = db.parse(new URL(urlString).openStream());
		} catch (Exception e) {
			return "";
		}
		
		//Get the city and the state string
		NodeList nodes =  doc.getElementsByTagName("formatted_address");
		
		//If no location found, return empty string.
		if(nodes.getLength() == 0) 
			return "";
		
		String cityStateString = nodes.item(0).getChildNodes().item(0).getNodeValue();
		
		return cityStateString;
		
		
	}
	
	/**
	 * Returns a size two array that contains the city state.
	 * or the empty string if there was an error.
	 * @param longitude the longitude.  Must be valid
	 * @param latitude the latitude. Must be valid.
	 * @return the city state or empty.
	 */
	public static String convertToCityStateFromLongLat(double latitude, double longitude) {
		
		//Error check input
		if(longitude > 180 || longitude <-180 ||latitude > 90 || latitude < -90)
			return "";
		
		//Build URL
		String baseURL = "http://maps.googleapis.com/maps/api/geocode/xml?";
		String addressPrefix = "latlng=";
		String sensor = "&sensor=true";
		Document doc;
		
		//Build URL string
		String urlString = baseURL + addressPrefix + latitude + ","+longitude + sensor;
		
		//Connect to URL
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			doc = db.parse(new URL(urlString).openStream());
		} catch (Exception e) {
			return "";
		}
		
		//Get the city and the state string
		NodeList nodes =  doc.getElementsByTagName("formatted_address");
		
		//If no location found, return empty string.
		if(nodes.getLength() == 0) 
			return "";
		
		String cityStateString = nodes.item(0).getChildNodes().item(0).getNodeValue();
		
		//Parse and add to list
		
		return cityStateString;
		
		
	}
	
	

}
