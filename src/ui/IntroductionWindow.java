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

import javax.swing.JLabel;

import javax.swing.AbstractButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import java.awt.Toolkit;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * This is the main first UI window to show up
 * and to allow users to choose between tow different inputs and three different providers.
 * 
 * @author AbdulRahman, Forest and Bharat
 *
 */
public class IntroductionWindow extends JFrame {
	/**
	 * Default serial ID.
	 */
	private static final long serialVersionUID = 1L;
	private final ButtonGroup buttonGroup = new ButtonGroup(); // Group list to include all radio button{Google, Yahoo, Bing}
	private final ButtonGroup buttonGroup_1 = new ButtonGroup(); // Group list to include all radio button {Location, LongandLat}
	String Option = "Location: City & State" , service_Ptovider = null;
	/**
	 * Returns the selected button into a text given the button group.
	 * @param buttonGroup
	 * @return one of the RadioButton
	 */
	public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
	
	/**
	 * This is the main class in IntroductionWindow
	 * Here, we will build IntrroductionWindow Construction
	 * @param The data providers.
	 */
	public IntroductionWindow(final HashMap<String, IWeatherDataProvider> providers) {
		setFont(new Font("Clarendon", Font.BOLD, 20)); // set frame font
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/sun_with_transparent_background.png"))); // the window image instead of java logo
		setTitle("WePro");// the window title
		
		//Local Declarations
		ArrayList<JRadioButton> providerButtons = new ArrayList<JRadioButton>();
		
		JLabel lblSelectC = new JLabel("Select the Source:");
		
		/**
		 * Create the buttons based on the providers.
		 * In this case, we have two data provider WWO and Yahoo
		 */
	    Iterator<Entry<String, IWeatherDataProvider>> it = providers.entrySet().iterator();
	    int counter = 0;
	    while (it.hasNext()) {
	        Map.Entry<String, IWeatherDataProvider> pairs = (Map.Entry<String, IWeatherDataProvider>)it.next();
	        JRadioButton radioButton = new JRadioButton(pairs.getKey());
	        buttonGroup.add(radioButton);
	        providerButtons.add(radioButton);
	        if(counter == 0) {
	        	radioButton.setSelected(true);
	        	counter++;
	        }
	    }
		
	
		JLabel lblSelectDataInput = new JLabel("Select Data Input Method");
		
		/**
		 * the process to create all RadioButton {Location, LongandLat} and add them to ButtonGroup_1 
		 */
		
		final JRadioButton rdbtnLocation = new JRadioButton("Location: City & State",true); // true means default value.
		buttonGroup_1.add(rdbtnLocation);
		
		
		JRadioButton rdbtnLatitudeAndLongitute = new JRadioButton("Latitude And Longitude");
		buttonGroup_1.add(rdbtnLatitudeAndLongitute);
		
		JRadioButton rdbtnLocationZipcode = new JRadioButton("Location: Zipcode");
		buttonGroup_1.add(rdbtnLocationZipcode);
		
		/**
		 * Here, we add MouseListener to close the current window which is IntroductionWindow and to open Window2. 
		 */
		JButton btnInputData = new JButton("Input Data:");
		btnInputData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				service_Ptovider = getSelectedButtonText(buttonGroup);  // To return what a user chooses in buttonGroup
				Option = getSelectedButtonText(buttonGroup_1);          // To return what a user chooses in buttonGroup_1
				LocationRequestWindow w = new LocationRequestWindow(service_Ptovider,Option, providers);
				w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				w.setSize(560,534); // set frame size
				w.setVisible(true); // display frame
				dispose();// to close first window.
			}
		});
		
		
		/**
		 * To add all GUI's component to the layout and to set the layout.
		 * also make them in the correct place.
		 */
		JLabel lblNewLabel = new JLabel("");
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(getClass().getResource("/images/Untitled-1.png")));
		
		// YahooAndWWO is combining two images Yahoo and WWO in one image.  
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getResource("/images/YahooAndWWO.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		
		//SEtup button layout group first
		ParallelGroup buttonLayout = groupLayout.createParallelGroup(Alignment.LEADING);
		buttonLayout.addComponent(rdbtnLocation);
		buttonLayout.addComponent(rdbtnLatitudeAndLongitute);
		buttonLayout.addComponent(rdbtnLocationZipcode);
		
		//Iteratively add providers to layout
		for(int i = 0; i < providerButtons.size(); i++)
			buttonLayout.addComponent(providerButtons.get(i));
		
		/**
		 * Add all GUI components in the right horizontal place
		 */
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSelectC)
								.addComponent(lblSelectDataInput))
							.addGap(21)
							.addGroup(buttonLayout))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(146)
							.addComponent(btnInputData)))
					.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(43))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label)
							.addContainerGap())))
		);
		
		
		//Setup button layout for vertical group
		SequentialGroup seqGroup = groupLayout.createSequentialGroup();
		seqGroup.addGap(19);
		
		//Iteratively add providers to layout
		for(int i = 0; i < providerButtons.size(); i++) {
			if(i ==0) {
				ParallelGroup paraGroup = groupLayout.createParallelGroup(Alignment.BASELINE);
				paraGroup.addComponent(lblSelectC);
				paraGroup.addComponent(providerButtons.get(i));
				seqGroup.addGroup(paraGroup);
			} else {
				seqGroup.addPreferredGap(ComponentPlacement.RELATED);
				seqGroup.addComponent(providerButtons.get(i));
			}
			
		}
		
		/**
		 * Add all GUI components in the right vertical place
		 */
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(seqGroup)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_1)))
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelectDataInput)
						.addComponent(rdbtnLocation))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(rdbtnLatitudeAndLongitute)
							.addComponent(rdbtnLocationZipcode)
							.addGap(48)
							.addComponent(btnInputData))
						.addComponent(label))
					.addGap(263)
					.addComponent(lblNewLabel))
		);
		getContentPane().setLayout(groupLayout);
	}
}
