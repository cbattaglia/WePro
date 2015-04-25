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
package tests.wedapro;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.w3c.dom.Document;

import core.WeatherModel;
import request.CityStateRequest;
import request.LongLatRequest;
import request.ZipCodeRequest;
import wedapro.WWODataProvider;

/**
 * A class that tests the WWODataProvider.
 * 
 * @author Forest
 *
 */
public class WWODataProviderTester {

	/**
	 * Check the construction
	 */
	@Test
	public void checkConstruction() {

		WWODataProvider provider;

		String fakeKey = "333";
		String emptyKey = "";
		String nullKey = null;
		String name = "World Weather Online";

		// Check fake construction
		provider = new WWODataProvider(fakeKey);

		// Check values - fake key is bad. So it won't work.
		assertEquals(name, provider.getName());
		assertEquals(0, provider.getWeather().size());
		assertTrue(provider.canRefresh());
		assertEquals(fakeKey, provider.getKey());

		// Check null construction
		provider = new WWODataProvider(nullKey);

		// Check values - null key is bad. So it won't work.
		assertEquals(name, provider.getName());
		assertEquals(0, provider.getWeather().size());
		assertTrue(provider.canRefresh());
		assertEquals(emptyKey, provider.getKey());

		// Check empty construction
		provider = new WWODataProvider(emptyKey);

		// Check values - empty key is bad. So it won't work.
		assertEquals(name, provider.getName());
		assertEquals(0, provider.getWeather().size());
		assertTrue(provider.canRefresh());
		assertEquals(emptyKey, provider.getKey());

	}

	/**
	 * This makes sure that the operations can be visited. This will not compile
	 * if the following requests listed below are not apart of the visitor
	 * interface
	 */
	@Test
	public void checkVisitation() {

		// Local Declarations
		WWODataProvider provider;
		String fakeKey = "333";

		// Setup request types
		ZipCodeRequest zipRequest = new ZipCodeRequest(12345, 1);
		CityStateRequest csRequest = new CityStateRequest("Atlanta", "Georgia",
				1);
		LongLatRequest llRequest = new LongLatRequest(0, 0, 1);

		// Check fake construction
		provider = new WWODataProvider(fakeKey);

		// Check submit request
		assertTrue(provider.submitRequest(zipRequest));
		assertTrue(provider.submitRequest(csRequest));
		assertTrue(provider.submitRequest(llRequest));

	}

	/**
	 * This test is to see the data being pooled by the WWODataProvider. This
	 * test is to be DISABLED as it should only be utilized For debugging
	 * purposes
	 */
	//@Test
	public void checkConnection() {

		// Local Declarations
		String key = "";
		WWODataProvider provider = new WWODataProvider(key);
		CityStateRequest csRequest = new CityStateRequest("Atlanta", "Georgia",
				3);
		
		//New York City
		LongLatRequest llRequest = new LongLatRequest(40.6700, 73.9400, 2);
		
		//Zipcode
		ZipCodeRequest zipRequest = new ZipCodeRequest(32901, 5);

		// Check to see if you can refresh
		assertTrue(provider.canRefresh());

		// Since we can refresh, pool data
		
		// First submit request
		provider.submitRequest(csRequest);

		// Check out model
		assertEquals(3, provider.getWeather().size());
		
		try {
		    Thread.sleep(5000);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		//Try second request
		provider.submitRequest(llRequest);
		
		//Check out model
		assertEquals(2, provider.getWeather().size());
		
		try {
		    Thread.sleep(5000);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		//Try third request
		provider.submitRequest(zipRequest);
		
		//Check out model
		assertEquals(5, provider.getWeather().size());

	}

	/**
	 * This tests checks the capability of the WWODataProvider's parseXML file.
	 * It should return a list of weather models that correspond to the number
	 * of days requested.
	 * 
	 */
	@Test
	public void checkParseXML() {
		
		//Local Declarations
		WWODataProvider provider = new WWODataProvider("fakeKey");
		ArrayList<WeatherModel> models = new ArrayList<WeatherModel>();
		String path = null;
		String pathFile = null;
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		
		Document doc = null;
		
		//First, we need to give it a file.
		//Get the path:
		try {
			path = new java.io.File(".").getCanonicalPath() + System.getProperty("file.separator") + "testData" + System.getProperty("file.separator");
			pathFile = path+"weatherData.xml";
			System.out.println(path);
			db = dbf.newDocumentBuilder();
			doc = db.parse(new File(pathFile));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
		//Make sure the file exists
		assertNotNull(pathFile);
		
		//Make sure the files exists
		File file = new File(pathFile);
		assertTrue(file.exists());
		
		//Test bad cases first:  Try to pass a file that does not exist
		assertEquals(0, provider.parseXML(null).size());
		//Parse the file - note that there is only one model.
		models = provider.parseXML(doc);
		assertEquals(1, models.size());
		
		//Check data - brief check to verify the mechanism is working.
		assertEquals("11/20/2013", models.get(0).getDate());
		assertEquals(10, models.get(0).getCelsius());
		assertEquals(0.0, models.get(0).getWindSpeedKPH(), 0.0); //Reflects current weather conditions, not the max for the day.
		assertEquals(46.0, models.get(0).getHumidity(), 0.0);
		assertEquals(11, models.get(0).getMaxCelsius());
		assertEquals(7, models.get(0).getMinCelsius());
		
		
	}
}
