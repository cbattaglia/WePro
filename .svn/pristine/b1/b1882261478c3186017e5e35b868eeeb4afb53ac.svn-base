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

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import request.CityStateRequest;

/**
 * A class that tests CityStateRequest
 * 
 * @author Forest
 *
 */
public class CityStateRequestTester {
	
	/**
	 * This operation checks the construction and getters of the class.
	 */
	@Test
	public void checkConstruction() {
		
		//Local Declarations
		CityStateRequest request;
		String city = "Foo" , state = "Bar";
		int days = 3;
		HashMap <String, String> map = new HashMap<String, String>();
		
		//Setup map
		map.put("city", city);
		map.put("state", state);
		
		//Defaults
		String defaultCity = "";
		String defaultState = "";
		int defaultDays = 1;
		int negativeDays = -1;
		int zeroDays = 0;
		HashMap <String, String> defaultMap = new HashMap<String, String>();
		
		//Setup map
		defaultMap.put("city", defaultCity);
		defaultMap.put("state", defaultState);
		
		//Check normal construction
		request = new CityStateRequest(city, state, days);
		assertTrue(map.equals(request.getRequest()));
		assertEquals(days, request.getNumberOfDays());
		
		//Check nullary construction
		request = new CityStateRequest(null, null, days);
		assertTrue(defaultMap.equals(request.getRequest()));
		assertEquals(days, request.getNumberOfDays());
		
		//First null
		request = new CityStateRequest(null, state, days);
		HashMap <String, String> oneNull = new HashMap<String, String>();
		oneNull.put("city", "");
		oneNull.put("state", state);
		assertTrue(oneNull.equals(request.getRequest()));
		assertEquals(days, request.getNumberOfDays());
		
		//Second null
		request = new CityStateRequest(city, null, days);
		oneNull = new HashMap<String, String>();
		oneNull.put("city", city);
		oneNull.put("state", "");
		assertTrue(oneNull.equals(request.getRequest()));
		assertEquals(days, request.getNumberOfDays());
		
		//Check empty construction
		request = new CityStateRequest("", "", days);
		assertTrue(defaultMap.equals(request.getRequest()));
		assertEquals(days, request.getNumberOfDays());
		
		//Check negative days construction
		request = new CityStateRequest(city, state, negativeDays);
		assertTrue(map.equals(request.getRequest()));
		assertEquals(defaultDays, request.getNumberOfDays());
		
		//Check zero days construction
		request = new CityStateRequest(city, state, zeroDays);
		assertTrue(map.equals(request.getRequest()));
		assertEquals(defaultDays, request.getNumberOfDays());
		
	}
	
	/**
	 * Checks the equals operation.
	 */
	@Test
	public void checkEquality() {
		
		//Local Declarations
		CityStateRequest request, equalRequest, transRequest, notEqualRequest;
		String city = "Foo" , state = "Bar";
		int days = 3;
		int wrongDays = 4;
		HashMap <String, String> map = new HashMap<String, String>();
				
		//Setup map
		map.put("city", city);
		map.put("state", state);
		
		//Create requests
		request = new CityStateRequest(city, state, days);
		equalRequest = new CityStateRequest(city, state, days);
		transRequest = new CityStateRequest(city, state, days);
		notEqualRequest = new CityStateRequest(city, state, wrongDays);
		
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
	 * Checks the visitor pattern
	 */
	@Test
	public void checkVisitation() {
		
		CityStateRequest request;
		TestRequestVisitor visitor = new TestRequestVisitor();
		String city = "Foo" , state = "Bar";
		int days = 3;
		
		//Create class
		request = new CityStateRequest(city, state, days);
		
		//visit the class
		request.accept(visitor);
		
		//Make sure it was visited
		assertTrue(visitor.wasVisited());
		
	}
}
