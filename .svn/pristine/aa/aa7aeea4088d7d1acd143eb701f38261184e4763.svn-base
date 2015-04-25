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

package tests.request;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;

import request.LongLatRequest;

/**
 * Checks the LongLatRequest class.
 * 
 * @author Forest
 *
 */
public class LongLatRequestTester {
	
	/**
	 * Checks the construction of the class specified.
	 */
	@Test
	public void checkConstruction() {
		
		//Local Declaration
		LongLatRequest request;
		double latitudeMin = -90;
		double latitudeMax = 90;
		double longitudeMin = -180;
		double longitudeMax = 180;
		
		double normalLat = 2;
		double normalLong = 3;
		
		//Number of days
		int days = 3;
		int negativeDays = -1;
		int zeroDays = 0;
		
		//Defaults - when errors occur on constructor
		int defaultDays = 1;
		int defaultLongLat = 0;
		
		//Create hashMap to use later
		String longitudeString = "longitude";
		String latString = "latitude";
		HashMap<String, String> map = new HashMap<String, String>();
		map.put(longitudeString, ""+normalLong);
		map.put(latString, ""+normalLat);
		
		//Check normal construction
		request = new LongLatRequest(normalLong, normalLat, days);
		//Check values
		assertEquals(days, request.getNumberOfDays());
		assertTrue(map.equals(request.getRequest()));
		
		//Check normal construction - min
		request = new LongLatRequest(longitudeMin, latitudeMin, days);
		map.put(longitudeString, ""+longitudeMin);
		map.put(latString, ""+latitudeMin);
		//Check values
		assertEquals(days, request.getNumberOfDays());
		assertTrue(map.equals(request.getRequest()));
		
		//Check normal construction - max
		request = new LongLatRequest(longitudeMax, latitudeMax, days);
		map.put(longitudeString, ""+longitudeMax);
		map.put(latString, ""+latitudeMax);
		//Check values
		assertEquals(days, request.getNumberOfDays());
		assertTrue(map.equals(request.getRequest()));
		
		//Check normal construction - min -1
		request = new LongLatRequest(longitudeMin-1, latitudeMin-1, days);
		map.put(longitudeString, ""+defaultLongLat);
		map.put(latString, ""+defaultLongLat);
		//Check values
		assertEquals(days, request.getNumberOfDays());
		assertTrue(map.equals(request.getRequest()));
		
		//Check normal construction - max+1
		request = new LongLatRequest(longitudeMax+1, latitudeMax+1, days);
		map.put(longitudeString, ""+defaultLongLat);
		map.put(latString, ""+defaultLongLat);
		//Check values
		assertEquals(days, request.getNumberOfDays());
		assertTrue(map.equals(request.getRequest()));
		
		//Check negative days
		request = new LongLatRequest(normalLong, normalLat, negativeDays);
		map.put(longitudeString, ""+normalLong);
		map.put(latString, ""+normalLat);
		//Check values
		assertEquals(defaultDays, request.getNumberOfDays());
		assertTrue(map.equals(request.getRequest()));
		
		//Check zero days
		request = new LongLatRequest(normalLong, normalLat, zeroDays);
		map.put(longitudeString, ""+normalLong);
		map.put(latString, ""+normalLat);
		//Check values
		assertEquals(defaultDays, request.getNumberOfDays());
		assertTrue(map.equals(request.getRequest()));
		
	}
	
	/**
	 * Checks the equality operation.
	 */
	@Test
	public void checkEquality() {
		
		//Local Declarations
		LongLatRequest request, equalRequest, transRequest, notEqualRequest;
		double normalLong =2;
		double normalLat = 3;
		int days = 3;
		int wrongDays = 4;
		
		//Create requests
		request = new LongLatRequest(normalLong, normalLat, days);
		equalRequest = new LongLatRequest(normalLong, normalLat, days);
		transRequest = new LongLatRequest(normalLong, normalLat, days);
		notEqualRequest = new LongLatRequest(normalLong, normalLat, wrongDays);
		
		//Check null
		assertFalse(request.equals(null));
		
		//Reflexive
		assertTrue(request.equals(request));
		
		//Symmetric
		assertTrue(request.equals(equalRequest));
		assertTrue(equalRequest.equals(request));
		
		//Transitive
		assertTrue(request.equals(equalRequest));
		assertTrue(equalRequest.equals(transRequest));
		assertTrue(request.equals(transRequest));
		
		//NotEqual - Reflexive
		assertFalse(request.equals(notEqualRequest));
		
		//NotEqual - Symmetric
		assertFalse(request.equals(notEqualRequest));
		assertFalse(notEqualRequest.equals(request));
		
	}
	
	/**
	 * Checks the visitation.
	 */
	@Test
	public void checkVisitation() {
		
		LongLatRequest request;
		TestRequestVisitor visitor = new TestRequestVisitor();
		double normalLong =2;
		double normalLat = 3;
		int days = 3;
		
		//Create class
		request = new LongLatRequest(normalLong, normalLat, days);
		
		//visit the class
		request.accept(visitor);
		
		//Make sure it was visited
		assertTrue(visitor.wasVisited());
		
	}
}
