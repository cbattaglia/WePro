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

import java.util.ArrayList;

/**
 * An interface that handles the interactions between the UI and the model.
 * Should be implemented by classes providing weather data.
 * 
 * @author Forest
 * 
 */
public interface IWeatherDataProvider {

	/**
	 * Takes a request. Stores it on the class for later, and repeated, use.
	 * Returns true if the request was valid. False otherwise.
	 * 
	 * @param the request
	 * @return true if reuqest was successful. 
	 */
	boolean submitRequest(IRequest request);

	/**
	 * Returns the weather. The request must be submitted prior to making a
	 * request. Returns a list of model objects if successful, false otherwise.
	 * 
	 * @return the list of models
	 */
	ArrayList<WeatherModel> getWeather();

	/**
	 * Notifies if the weather provider can make another request. True if it is
	 * possible, false otherwise.
	 * 
	 * @param true if it can refresh. False otherwise
	 */
	boolean canRefresh();

	/**
	 * Returns the name of the provider
	 * 
	 * @param the name.
	 */
	String getName();

}
