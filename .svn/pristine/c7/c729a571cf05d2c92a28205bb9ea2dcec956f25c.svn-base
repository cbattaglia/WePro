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

package ui;

import java.util.HashMap;

import javax.swing.JFrame;

import wedapro.WWODataProvider;
import wedapro.Yahoo;
import core.IWeatherDataProvider;

/**
 * This is the main driver class that starts up the application.
 * 
 * @author AbdulRahman, Forest and Bharat.
 * 
 */

public class WeProDriver {
	/**
	 * The main class.
	 * 
	 * @param args
	 *            The main arguments.
	 */
	public static void main(String[] args) {
		
		//Setup my providers first
		HashMap<String, IWeatherDataProvider> providers = setupProviders();
		
		IntroductionWindow w = new IntroductionWindow(providers); // create ListFrame
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.setSize(600, 350); // set frame size
		w.setVisible(true); // display frame
	}
	
	/**
	 * Setup the providers to use to get the data.
	 * In this dynamic HashMap providers, just add any provider that you need and it will
	 * be set up to add to the layout and to the ongoing process.
	 * @return the providers
	 */
	private static HashMap<String, IWeatherDataProvider> setupProviders () {
		
		//First, create the hashmap
		HashMap<String, IWeatherDataProvider> map = new HashMap<String, IWeatherDataProvider>();
		
		//Setup the WWODataProvider.  This is a data providing service that links to 
		//World Weather Online.
		String key = "2snpt864xex9b9c42sbgpm6t";
		
		//Create the providers
		WWODataProvider wwoProvider = new WWODataProvider (key);
		Yahoo yahooProvider = new Yahoo();
		
		//Add the providers to the map
		map.put(wwoProvider.getName(), wwoProvider);
		map.put(yahooProvider.getName(), yahooProvider);
		
		//Return the map
		return map;
	}
}