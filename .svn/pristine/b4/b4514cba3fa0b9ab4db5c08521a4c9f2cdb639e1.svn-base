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
package core;

import request.CityStateRequest;
import request.LongLatRequest;
import request.ZipCodeRequest;

/**
 * 
 * An interface designed to visit
 * the visitable pieces that implement
 * IRequest.
 * 
 * @author Forest
 *
 */
public interface IRequestVisitor {

	/**
	 * Accept's CityStateRequests.
	 * 
	 * @param the request
	 */
	 void visit(CityStateRequest e);
	 

	/**
	 * Accept's ZipCodeRequests.
	 * 
	 * @param the request
	 */
	 void visit(ZipCodeRequest e);
		 

	/**
	 * Accept's LongLatRequests.
	 * 
	 * @param the request
	 */
	 void visit(LongLatRequest e);

}
