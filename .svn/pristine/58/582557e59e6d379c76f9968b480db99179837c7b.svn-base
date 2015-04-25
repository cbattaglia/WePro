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
import javax.swing.JFrame;

import core.IWeatherDataProvider;
import core.IRequest;
import request.*;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JButton;

import request.CityStateRequest;
import request.ZipCodeRequest;
import util.LocationConversionUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

/**
 * This is second window (LocationRequestWindow) which is input window to enter all needed data.
 * @author AbdulRahman, Forest and Bharat
 *
 */
public class LocationRequestWindow extends JFrame {
	/**
	 * The default serial ID 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtEnterUsCity; // to enter city
	private JTextField txtEnterUsState; // to enter state
	private JTextField txtEnterdigitZip; // to enter zipcode
	private JTextField txtEnterLatitude; // to enter latitude
	private JTextField txtEnterLongitude; // to enter longitude
	
	/**
	 * LocationRequestWindow's construction.
	 * @param sss1
	 * @param sss2
	 * @param providers
	 */
	public LocationRequestWindow(final String sss1, final String sss2, final HashMap<String,IWeatherDataProvider> providers) {
		setForeground(Color.CYAN); // set frame foreground color
		//setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\bhara_000\\Desktop\\Software Engineering\\sun_with_transparent_background.png"));
		setFont(new Font("Harrington", Font.BOLD | Font.ITALIC, 33)); // set frame font
		setTitle("WePro");  // set frame title
		 /**
		  * Create all GUI components.
		  */
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/images/Untitled-1.png"))); // creating label for image
		
		JLabel lblNewLabel_1 = new JLabel("");
		// showing the picture of DataProviders.
		//
		JLabel lblNewLabel_2 = new JLabel("");
		
		/**
		 * sss1 has different data providers
		 * Here, we make it more flexible in one sentence to handle all these images and replace , with _.
		 */
		
		lblNewLabel_2.setIcon(new ImageIcon(getClass().getResource("/images/"+sss1.replaceAll(" ", "_") + ".png")));
		
		JLabel lblAreaLocationInformation = new JLabel("Area Location Information :");
		lblAreaLocationInformation.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblNewLabel_3 = new JLabel("City:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));  // font format
		
		txtEnterUsCity = new JTextField();
		txtEnterUsCity.setText("Enter US city only.");
		txtEnterUsCity.setColumns(10);  // to set how many character you can add.
		
		JLabel lblState = new JLabel("State:");
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		txtEnterUsState = new JTextField();
		txtEnterUsState.setText("Enter US State only.");
		txtEnterUsState.setColumns(10);
		
		JLabel lblZipcode = new JLabel("Zipcode:");
		lblZipcode.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		txtEnterdigitZip = new JTextField();
		txtEnterdigitZip.setText("Enter 5-Digit Zip."); // the limit of the zip code 
		txtEnterdigitZip.setColumns(10);
		
			
		JLabel lblLatitude = new JLabel("Latitude:");
		lblLatitude.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		txtEnterLatitude = new JTextField();
		txtEnterLatitude.setText("Enter Latitude");
		txtEnterLatitude.setColumns(10);
		
		
		JLabel lblLongitutde = new JLabel("Longitutde:");
		lblLongitutde.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		txtEnterLongitude = new JTextField();
		txtEnterLongitude.setText("Enter Longitude");
		txtEnterLongitude.setColumns(10);
		/**
		 * Here, we add some action listener and make some of GUI's component invisible 
		 * and set text to some text fields.
		 */
		if (sss2 == "Location: City & State") {
			lblLongitutde.setVisible(false);
			lblLatitude.setVisible(false);
			lblZipcode.setVisible(false);
			txtEnterLongitude.setVisible(false);
			txtEnterLatitude.setVisible(false);
			txtEnterdigitZip.setVisible(false);
			txtEnterUsCity.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent arg0) {
					txtEnterUsCity.setText("");
				}
			});
			txtEnterUsState.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent arg0) {
					txtEnterUsState.setText("");
				}
			});

		} else if (sss2 == "Latitude And Longitude") {
			lblNewLabel_3.setVisible(false);
			lblState.setVisible(false);
			lblZipcode.setVisible(false);
			txtEnterUsCity.setVisible(false);
			txtEnterUsState.setVisible(false);
			txtEnterdigitZip.setVisible(false);
			txtEnterLatitude.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent arg0) {
					txtEnterLatitude.setText("");
				}
			});
			txtEnterLongitude.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent arg0) {
					txtEnterLongitude.setText("");
				}
			});
		}  else if (sss2 == "Location: Zipcode") {
			lblNewLabel_3.setVisible(false);
			lblState.setVisible(false);
			lblLongitutde.setVisible(false);
			lblLatitude.setVisible(false);
			txtEnterUsCity.setVisible(false);
			txtEnterUsState.setVisible(false);
			txtEnterLongitude.setVisible(false);
			txtEnterLatitude.setVisible(false);
			txtEnterdigitZip.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent arg0) {
					txtEnterdigitZip.setText("");
				}
			});
		}
		/**
		 * To pass data to the Window3 (ResultWindow) and to add action listener to the btnGenerateWeather
		 */
		JButton btnGenerateWeather = new JButton("Generate Weather");
		btnGenerateWeather.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				IRequest request; // to get the data from the providers
				
				String Location = null;
				/**
				 * to show up the date and time in format (yyyy/MM/dd HH:mm:ss)
				 */
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				if (sss2 == "Location: City & State"){ 
					 // location means city and state
						Location = txtEnterUsCity.getText();
						Location = Location + " " + txtEnterUsState.getText();
						
						//Create a request
						request = new CityStateRequest(txtEnterUsCity.getText(), txtEnterUsState.getText(), 5);
						
						//Submit request
						IWeatherDataProvider provider = providers.get(sss1);
						if(provider != null)
							provider.submitRequest(request);
						
					
				}
				else if (sss2 == "Latitude And Longitude"){

					/**
					 * Check to see if the text entered is a 5 digit number
					 * initial values for longitude and latitude (0.0, 0.0)
					 */
					
					double longitude=0.0;
					double latitude=0.0;
					try {
						longitude = Double.parseDouble(txtEnterLongitude.getText());
						latitude = Double.parseDouble(txtEnterLatitude.getText());
					} catch (Exception e) {
						//Invalid zip code.
						JOptionPane.showMessageDialog(null, "Error: Longitude or Latitude not correct. Defaulting to 0.0,0.0", "WePro Error Message" , JOptionPane.WARNING_MESSAGE, null);
						//Change the text to something valid
						txtEnterLatitude.setText("0.0");
						txtEnterLongitude.setText("0.0");
						
					}
					
					//If the length of the numbers is not 5
					if(latitude < -90 || latitude > 90 || longitude < -180 || longitude > 180 ) {
						JOptionPane.showMessageDialog(null, "Error: Longitude or Latitude not correct. Defaulting to 0.0,0.0", "WePro Error Message" , JOptionPane.WARNING_MESSAGE, null);
						//Change the text to something valid
						txtEnterLatitude.setText("0.0");
						txtEnterLongitude.setText("0.0");
					}
					
					//Set the location
					String loc =  LocationConversionUtil.convertToCityStateFromLongLat(latitude, longitude);
					if (!loc.equals("")) 
						Location = loc;
					else
						Location = "Coordinates: " + latitude + ", " + longitude;
					
					//Create a request
					request = new LongLatRequest(longitude, latitude, 5);
					
					//Submit request
					IWeatherDataProvider provider = providers.get(sss1);
					if(provider != null)
						provider.submitRequest(request);
					
				}
				else if (sss2 == "Location: Zipcode"){
					
					//Check to see if the text entered is a 5 digit number
					int zipCode = 11111;
					try {
						zipCode = Integer.parseInt(txtEnterdigitZip.getText());
					} catch (Exception e) {
						//Invalid zip code.
						JOptionPane.showMessageDialog(null, "Error:  Zip code not correct. Defaulting to 11111", "WePro Error Message" , JOptionPane.WARNING_MESSAGE, null);
						//Change the text to something valid
						txtEnterdigitZip.setText("11111");
					}
					
					//If the length of the numbers is not 5
					if(txtEnterdigitZip.getText().length() != 5) {
						JOptionPane.showMessageDialog(null, "Error:  Zip code not correct. Defaulting to 11111", "WePro Error Message" , JOptionPane.WARNING_MESSAGE, null);
						//Change the text to something valid
						txtEnterdigitZip.setText("11111");
					}
					
					
					//Set the location
					String loc = LocationConversionUtil.convertToCityStateFromZipCode(zipCode);
					
					if (!loc.equals("")) 
						Location = loc;
					else
						Location = "Zipcode: " + zipCode;
					
					//Create a request
					request = new ZipCodeRequest(zipCode, 5);
					
					//Submit request
					IWeatherDataProvider provider = providers.get(sss1);
					if(provider != null)
						provider.submitRequest(request);
					
					
				}
				/*
				LongLatRequest request = LongLatRequest(double longitude, double latitude, int days)
				
				* HashMap<String, IWeatherDataProvider> 
				*
				*
				*/
				ResultsWindow w = null;
				try {
					w = new ResultsWindow(Location , (dateFormat.format(cal.getTime())), "Wepro", sss1, providers);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
				w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				w.setSize(740,520); // set frame size
				w.setVisible(true); // display frame
				dispose();// to close first window.
			}
			
		});
		/**
		 * To add all GUI's component to the layout and to set the layout
		 */
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(57)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(51))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(93, Short.MAX_VALUE)
					.addComponent(lblAreaLocationInformation)
					.addGap(217))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(53, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_3)
								.addComponent(lblState)
								.addComponent(lblZipcode)
								.addComponent(lblLatitude)
								.addComponent(lblLongitutde))
							.addGap(136))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(42)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(txtEnterUsState, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtEnterUsCity, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
							.addComponent(txtEnterdigitZip, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
							.addComponent(txtEnterLatitude))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtEnterLongitude, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(151, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(145)
					.addComponent(btnGenerateWeather)
					.addContainerGap(273, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(56)
							.addComponent(lblNewLabel_1))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_2))))
					.addGap(10)
					.addComponent(lblAreaLocationInformation)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_3)
								.addComponent(txtEnterUsCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtEnterUsState, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblState))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtEnterdigitZip, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblZipcode)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(74)))
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblLatitude)
						.addComponent(txtEnterLatitude, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLongitutde)
						.addComponent(txtEnterLongitude, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnGenerateWeather)
					.addContainerGap(79, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
	}
}
