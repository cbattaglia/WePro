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

import request.ZipCodeRequest;

/**
 * Tests the operations on ZipCodeRequest class.
 * 
 * @author Forest
 *
 */
public class ZipcodeRequestTester {
	
	/**
	 * Checks the construction of the class specified.
	 * This checks just for 5 digits.
	 */
	@Test
	public void checkConstruction5Digit() {
		
		//Local Declaration
		ZipCodeRequest request;
		int zipCodeMin = 10000;
		int zipCodeMax = 99999;
		int zipCodeMid = 55555;
		
		//Number of days
		int days = 3;
		int negativeDays = -1;
		int zeroDays = 0;
		
		//Defaults - when errors occur on constructor
		int defaultDays = 1;
		int defaultZip = zipCodeMin;
		
		//Create hashMap to use later
		String zipCodeString = "zip";
		String value = "";
		HashMap<String, String> map = new HashMap<String, String>();
		map.put(zipCodeString, value);
		
		//Check normal construction
		request = new ZipCodeRequest(zipCodeMid, days);
		map.put(zipCodeString, ""+zipCodeMid);
		//Check values
		assertEquals(days, request.getNumberOfDays());
		assertTrue(map.equals(request.getRequest()));
		
		//Check normal construction - min
		request = new ZipCodeRequest(zipCodeMin, days);
		map.put(zipCodeString, ""+zipCodeMin);
		//Check values
		assertEquals(days, request.getNumberOfDays());
		assertTrue(map.equals(request.getRequest()));
		
		//Check normal construction - max
		request = new ZipCodeRequest(zipCodeMax, days);
		map.put(zipCodeString, ""+zipCodeMax);
		//Check values
		assertEquals(days, request.getNumberOfDays());
		assertTrue(map.equals(request.getRequest()));
		
		//Check normal construction - min -1
		request = new ZipCodeRequest(zipCodeMin-1, days);
		map.put(zipCodeString, ""+defaultZip);
		//Check values
		assertEquals(days, request.getNumberOfDays());
		assertTrue(map.equals(request.getRequest()));
		
		//Check normal construction - max+1
		request = new ZipCodeRequest(zipCodeMax+1, days);
		map.put(zipCodeString, ""+defaultZip);
		//Check values
		assertEquals(days, request.getNumberOfDays());
		assertTrue(map.equals(request.getRequest()));
		
		//Check negative days
		request = new ZipCodeRequest(zipCodeMid, negativeDays);
		map.put(zipCodeString, ""+zipCodeMid);
		//Check values
		assertEquals(defaultDays, request.getNumberOfDays());
		assertTrue(map.equals(request.getRequest()));
		
		
		//Check zero days
		request = new ZipCodeRequest(zipCodeMid, zeroDays);
		map.put(zipCodeString, ""+zipCodeMid);
		//Check values
		assertEquals(defaultDays, request.getNumberOfDays());
		assertTrue(map.equals(request.getRequest()));
		
	}
	
	/**
	 * Checks the construction of the class specified.
	 * This checks just for 9 digits.
	 */
	@Test
	public void checkConstruction9Digit() {
		
		//Local Declaration
		ZipCodeRequest request;
		int zipCodeMin = 100000000;
		int zipCodeMax = 999999999;
		int zipCodeMid = 555555555;
		
		//Number of days
		int days = 3;
		int negativeDays = -1;
		int zeroDays = 0;
		
		//Defaults - when errors occur on constructor
		int defaultDays = 1;
		int defaultZip = 10000;
		
		//Create hashMap to use later
		String zipCodeString = "zip";
		String value = "";
		HashMap<String, String> map = new HashMap<String, String>();
		map.put(zipCodeString, value);
		
		//Check normal construction
		request = new ZipCodeRequest(zipCodeMid, days);
		map.put(zipCodeString, ""+Integer.toString(zipCodeMid).substring(0, 4) + "-" + Integer.toString(zipCodeMid).substring(5));
		//Check values
		assertEquals(days, request.getNumberOfDays());
		assertTrue(map.equals(request.getRequest()));
		
		//Check normal construction - min
		request = new ZipCodeRequest(zipCodeMin, days);
		map.put(zipCodeString, ""+""+Integer.toString(zipCodeMin).substring(0, 4) + "-" + Integer.toString(zipCodeMin).substring(5));
		//Check values
		assertEquals(days, request.getNumberOfDays());
		assertTrue(map.equals(request.getRequest()));
		
		//Check normal construction - max
		request = new ZipCodeRequest(zipCodeMax, days);
		map.put(zipCodeString, ""+""+Integer.toString(zipCodeMax).substring(0, 4) + "-" + Integer.toString(zipCodeMax).substring(5));
		//Check values
		assertEquals(days, request.getNumberOfDays());
		assertTrue(map.equals(request.getRequest()));
		
		//Check normal construction - min -1
		request = new ZipCodeRequest(zipCodeMin-1, days);
		map.put(zipCodeString, ""+defaultZip);
		//Check values
		assertEquals(days, request.getNumberOfDays());
		assertTrue(map.equals(request.getRequest()));
		
		//Check normal construction - max+1
		request = new ZipCodeRequest(zipCodeMax+1, days);
		map.put(zipCodeString, ""+defaultZip);
		//Check values
		assertEquals(days, request.getNumberOfDays());
		assertTrue(map.equals(request.getRequest()));
		
		//Check negative days
		request = new ZipCodeRequest(zipCodeMid, negativeDays);
		map.put(zipCodeString, ""+Integer.toString(zipCodeMid).substring(0, 4) + "-" + Integer.toString(zipCodeMid).substring(5));
		//Check values
		assertEquals(defaultDays, request.getNumberOfDays());
		assertTrue(map.equals(request.getRequest()));
		
		
		//Check zero days
		request = new ZipCodeRequest(zipCodeMid, zeroDays);
		map.put(zipCodeString, ""+Integer.toString(zipCodeMid).substring(0, 4) + "-" + Integer.toString(zipCodeMid).substring(5));
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
		ZipCodeRequest request, equalRequest, transRequest, notEqualRequest;
		int zipCode =33333;
		int days = 3;
		int wrongDays = 4;
		HashMap <String, String> map = new HashMap<String, String>();
				
		//Setup map
		map.put("zip", ""+zipCode);
		
		//Create requests
		request = new ZipCodeRequest(zipCode, days);
		equalRequest = new ZipCodeRequest(zipCode, days);
		transRequest = new ZipCodeRequest(zipCode, days);
		notEqualRequest = new ZipCodeRequest(zipCode, wrongDays);
		
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
		
		ZipCodeRequest request;
		TestRequestVisitor visitor = new TestRequestVisitor();
		int zipCode =33333;
		int days = 3;
		
		//Create class
		request = new ZipCodeRequest(zipCode, days);
		
		//visit the class
		request.accept(visitor);
		
		//Make sure it was visited
		assertTrue(visitor.wasVisited());
		
	}
}
