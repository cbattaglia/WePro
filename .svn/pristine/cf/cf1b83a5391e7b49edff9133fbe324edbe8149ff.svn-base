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
 * Implementation of the zip code request.
 * 
 * @author Forest
 *
 */
public class ZipCodeRequest implements IRequest {

	/**
	 * The number of days set.  Defaults to 1.
	 */
	 private int days;
	/**
	 * The request encoded into a hashmap.
	 */
	 private java.util.HashMap<String, String> request;
	
	/**
	 * The constructor.  Takes 2 args (zip and days).
	 * The days must be positive.  Defaults to 1.
	 * The zip must be valid range: (9999, 100000) or (99999999, 1000000000.  Defaults to 10000.
	 * 
	 * @param the zip
	 * @param the days
	 */
	public ZipCodeRequest(int zip, int days) {
		
		String key1 = "zip";
		int defaultDays = 1;
		
		//Setup default days
		this.days = defaultDays;
		
		//Instantiate request
		this.request = new HashMap<String, String>();
		
		//Setup defaults for map
		this.request.put(key1, "10000");
		
		//If days valid, set
		if(days > 1) {
			this.days = days;
		}
		
		//If key1 valid, set
		if(zip > 10000 && zip < 100000) {
			this.request.put(key1, ""+zip);
		}
		
		//Takes care of extra portion of zip.
		if(zip > 99999999 && zip < 1000000000) {
			String prefix = Integer.toString(zip).substring(0, 4);
			String postfix = Integer.toString(zip).substring(5);
			this.request.put(key1, ""+prefix+"-"+postfix);
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
	
	public boolean equals(Object object) {
		
		//Local Declarations
		ZipCodeRequest mrequest;
		
		if(object == null || !(object instanceof ZipCodeRequest)) {
			return false;
		}
		
		mrequest = (ZipCodeRequest) object;
		
		if(this.days != mrequest.days || !(this.request.equals(mrequest.request))) {
			return false;
		}
		
		return true;
		
	}

}
