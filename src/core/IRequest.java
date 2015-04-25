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

/**
 * The IRequest interface. The classes that implement this interface allow the
 * use of WeatherDataProviders to assess the multiple types of requests required
 * by the user.
 * 
 * @author Forest
 */
public interface IRequest {

	/**
	 * Returns the request in a key/value format. This will change depending on
	 * the implementation of the subclass.
	 * 
	 * @param the request
	 */
	public java.util.HashMap<String, String> getRequest();

	/**
	 * Returns the number of days for the weather sent by the request.
	 * 
	 * @param the number of days
	 */
	public int getNumberOfDays();

	/**
	 * Utilizing the visitor interface to allow visitations of visitors.
	 * 
	 * @param the visitor
	 */
	void accept(IRequestVisitor visitor);

	/**
	 * Returns true if the objects are equal. False otherwise.
	 * 
	 * @param otherObject
	 * @return true if equal. False otherwise.
	 */
	boolean equals(Object otherObject);

}
