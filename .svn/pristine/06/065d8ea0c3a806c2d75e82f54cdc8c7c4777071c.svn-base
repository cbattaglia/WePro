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
package tests.util;

import static org.junit.Assert.*;
import org.junit.Test;
import util.LocationConversionUtil;

/**
 * Tests the LocationConversionUtil's operations.
 * 
 * @author Forest
 *
 */
public class LocationConversionUtilTester {
	
	/**
	 * Test the zip code conversion to city state.
	 */
	@Test
	public void checkZipCodeToCityStateConversion() {
		
		//Local Declarations
		String cityState = "Nashville, TN 37221, USA";
		int zipCode = 37221;
		
		//Try it with an invalid zip code - min
		assertEquals("", LocationConversionUtil.convertToCityStateFromZipCode(9999));
		
		//Try it with an invalid zip code - max
		assertEquals("", LocationConversionUtil.convertToCityStateFromZipCode(100000));
		
		//Run the operation to convert a zip code to a city state
		assertEquals(cityState, LocationConversionUtil.convertToCityStateFromZipCode(zipCode));
		

		
	}
	
	/**
	 * Tests the longitude and latitude to city state.
	 */
	@Test
	public void checkLongLatToCityStateConversion() {
		
		//Local Declarations
		String cityState = "277 Bedford Avenue, Brooklyn, NY 11211, USA";
		double latitude = 40.714224;
		double longitude = -73.961452;
		
		//Try it with an invalid zip code - min
		assertEquals("", LocationConversionUtil.convertToCityStateFromLongLat(-90.1, -180.1));
		
		//Try it with an invalid zip code - max
		assertEquals("", LocationConversionUtil.convertToCityStateFromLongLat(90.1, 180.1));
		
		//Run the operation to convert a zip code to a city state
		assertEquals(cityState, LocationConversionUtil.convertToCityStateFromLongLat(latitude, longitude));
		

		
	}
}
