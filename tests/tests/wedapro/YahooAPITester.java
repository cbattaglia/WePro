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

import org.junit.Test;

import request.CityStateRequest;
import request.LongLatRequest;
import request.ZipCodeRequest;
import wedapro.Yahoo;

/**
 * A class that tests the Yahoo data provider.
 * 
 * @author Christian
 *
 */
public class YahooAPITester {

	@Test
	public void checkConstruct() {
		Yahoo yahoo = new Yahoo();

		String name = "Yahoo";

		// test for empty location
		assertEquals(name, yahoo.getName());
		assertTrue(yahoo.canRefresh());

		// test for correct info
		assertEquals(name, yahoo.getName());
		assertTrue(yahoo.canRefresh());

		// What does it return if you call getWeather?
		assertEquals(0, yahoo.getWeather().size());

	}

	@Test
	public void checkVisit() {
		// Local Declarations
		Yahoo yahoo = new Yahoo();

		// Setup request types
		ZipCodeRequest zipRequest = new ZipCodeRequest(12345, 1);
		CityStateRequest csRequest = new CityStateRequest("Atlanta", "Georgia",
				1);
		LongLatRequest llRequest = new LongLatRequest(0, 0, 1);

		// Check submit request
		assertTrue(yahoo.submitRequest(zipRequest));
		assertTrue(yahoo.submitRequest(csRequest));
		assertTrue(yahoo.submitRequest(llRequest));

	}

	@Test
	public void checkConnection() {

		// Local Declarations
		Yahoo provider = new Yahoo();
		CityStateRequest csRequest = new CityStateRequest("Atlanta", "Georgia",
				3);

		// New York City
	    //LongLatRequest llRequest = new LongLatRequest(73.9400, 40.6700, 2);
	    LongLatRequest llRequest = new LongLatRequest(-73.9400, 40.6700, 2);
		//LongLatRequest llRequest = new LongLatRequest(-37.87, 145.1, 2);
		//LongLatRequest llRequest = new LongLatRequest(-80.67, 28, 2);
		//LongLatRequest llRequest = new LongLatRequest(82.7, 26.8, 2);
		// Zipcode
		ZipCodeRequest zipRequest = new ZipCodeRequest(32901, 5);

		// Check to see if you can refresh
		assertTrue(provider.canRefresh());

		// Since we can refresh, pool data

		// First submit request
		provider.submitRequest(csRequest);

		// Check out model
		assertEquals(3, provider.getWeather().size());

		// Try second request
		provider.submitRequest(llRequest);

		// Check out model
		assertEquals(2, provider.getWeather().size());

		// Try third request
		provider.submitRequest(zipRequest);

		// Check out model
		assertEquals(5, provider.getWeather().size());

	}
}