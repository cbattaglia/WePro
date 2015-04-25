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

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import core.IWeatherDataProvider;
import core.WeatherModel;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Dimension;

/**
 * This is Result frame window.  Displays the results of
 * the pooled weather objects made by the request and displays
 * the results on the screen.
 * 
 * @author bharat, Forest and abdulrahman
 *
 */

public class ResultsWindow extends JFrame {
	
	/**
	 * Default serial id.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Generates the content of the weather box
	 * @param model the weather model
	 * @param isFirstDay true if first date, false otherwise
	 * @return the result in a string
	 */
	public String generateTextBox(WeatherModel model, boolean isFirstDay) {
		
		if(model == null) return "";
		String result = "";
		//Generate String based on content provided
		
		result = "" + model.getDate();
		
		if(isFirstDay)
			result += "\n" + "Current C: " + model.getCelsius();
		
		result+= "\n" + "Max C: " + model.getMaxCelsius() + "\n" 
					+	"Min C: " + model.getMinCelsius();
		
		return result;
	}
	
	private JPanel contentPane;
	
	/**
	 * Result 3's construction
	 * @param textField_1
	 * @param textField_2
	 * @param textField_3
	 * @param sss1
	 * @param providers
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public ResultsWindow(String textField_1 ,String textField_2, String textField_3, String sss1, final HashMap<String, IWeatherDataProvider> providers) throws FileNotFoundException, IOException {
		setFont(new Font("Distant Galaxy", Font.PLAIN, 18));
		setIconImage(Toolkit.getDefaultToolkit().getImage("images" + System.getProperty("file.separator")  +"sun_with_transparent_background.png"));
		setTitle("Wepro");
		String str1 = textField_1;
		String str2 = textField_2;
		setBounds(100, 100, 774, 527);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		String location = "Location: ";
		location = location + str1;
		String dt = "Date and Time: ";
		dt = dt + str2;
		JLabel lblDateAndDay = new JLabel(dt);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblDateAndDay, 69, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblDateAndDay, -211, SpringLayout.SOUTH, contentPane);
		lblDateAndDay.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblDateAndDay);
		
		ArrayList<WeatherModel> models = null;
		
		//Try to get the models
		IWeatherDataProvider provider = providers.get(sss1);
		if(provider != null)
			models = provider.getWeather();
		
		/**
		 * Add all GUI's component to the frame
		 */
		JLabel label = new JLabel("");
		sl_contentPane.putConstraint(SpringLayout.NORTH, label, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, label, 74, SpringLayout.WEST, contentPane);
		
		//To add picture of providers
		label.setIcon(new ImageIcon(getClass().getResource("/images/" + sss1.replaceAll(" ", "_") + ".png")));

		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 20, SpringLayout.EAST, label);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNewLabel, 0, SpringLayout.SOUTH, label);
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/images/Untitled-1.png")));
		contentPane.add(lblNewLabel);
		
		JLabel label_1 = new JLabel("");
		sl_contentPane.putConstraint(SpringLayout.NORTH, label_1, 0, SpringLayout.NORTH, label);
		sl_contentPane.putConstraint(SpringLayout.EAST, label_1, -10, SpringLayout.EAST, contentPane);
		
		//Set the logic for the current weather here:
		if(models.size() >=1 && models.get(0).getImageURL()!= null) {
			System.out.println(models.get(0).getImageURL());
			URL url = new URL(models.get(0).getImageURL());
			BufferedImage img = ImageIO.read(url);
			label_1.setIcon(new ImageIcon(img));
		}else {
			//Print a "blank" image.  Just cause.
			label_1.setIcon(new ImageIcon(getClass().getResource("/images/images.jfif")));
		}
		contentPane.add(label_1);
		
		// To add action listener to the lblClickHereTo to go to IntroductionWindow 
		JButton lblClickHereTo = new JButton("Main Menu");
		lblClickHereTo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				IntroductionWindow w = new IntroductionWindow(providers); // create ListFrame
				w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				w.setSize(600,350); // set frame size
				w.setVisible(true); // display frame
				dispose();
			}
		});
		lblClickHereTo.setForeground(Color.GREEN);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblClickHereTo, 29, SpringLayout.SOUTH, label_1);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblClickHereTo, 0, SpringLayout.EAST, label_1);
		lblClickHereTo.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblClickHereTo);
		
		JLabel lblLocation = new JLabel(location);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblLocation, -258, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblDateAndDay, 6, SpringLayout.SOUTH, lblLocation);
		lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 18));
		sl_contentPane.putConstraint(SpringLayout.WEST, lblLocation, 0, SpringLayout.WEST, lblDateAndDay);
		contentPane.add(lblLocation);
		
		JTextArea txtrCurrentWeather = new JTextArea();
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtrCurrentWeather, 265, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtrCurrentWeather, 69, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, txtrCurrentWeather, 78, SpringLayout.SOUTH, lblDateAndDay);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtrCurrentWeather, 296, SpringLayout.WEST, contentPane);
		contentPane.add(txtrCurrentWeather);
		if(models!= null && models.size() >= 1)
			txtrCurrentWeather.setText(generateTextBox(models.get(0), true));
		txtrCurrentWeather.setEditable(false);
		
		JTextArea txtrDay = new JTextArea();
		sl_contentPane.putConstraint(SpringLayout.WEST, txtrDay, 71, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, txtrDay, -15, SpringLayout.SOUTH, contentPane);
		if(models!= null&& models.size() >= 2)
			txtrDay.setText(generateTextBox(models.get(1), false));
		contentPane.add(txtrDay);
		txtrDay.setEditable(false);
		
		JTextArea txtDay = new JTextArea();
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtrDay, 0, SpringLayout.NORTH, txtDay);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtrDay, -26, SpringLayout.WEST, txtDay);
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtDay, 26, SpringLayout.SOUTH, txtrCurrentWeather);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtDay, 289, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, txtDay, -10, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtDay, -265, SpringLayout.EAST, contentPane);
		txtDay.setMinimumSize(new Dimension(4, 22));
		contentPane.add(txtDay);
		if(models!= null && models.size() >= 3)
			txtDay.setText(generateTextBox(models.get(2), false));
		txtDay.setEditable(false);
		
		JTextArea txtDay_1 = new JTextArea();
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtDay_1, -104, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtDay_1, -226, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, txtDay_1, 0, SpringLayout.SOUTH, txtDay);
		txtDay_1.setPreferredSize(new Dimension(4, 22));
		sl_contentPane.putConstraint(SpringLayout.EAST, txtDay_1, -34, SpringLayout.EAST, contentPane);
		txtDay_1.setMinimumSize(new Dimension(4, 22));
		contentPane.add(txtDay_1);
		if(models!= null&& models.size() >= 4)
			txtDay_1.setText(generateTextBox(models.get(3), false));
		txtDay_1.setEditable(false);
	
	}
}


