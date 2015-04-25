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
 * Represents a request made by longitude and latitude.
 * 
 * @author Forest
 *
 */
public class LongLatRequest implements IRequest {

	/**
	 * The number of days set.  Defaults to 1.
	 */
	 private int days;
	/**
	 * The request encoded into a hashmap.
	 */
	 private java.util.HashMap<String, String> request;
	
	/**
	 * The constructor
	 * Takes 3 variables.
	 * 
	 * @param Longitude - must be between 180 and -180.  Defaults to 0.
	 * @param Latitude - must be between 90 and -90.  Defaults to 0.
	 * @param Days-  must be greater than zero.  Defaults to 1.
	 */
	public LongLatRequest(double longitude, double latitude, int days) {
		
		
		String key1 = "longitude";
		String key2 = "latitude";
		int defaultDays = 1;
		
		//Setup default days
		this.days = defaultDays;
		
		//Instantiate request
		this.request = new HashMap<String, String>();
		
		//Setup defaults for map
		this.request.put(key1, "0");
		this.request.put(key2, "0");
		
		//If days valid, set
		if(days > 1) {
			this.days = days;
		}
		
		//If key1 valid, set
		if(longitude <= 180 && longitude >=-180) {
			this.request.put(key1, ""+longitude);
		}
		
		//If key2 valid, set
		if(latitude <= 90 && latitude >=-90) {
			this.request.put(key2, ""+latitude);
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
		LongLatRequest mrequest;
		
		if(object == null || !(object instanceof LongLatRequest)) {
			return false;
		}
		
		mrequest = (LongLatRequest) object;
		
		if(this.days != mrequest.days || !(this.request.equals(mrequest.request))) {
			return false;
		}
		
		return true;
		
	}

}
