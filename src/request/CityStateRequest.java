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
package request;

import java.util.HashMap;

import core.IRequest;
import core.IRequestVisitor;

/**
 * Represents a type of request given by city and state.
 * Assumes United States of America as country.
 * 
 * @author Forest
 *
 */
public class CityStateRequest implements IRequest {

	/**
	 * The number of days set.  Defaults to 1.
	 */
	 private int days;
	/**
	 * The request encoded into a hashmap.
	 * 
	 */
	 private java.util.HashMap<String, String> request;

	/**
	 * The constructor.  Takes 3 args (city, state and days).
	 * The days must be positive.  Defaults to 1.
	 * The state and city must not be null.  Defaults to empty string.
	 * 
	 * @param the city
	 * @param the state
	 * @param the days
	 */
	public CityStateRequest(String city, String state, int days) {
		
		String key1 = "city";
		String key2 = "state";
		int defaultDays = 1;
		
		//Setup default days
		this.days = defaultDays;
		
		//Instantiate request
		this.request = new HashMap<String, String>();
		
		//Setup defaults for map
		this.request.put(key1, "");
		this.request.put(key2, "");
		
		//If days valid, set
		if(days > 1) {
			this.days = days;
		}
		
		//If key1 valid, set
		if(city != null) {
			this.request.put(key1, city);
		}
		
		//If key2 valid, set
		if(state != null) {
			this.request.put(key2, state);
		}
		
	}

	@Override
	public HashMap<String, String> getRequest() {
		
		return this.request;
	}

	@Override
	public int getNumberOfDays() {
		return this.days;
	}

	@Override
	public void accept(IRequestVisitor visitor) {
		visitor.visit(this);
		
	}
	
	@Override
	public boolean equals(Object object) {
		
		//Local Declarations
		CityStateRequest mrequest;
		
		if(object == null || !(object instanceof CityStateRequest)) {
			return false;
		}
		
		mrequest = (CityStateRequest) object;
		
		if(this.days != mrequest.days || !(this.request.equals(mrequest.request))) {
			return false;
		}
		
		return true;
		
	}

}
