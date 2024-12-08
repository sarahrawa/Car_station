
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.JTextField;
import  java.awt.*;
import  java.awt.event.*;
import  java.awt.BorderLayout;
import  java.awt.FlowLayout;
import  java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList; // import the ArrayList class

import javax.swing.table.DefaultTableModel;


public class Station implements ActionListener,ItemListener {

    // Static variables for credentials and fields
    static final String password = "123456";
    static final  String username = "New user";
    	 static double flamb=35;

    static JTextField usernameField;
    static JPasswordField passwordField;
    static int attempts = 3; // Number of allowed attempts
    static JTextField KiloWatt;//info1-1
static  ArrayList<String >KiloWatt1=new ArrayList<String>();

static  ArrayList<Double>arrprice=new ArrayList<Double>();
static  ArrayList<String>arrinfo_clean=new ArrayList<String>();
static  ArrayList<String>oil_info=new ArrayList<String>();
static  ArrayList<String>oil_info1=new ArrayList<String>();

static JTextField textField;
static double calculatedPrice;
static  ArrayList<Double>price_s=new ArrayList<Double>();
static  ArrayList<Double>price_C=new ArrayList<Double>();
static  ArrayList<Double>price_M=new ArrayList<Double>();
static  ArrayList<String >vehical_t=new ArrayList<String>();

static  ArrayList<String >mechan_name=new ArrayList<String>();

static double price_oil=0.0;
static double price_c;

static  int kwatt;
static String label_v1[]={"Small car","Large car","Small bus","Large bus","Mechanisms"};
static String []label_R1={"Oil change","Electricity"};

static String []label_R2={"Front lamb","Back Lamb","Car Spark Plugs"};


static String []Oil_t={"Conventional Oil","Synthetic Oil","Synthetic Blend","High-Mileage Oil"};



    static JTextField pricetext;


//servic
static String Slabel_s1[] = {"Charge","Clean","Repair","Services"};

static String Slabel_s2[] = {"Service Charge","Service Clean","Service Repair"};


    // Login Screen
    static void Login_screen() {
        // Create the main frame
        JFrame frame = new JFrame("Login Screen");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel for the form
        //JPanel panel = new JPanel(new GridLayout(2, 2, 50,50)); // Grid layout for labels and fields
   JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding between components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make components stretch horizontally
        // Username label and text field
        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
	   gbc.gridy = 0;
        panel.add(usernameLabel, gbc);


        usernameField = new JTextField();
          usernameField.setPreferredSize(new Dimension(200, 25)); // Ensure consistent size
              usernameField.setToolTipText("Enter your username"); // Add tooltip


        gbc.gridx = 1;
			   gbc.gridy = 0;
        panel.add(usernameField, gbc);


        // Password label and password field
        JLabel passwordLabel = new JLabel("Password:");


         gbc.gridx = 0;
			   gbc.gridy = 1;
        panel.add(passwordLabel, gbc);

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 25)); // Ensure consistent size
        passwordField.setToolTipText("Enter your password"); // Add tooltip



         gbc.gridx = 1;
			  gbc.gridy = 1;
        panel.add(passwordField, gbc);




        // Login button
        JButton loginButton = new JButton("Login");
            loginButton.setMnemonic('L'); // Set mnemonic
               frame.getRootPane().setDefaultButton(loginButton);



        // Add action listener to handle the login
        loginButton.addActionListener(new Station());

        // Panel for the button
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(loginButton);

        // Add panels to the frame
        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

          // Center the frame on the screen
    frame.setLocationRelativeTo(null);

        // Display the frame
        frame.setVisible(true);
    }



private void handleLogin(ActionEvent e) {
	        String inputUsername = usernameField.getText();
	        String inputPassword = new String(passwordField.getPassword());

	        if (username.equals(inputUsername) && password.equals(inputPassword)) {
	            //JOptionPane.showMessageDialog(null, "Login Successful!", "Welcome", JOptionPane.INFORMATION_MESSAGE);
	            JFrame loginFrame = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
	            loginFrame.dispose(); // Close the login screen
	            Servic_Screen(); // Open the service screen
	        } else {
	            attempts--;
	            if (attempts > 0) {
	                JOptionPane.showMessageDialog(null, "Invalid Username or Password! Attempts left: " + attempts, "Error", JOptionPane.ERROR_MESSAGE);
	            } else {
	                JOptionPane.showMessageDialog(null, "Too many failed attempts. Exiting.", "Error", JOptionPane.ERROR_MESSAGE);
	                System.exit(0);
	            }
	        }
	    }

    //Service screen
    static void Servic_Screen(){
		        JButton []button_s=new JButton[4];
		               String Slabel_s[] = {"Charging","Cleaning","Mechanics","Services"};

		               JLabel []label_s =new JLabel[4];

		JPanel panel_Service=new JPanel(new GridLayout(button_s.length,1,10,10));
				JPanel panel_Service1=new JPanel(new GridLayout(label_s.length,1,10,10));

		 JFrame frame_s = new JFrame("Service Screen");
		        frame_s.setSize(400, 200);
		        frame_s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        for(int i=0;i<button_s.length;i++)
        {button_s[i]=new JButton("Click to "+Slabel_s1[i]);
                button_s[i].addActionListener(new Station());

        panel_Service.add(button_s[i]);
        label_s[i]=new JLabel(Slabel_s[i]);
        panel_Service1.add(label_s[i]);

        }
                frame_s.add(panel_Service, BorderLayout.CENTER);
                frame_s.add(panel_Service1, BorderLayout.WEST);

    frame_s.setLocationRelativeTo(null);

                frame_s.setVisible(true);



		}


	    private void handleService(String command, ActionEvent e) {
	        JFrame serviceFrame = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());

	        switch (command) {
	            case "Click to Charge":
	                serviceFrame.dispose();
	                openChargeWindow();
	                break;
	            case "Click to Clean":
	            serviceFrame.dispose();
           Cleaning_Screen();
	                break;
	            case "Click to Repair":
serviceFrame.dispose();

Repair_screen();

break;
	            case "Click to Services":
	                JOptionPane.showMessageDialog(null, "Other services initiated.", "Services", JOptionPane.INFORMATION_MESSAGE);
	                break;
	            default:
	                JOptionPane.showMessageDialog(null, "Unknown action!", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }

 static void openChargeWindow() {
    JFrame chargeFrame = new JFrame("Charging Window");
    chargeFrame.setSize(500, 300);
    chargeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        // Main panel with GridBagLayout for input
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding between components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make components fill horizontally

    JLabel chargingLabel = new JLabel("Charging numbers are :");
     gbc.gridx = 0;
        gbc.gridy = 0;
                panel.add(chargingLabel);

        KiloWatt = new JTextField(10);  // Use the static KiloWatt variable
         gbc.gridx = 1;
		        gbc.gridy = 0;
        panel.add(KiloWatt, gbc);
                 KiloWatt.addActionListener(new Station());


        JLabel chargingLabel1 = new JLabel(" KILOWATT");

gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(chargingLabel1, gbc);


        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

    // Create "OK" button, but disable it initially

    JButton charge_button = new JButton("OK");
        charge_button.setEnabled(false);  // Initially disabled

                     charge_button.addActionListener(new Station());

    JButton charge_button2 = new JButton("Discount");
                     charge_button2.addActionListener(new Station());



    panel2.add(charge_button);

        panel2.add(charge_button2);

    chargeFrame.add(panel, BorderLayout.CENTER);
        chargeFrame.add(panel2, BorderLayout.SOUTH);


    chargeFrame.setLocationRelativeTo(null);

    chargeFrame.setVisible(true);
}

 private double handleprice(String input, ActionEvent e)
 {		  double discount=0.0;
		   double pricebeforediscount=0.0;
 double priceafterdiscount=0.0;
	 try{


	 input=KiloWatt.getText();
	 KiloWatt1.add(input);
 kwatt=Integer.parseInt(input);


 if (kwatt==50)
 {discount =0.01;
 }
 else if(kwatt>50&&kwatt<60)
 {
	            discount = (kwatt - 50) * 0.01;

	 }

	  else if(kwatt==60)
	  {
	 	discount =0.02;	 }
	 	 else if(kwatt>60){
		             discount = (kwatt - 60) * 0.02;

	 }
	 else
	 {discount=0.0;}

	   pricebeforediscount = kwatt * 0.08;
	         priceafterdiscount = pricebeforediscount - discount;

	         // Store values in arrprice
	         arrprice.add((double) kwatt); // Ensure kwatt is added as a double
	         arrprice.add(pricebeforediscount);
        arrprice.add(priceafterdiscount);
        	             price_s.add(priceafterdiscount);


           JButton charge_button = (JButton) e.getSource();
		        JFrame chargeFrame = (JFrame) SwingUtilities.getWindowAncestor(charge_button);
		        for (Component comp : chargeFrame.getContentPane().getComponents()) {
		            if (comp instanceof JPanel) {
		                for (Component innerComp : ((JPanel) comp).getComponents()) {
		                    if (innerComp instanceof JButton && ((JButton) innerComp).getText().equals("OK")) {
		                        ((JButton) innerComp).setEnabled(true); // Enable the OK button
		                    }
		                }
		            }
        }



            KiloWatt.setText("");

 }


	  catch (NumberFormatException n) {
	 	        System.out.println("Invalid input: Please enter a valid number.");
	 	        	                JOptionPane.showMessageDialog(null, "Please enter the vaild mount!", "Error", JOptionPane.ERROR_MESSAGE);

	 	             throw new NumberFormatException("Invalid input: Please enter a valid number.");


    }


	return discount;}





static void Information_Screen() {

    JFrame frame_info = new JFrame("Information Screen");
    frame_info.setSize(400, 300); // Adjusted size for simplicity
    frame_info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel gradientPanel = new JPanel() {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            GradientPaint gradient = new GradientPaint(0, 0, new Color(70, 130, 180), 0, getHeight(), new Color(100, 149, 237));
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    };

    gradientPanel.setLayout(new GridLayout(5, 1, 10, 10)); // 4 rows for title, index, kilowatt, and price

    // Title Label
    JLabel titleLabel = new JLabel("Last Entry Details", JLabel.CENTER);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
    titleLabel.setForeground(Color.WHITE);
    titleLabel.setOpaque(false);

    // Check if data exists
    JLabel lastIndexLabel = new JLabel("", JLabel.CENTER);
    JLabel lastKilowattLabel = new JLabel("", JLabel.CENTER);
    JLabel lastPriceLabel = new JLabel("", JLabel.CENTER);

    if (!price_s.isEmpty() && !arrprice.isEmpty()) {
        int lastIndex = price_s.size() - 1;

        lastIndexLabel.setText(" Index: " + (lastIndex+1));
        lastKilowattLabel.setText("Kilowatt: " + arrprice.get(lastIndex) + " kW");
        lastPriceLabel.setText("Price: " + price_s.get(lastIndex) + " JD");
    } else {
        lastIndexLabel.setText("No data available.");
        lastKilowattLabel.setText("");
        lastPriceLabel.setText("");
    }

    // Styling Labels
    for (JLabel label : new JLabel[]{lastIndexLabel, lastKilowattLabel, lastPriceLabel}) {
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setForeground(Color.WHITE);
        label.setOpaque(false);
    }


   // Menu button
	    JButton button_menu = new JButton("Menu");
	    button_menu.setFont(new Font("Arial", Font.BOLD, 16));
	    button_menu.setIcon(new ImageIcon("menu-icon.png")); // Add an icon to the button
	    button_menu.setBackground(new Color(70, 130, 180));
	    button_menu.setForeground(Color.WHITE);
	    button_menu.setFocusPainted(false);
	    button_menu.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

	    // Button Hover Effect
	    button_menu.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseEntered(MouseEvent e) {
	            button_menu.setBackground(new Color(100, 149, 237)); // Lighter blue
	        }

	        @Override
	        public void mouseExited(MouseEvent e) {
	            button_menu.setBackground(new Color(70, 130, 180)); // Original color
	        }
	    });

	    button_menu.addActionListener(new Station()); // Assuming Station is defined elsewhere

    // Adding components to the gradient panel

    // Adding components to the gradient panel
    gradientPanel.add(titleLabel);
    gradientPanel.add(lastIndexLabel);
    gradientPanel.add(lastKilowattLabel);
    gradientPanel.add(lastPriceLabel);
    gradientPanel.add(button_menu);





    // Add gradient panel to frame
    frame_info.add(gradientPanel);
    frame_info.setLocationRelativeTo(null); // Center the frame on the screen
    frame_info.setVisible(true);
}
////////////////////////////end button charge and price

 static void Cleaning_Screen()
 {
	  JFrame frame_V = new JFrame("Vehicle_Screen");
	 		        frame_V.setSize(500, 400);
		        frame_V.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        JPanel panel_v= new JPanel(new GridLayout(5,1,15,15));
 panel_v.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding for the panel
 		           panel_v.setBackground(new Color(230, 240, 250)); // Soft background color for the panel

		    JLabel label_v = new JLabel("Choose your vehicle, please:", JLabel.CENTER);
        label_v.setFont(new Font("Arial", Font.BOLD, 18));
        label_v.setForeground(new Color(50, 50, 150)); // Dark blue text   JLabel label_v=new JLabel("Choose your vehicle please :",JLabel.LEFT);


        JButton[] button_v = new JButton[5];
		   for (int i=0;i<5;i++)
		   {button_v[i]= new JButton(label_v1[i]);
		   panel_v.add( button_v[i]);
		     button_v[i].addActionListener(new Station());
// Add default styles to buttons
        button_v[i].setBackground(new Color(70, 130, 180)); // Steel Blue color
        button_v[i].setForeground(Color.WHITE); // White text
        button_v[i].setFocusPainted(false); // Remove focus border
        button_v[i].setFont(new Font("Arial", Font.BOLD, 14)); // Bold font
        button_v[i].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding inside button
  button_v[i].addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                JButton button = (JButton) e.getSource();
                button.setBackground(new Color(100, 149, 237)); // Light Blue on hover
            }
            public void mouseExited(MouseEvent e) {
			                JButton button = (JButton) e.getSource();
			                button.setBackground(new Color(70, 130, 180)); // Reset to Steel Blue
			            }
        });


             panel_v.add(button_v[i]);
			    }

			    frame_V.add(label_v, BorderLayout.NORTH);
			    frame_V.add(panel_v, BorderLayout.CENTER);
			    frame_V.setLocationRelativeTo(null);
			    frame_V.setVisible(true);
}



 private double handleprice_clean(String s)
 {

	 double price_arr[]={3,4,5,6,7};


		   switch(s)
		   {
			   case "Small car":
		   price_c=price_arr[0]+(0.16*price_arr[0]);
		   break;
		    case "Large car":
		   		   price_c=price_arr[1]+(0.16*price_arr[1]);
		   break;
		     case "Large bus":
		   		   		   price_c=price_arr[2]+(0.16*price_arr[2]);
		   break;
		    case "Small bus":
		   price_c=price_arr[3]+(0.16*price_arr[3]);
		   break;

		    case "Mechanisms":
		   	  price_c=price_arr[4]+(0.16*price_arr[4]);
		   break;

			   }

price_C.add( price_c);
vehical_t.add(s);
	 		  return  price_c;


	 }


public void screen_price_clean(String S1)

{

	JFrame frame_Vprice = new JFrame("Price of Cleaning");
	    frame_Vprice.setSize(500, 400);
	    frame_Vprice.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    // Main panel with padding
	    JPanel panel_vprice = new JPanel();
	    panel_vprice.setLayout(new BoxLayout(panel_vprice, BoxLayout.Y_AXIS));
	    panel_vprice.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding
	    panel_vprice.setBackground(new Color(230, 240, 250)); // Soft background color

	    // Calculate the price
	    double PC = handleprice_clean(S1);

	    // Labels with styling
	    JLabel label_vprice = new JLabel("The price of cleaning your vehicle is:");
	    label_vprice.setFont(new Font("Arial", Font.BOLD, 16));
	    label_vprice.setAlignmentX(JLabel.CENTER_ALIGNMENT);
	    label_vprice.setForeground(new Color(50, 50, 150)); // Dark blue text



	    JLabel label_vprice2 = new JLabel(PC + " JD");
	    label_vprice2.setFont(new Font("Arial", Font.BOLD, 16));
	    label_vprice2.setAlignmentX(JLabel.CENTER_ALIGNMENT);
	    label_vprice2.setForeground(new Color(50, 50, 150)); // Dark blue text

	    // Adding components to the panel
	    panel_vprice.add(Box.createVerticalGlue()); // Adds vertical spacing
	    panel_vprice.add(label_vprice);

	    panel_vprice.add(Box.createVerticalStrut(10)); // Spacing between labels
	    panel_vprice.add(label_vprice2);
	    panel_vprice.add(Box.createVerticalGlue()); // Adds vertical spacing

	    JPanel price_pc=new JPanel(new GridLayout(1,2));

	    JButton button_menu = new JButton("Cancel");
		        button_menu.setFont(new Font("Arial", Font.BOLD, 16));
		        button_menu.setIcon(new ImageIcon("menu-icon.png")); // Add an icon to the button
		        button_menu.setBackground(new Color(70, 130, 180));
		        button_menu.setForeground(Color.WHITE);
		        button_menu.setFocusPainted(false);
		        button_menu.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

		        // Button Hover Effect
		        button_menu.addMouseListener(new MouseAdapter() {
		            @Override
		            public void mouseEntered(MouseEvent e) {
		                button_menu.setBackground(new Color(100, 149, 237)); // Lighter blue
		            }

		            @Override
		            public void mouseExited(MouseEvent e) {
		                button_menu.setBackground(new Color(70, 130, 180)); // Original color
		            }
		        });
		       button_menu.addActionListener(new Station());


		       JButton button_OK1 = new JButton("Agree");
			           button_OK1.setFont(new Font("Arial", Font.BOLD, 16));
			           button_OK1.setBackground(new Color(70, 130, 180));
			           button_OK1.setForeground(Color.WHITE);
			           button_OK1.setFocusPainted(false);
			           button_OK1.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

			           // Button Hover Effect
			           button_OK1.addMouseListener(new MouseAdapter() {
			               @Override
			               public void mouseEntered(MouseEvent e) {
			                   button_OK1.setBackground(new Color(100, 149, 237)); // Lighter blue
			               }

			               @Override
			               public void mouseExited(MouseEvent e) {
			                   button_OK1.setBackground(new Color(70, 130, 180)); // Original color
			               }
			           });
			          button_OK1.addActionListener(new Station());

price_pc.add(button_menu);
price_pc.add(button_OK1);




arrinfo_clean.add(S1);
arrinfo_clean.add(Double.toString(PC));



	    // Add panel to frame
	    frame_Vprice.add(price_pc,BorderLayout.SOUTH);
	    	    frame_Vprice.add(panel_vprice);

	    frame_Vprice.setLocationRelativeTo(null); // Center on screen
    frame_Vprice.setVisible(true);




	}




 static void showCleanedInfoScreen(String command) {
     JFrame frame_infoc = new JFrame("Information Screen");
     frame_infoc.setSize(600, 500);
     frame_infoc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     // Panel with gradient background
     JPanel gradientPanel1 = new JPanel() {
         @Override
         protected void paintComponent(Graphics g) {
             super.paintComponent(g);
             Graphics2D g2d = (Graphics2D) g;
             GradientPaint gradient = new GradientPaint(0, 0, new Color(70, 130, 180), 0, getHeight(), new Color(100, 149, 237));
             g2d.setPaint(gradient);
             g2d.fillRect(0, 0, getWidth(), getHeight());
         }
     };
     gradientPanel1.setLayout(new BorderLayout());

     // Panel for index (only last index)
     JPanel panel_index1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
     panel_index1.setBackground(new Color(230, 240, 250)); // Light background
     panel_index1.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding
     panel_index1.setOpaque(false);

     // Get the last index
     int lastIndex = price_C.size() - 1; // Last index (0-based)
     JLabel indexLabel = new JLabel("Index: " + (lastIndex+1), JLabel.CENTER);
     indexLabel.setOpaque(true);
     indexLabel.setBackground(new Color(100, 149, 237));
     indexLabel.setFont(new Font("Arial", Font.BOLD, 18));
     indexLabel.setForeground(Color.WHITE);
     indexLabel.setPreferredSize(new Dimension(250, 40)); // Adjust size
     indexLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
     panel_index1.add(indexLabel);

     // Panel for names (price)
     JPanel panel_name1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
     panel_name1.setBackground(new Color(230, 240, 250));
     panel_name1.setOpaque(false);

     JLabel priceLabel = new JLabel("Cleaning Price", JLabel.CENTER);
     priceLabel.setOpaque(true);
     priceLabel.setBackground(new Color(70, 130, 180)); // Light blue background
     priceLabel.setFont(new Font("Arial", Font.BOLD, 16));
     priceLabel.setForeground(Color.WHITE);
     priceLabel.setPreferredSize(new Dimension(250, 40)); // Adjust size
     priceLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

     panel_name1.add(priceLabel);

     // Panel for the last price
     JPanel panel_number1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
     panel_number1.setBackground(new Color(230, 240, 250));
     panel_number1.setOpaque(false);

     // Get the last price from price_C
     Double lastPrice = price_C.get(price_C.size() - 1); // Last price

     JLabel lastPriceLabel = new JLabel(lastPrice + " JD", JLabel.CENTER);
     lastPriceLabel.setOpaque(true);
     lastPriceLabel.setBackground(new Color(200, 220, 240));
     lastPriceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
     lastPriceLabel.setForeground(new Color(50, 50, 150));
     lastPriceLabel.setPreferredSize(new Dimension(250, 40)); // Adjust size
     lastPriceLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

     panel_number1.add(lastPriceLabel);

     // Menu button with icon and smooth hover effect
     JButton button_menu = new JButton("Menu");
     button_menu.setFont(new Font("Arial", Font.BOLD, 16));
     button_menu.setIcon(new ImageIcon("menu-icon.png"));  // Ensure the path is correct
     button_menu.setBackground(new Color(70, 130, 180));
     button_menu.setForeground(Color.WHITE);
     button_menu.setFocusPainted(false);
     button_menu.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
     button_menu.setPreferredSize(new Dimension(120, 50)); // Better button size

     // Button Hover Effect with Smooth Transition
     button_menu.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
             button_menu.setBackground(new Color(100, 149, 237)); // Lighter blue
         }

         @Override
         public void mouseExited(MouseEvent e) {
             button_menu.setBackground(new Color(70, 130, 180)); // Original color
         }
     });

     button_menu.addActionListener(new Station());

     // Adding shadow effect to button and panels for depth
     button_menu.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 150), 2));

     // Layout the components in the main frame
     JPanel contentPanel = new JPanel();
     contentPanel.setLayout(new GridLayout(4, 1, 10, 10)); // Spacing between rows
     contentPanel.setBackground(new Color(230, 240, 250));
     contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding
     contentPanel.add(panel_index1);
     contentPanel.add(panel_name1);
     contentPanel.add(panel_number1);
     contentPanel.add(button_menu);

     frame_infoc.add(gradientPanel1, BorderLayout.CENTER);
     gradientPanel1.add(contentPanel, BorderLayout.CENTER);

     frame_infoc.setLocationRelativeTo(null); // Center the frame
     frame_infoc.setVisible(true);
 }

/////////////end of cleaning screen

 static void Repair_screen(){
	  JFrame frame_R= new JFrame("Repair screen");
	 		        frame_R.setSize(500, 400);
		        frame_R.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        JPanel panel_R= new JPanel(new GridLayout(5,1,15,15));
 panel_R.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding for the panel
 		           panel_R.setBackground(new Color(230, 240, 250)); // Soft background color for the panel

		    JLabel label_R = new JLabel("Choose the action, please:", JLabel.CENTER);
        label_R.setFont(new Font("Arial", Font.BOLD, 18));
        label_R.setForeground(new Color(50, 50, 150)); // Dark blue text   JLabel label_v=new JLabel("Choose your vehicle please :",JLabel.LEFT);


        JButton[] button_R = new JButton[2];
		   for (int i=0;i<2;i++)
		   {button_R[i]= new JButton(label_R1[i]);
		   panel_R.add( button_R[i]);
		     button_R[i].addActionListener(new Station());
// Add default styles to buttons
        button_R[i].setBackground(new Color(70, 130, 180)); // Steel Blue color
        button_R[i].setForeground(Color.WHITE); // White text
        button_R[i].setFocusPainted(false); // Remove focus border
        button_R[i].setFont(new Font("Arial", Font.BOLD, 14)); // Bold font
        button_R[i].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding inside button
  button_R[i].addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                JButton button = (JButton) e.getSource();
                button.setBackground(new Color(100, 149, 237)); // Light Blue on hover
            }
            public void mouseExited(MouseEvent e) {
			                JButton button = (JButton) e.getSource();
			                button.setBackground(new Color(70, 130, 180)); // Reset to Steel Blue
			            }
        });


             panel_R.add(button_R[i]);
			    }

			    frame_R.add(label_R, BorderLayout.NORTH);
			    frame_R.add(panel_R, BorderLayout.CENTER);
			    frame_R.setLocationRelativeTo(null);
			    frame_R.setVisible(true);
}


 static void oil_car(){


 JFrame frame_oil = new JFrame("Oil Car Selection");
        frame_oil.setSize(600, 500);
        frame_oil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set a background color for the frame
        frame_oil.getContentPane().setBackground(new Color(230, 230, 250)); // Lavender color

        // Create a JPanel with GridLayout to organize components
        JPanel panel_oil1 = new JPanel(new GridLayout(3, 1, 10, 10)); // 3 rows and 1 column for vertical alignment
        panel_oil1.setBackground(new Color(255, 250, 240)); // Off-white background color
        panel_oil1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding to the panel

        // Create a JLabel with custom font and color
        JLabel label_oil = new JLabel("Please choose your vehicle:");
        label_oil.setFont(new Font("Arial", Font.BOLD, 16)); // Bold font, 16px size
        label_oil.setForeground(new Color(0, 51, 102)); // Dark blue color
        label_oil.setHorizontalAlignment(SwingConstants.CENTER); // Center-align text
        label_oil.setPreferredSize(new Dimension(600, 40)); // Set preferred size for consistent height

        // Create JComboBox for vehicle selection
        JComboBox<String> comboBox = new JComboBox<>(label_v1);
        // Customize the combo box appearance
        comboBox.setFont(new Font("Arial", Font.PLAIN, 14)); // Font size 14px
        comboBox.setBackground(new Color(255, 255, 255)); // White background for combo box
        comboBox.setForeground(new Color(0, 51, 102)); // Dark blue text
        comboBox.setMaximumRowCount(4); // Limit number of visible rows to make it look clean
comboBox.setSelectedItem(null);

        // Add an ItemListener to handle item selection changes
        comboBox.addItemListener(new Station());

        // Add the JLabel and JComboBox to the JPanel
        panel_oil1.add(label_oil);
        panel_oil1.add(comboBox);

        // Create a second JPanel for oil selection
        JPanel panel_oil2 = new JPanel(new GridLayout(3, 1, 10, 10)); // 3 rows and 1 column for vertical alignment
        panel_oil2.setBackground(new Color(255, 250, 240)); // Off-white background color
        panel_oil2.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding to the panel

        // Create a JLabel for oil type selection
        JLabel label_oil1 = new JLabel("Please choose the oil type:");
        label_oil1.setFont(new Font("Arial", Font.BOLD, 16)); // Bold font, 16px size
        label_oil1.setForeground(new Color(0, 51, 102)); // Dark blue color
        label_oil1.setHorizontalAlignment(SwingConstants.CENTER); // Center-align text
        label_oil1.setPreferredSize(new Dimension(600, 40)); // Set preferred size for consistent height

        // Create JComboBox for oil type selection
        JComboBox<String> comboBox1 = new JComboBox<>(Oil_t);
        // Customize the combo box appearance
        comboBox1.setFont(new Font("Arial", Font.PLAIN, 14)); // Font size 14px
        comboBox1.setBackground(new Color(255, 255, 255)); // White background for combo box
        comboBox1.setForeground(new Color(0, 51, 102)); // Dark blue text
        comboBox1.setMaximumRowCount(4); // Limit number of visible rows to make it look clean
comboBox1.setSelectedItem(null);
        // Add an ItemListener to handle item selection changes
        comboBox1.addItemListener(new Station());

        // Add the JLabel and JComboBox to the second JPanel
        panel_oil2.add(label_oil1);
        panel_oil2.add(comboBox1);

        // Create a panel for user input (TextField and Submit button)
	       JPanel panel_oil3 = new JPanel();
		           panel_oil3.setBackground(new Color(255, 250, 240)); // Off-white background color
		           panel_oil3.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20)); // Center-align the components

		           // Create a JLabel for the text field
		           JLabel label_oil2 = new JLabel("Enter the amount of oil in kilo");
		           label_oil2.setFont(new Font("Arial", Font.BOLD, 16)); // Bold font, 16px size
		           label_oil2.setForeground(new Color(0, 51, 102)); // Dark blue color
		           label_oil2.setHorizontalAlignment(SwingConstants.CENTER); // Center-align text

		           // Create the JTextField
		            textField = new JTextField();
		           textField.setFont(new Font("Arial", Font.PLAIN, 14)); // Font size 14px
		           textField.setBackground(new Color(255, 255, 255)); // White background
		           textField.setForeground(new Color(0, 51, 102)); // Dark blue text
		           textField.setPreferredSize(new Dimension(200, 30)); // Set size for the text field
textField.addActionListener(new Station());
		           // Add the label and text field to the panel
		           panel_oil3.add(label_oil2);
		           panel_oil3.add(textField);
		           // Create a Submit button
		           JButton submitButton = new JButton("Submit");
		           submitButton.setFont(new Font("Arial", Font.BOLD, 14)); // Bold font for the button
		           submitButton.setBackground(new Color(0, 51, 102)); // Dark blue background
		           submitButton.setForeground(Color.WHITE); // White text color
		           submitButton.setPreferredSize(new Dimension(120, 40)); // Set button size
		           submitButton.addActionListener(new Station());

        panel_oil3.add(submitButton);

	           // Add the panels to the JFrame using BorderLayout
	           frame_oil.add(panel_oil1, BorderLayout.NORTH);
	           frame_oil.add(panel_oil2, BorderLayout.CENTER);
	           frame_oil.add(panel_oil3, BorderLayout.SOUTH); // Add the text field and submit button at the bottom

	           // Set the window location to the center and make it visible
	           frame_oil.setLocationRelativeTo(null); // Center the window
        frame_oil.setVisible(true); // Make the window visible





 }
static double price_oil1;
static double handleprice_oil(String s){

s=textField.getText();
oil_info1.add(s);

	 price_oil1= Double.parseDouble(s);

	price_oil=price_oil1+(price_oil1*0.16);


//was here
return price_oil;
	 }

////



   static void showoil_price(String pp) {
        JFrame newFrame = new JFrame("Oil price");
        newFrame.setSize(400, 300);
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Set the background color for the new frame
        newFrame.getContentPane().setBackground(new Color(230, 230, 250)); // Lavender color

        // Create a label to display the price
         calculatedPrice = handleprice_oil(pp);


        JLabel label = new JLabel("The price is: " + Double.toString(calculatedPrice));

        label.setFont(new Font("Arial", Font.BOLD, 20)); // Bold font
        label.setForeground(new Color(0, 51, 102)); // Dark blue color
        label.setHorizontalAlignment(SwingConstants.CENTER); // Center the text


        // Create "Done" button
        JPanel panel2 = new JPanel(new GridLayout(1 ,2,50,50));

		    JButton D_button = new JButton("Done");

		                     D_button.addActionListener(new Station());

 JButton c_button = new JButton("Cancel");

		                     c_button.addActionListener(new Station());


		    panel2.add(D_button);
		    		    panel2.add(c_button);


		        newFrame.add(panel2, BorderLayout.SOUTH);







        // Add the label to the frame and make the frame visible
        newFrame.add(label, BorderLayout.CENTER);
        newFrame.setLocationRelativeTo(null); // Center the window
        newFrame.setVisible(true); // Make the window visible
    }




static double handleprice_flamb(){//1



	flamb=flamb+(flamb*0.16);


	return flamb;
	 }


	 static  double flambb=35;

static double handleprice_blamb(){//2


	flambb=flambb+(flambb*0.16);


	return flambb;
	 }


//////
static double Spark=50;

static double Spark_Plugs (){//3


	Spark=Spark+(Spark*0.16);


	return Spark;
	 }



	  static void Spark_screen() {//1-1
	 	         JFrame newFramef = new JFrame("Spark  price");
	 	         newFramef.setSize(400, 300);
	 	         newFramef.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	 	         // Set the background color for the new frame
	 	         newFramef.getContentPane().setBackground(new Color(230, 230, 250)); // Lavender color

	 	         // Create a label to display the price


	 	         JLabel label = new JLabel("The price is: " + Double.toString(Spark_Plugs()));

	 	         label.setFont(new Font("Arial", Font.BOLD, 20)); // Bold font
	 	         label.setForeground(new Color(0, 51, 102)); // Dark blue color
	 	         label.setHorizontalAlignment(SwingConstants.CENTER); // Center the text


	 	         // Create "done" button
	 	         JPanel panel2 = new JPanel(new GridLayout(1 ,2,50,50));

	 	 		    JButton D_button = new JButton("done");

	 	 		                     D_button.addActionListener(new Station());

	 	  JButton c_button = new JButton("Not agreed");

	 	 		                     c_button.addActionListener(new Station());


	 	 		    panel2.add(D_button);
	 	 		    		    panel2.add(c_button);


	 	 		        newFramef.add(panel2, BorderLayout.SOUTH);


	 	         // Add the label to the frame and make the frame visible
	 	         newFramef.add(label, BorderLayout.CENTER);
	 	         newFramef.setLocationRelativeTo(null); // Center the window
	 	         newFramef.setVisible(true); // Make the window visible
	 	     }


	 	     public static void done() {
			 JFrame frame_oil = new JFrame("Car Spark Plugs price");
			         frame_oil.setSize(400, 300);
			         frame_oil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			         // Panel with gradient background
			         JPanel gradientPanel = new JPanel() {
			             @Override
			             protected void paintComponent(Graphics g) {
			                 super.paintComponent(g);
			                 Graphics2D g2d = (Graphics2D) g;
			                 GradientPaint gradient = new GradientPaint(0, 0, new Color(70, 130, 180), 0, getHeight(), new Color(100, 149, 237));
			                 g2d.setPaint(gradient);
			                 g2d.fillRect(0, 0, getWidth(), getHeight());
			             }
			         };
			         gradientPanel.setLayout(new BorderLayout(10, 10));

			         // Create a panel to hold the labels
			         JPanel infoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
			         infoPanel.setBackground(new Color(230, 240, 250)); // Light background
			         infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding

			         // Labels for index, name, and price
			         JLabel indexLabel = new JLabel("Index:");
			         JLabel nameLabel = new JLabel("Item Name:");
			         JLabel priceLabel = new JLabel("Price:");

			         // Values for the index, name, and price
			         JLabel indexValue = new JLabel("1");
			         JLabel nameValue = new JLabel("Car Spark Plugs");
			         JLabel priceValue = new JLabel(Double.toString(Spark_Plugs()));//////////////////////

			         // Customize label appearance
			         Font labelFont = new Font("Arial", Font.BOLD, 16);
			         indexLabel.setFont(labelFont);
			         nameLabel.setFont(labelFont);
			         priceLabel.setFont(labelFont);

			         indexValue.setFont(new Font("Arial", Font.PLAIN, 16));
			         nameValue.setFont(new Font("Arial", Font.PLAIN, 16));
			         priceValue.setFont(new Font("Arial", Font.PLAIN, 16));

			         // Add labels and values to the panel
			         infoPanel.add(indexLabel);
			         infoPanel.add(indexValue);
			         infoPanel.add(nameLabel);
			         infoPanel.add(nameValue);
			         infoPanel.add(priceLabel);
			         infoPanel.add(priceValue);

			         // Menu button with smooth hover effect
			         JButton button_menu = new JButton("Menu");
			         button_menu.setFont(new Font("Arial", Font.BOLD, 16));
			         button_menu.setBackground(new Color(70, 130, 180));
			         button_menu.setForeground(Color.WHITE);
			         button_menu.setFocusPainted(false);
			         button_menu.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
			         button_menu.setPreferredSize(new Dimension(100, 40)); // Button size
			         button_menu.addActionListener(new Station()); // Close the frame on click

			         // Button Hover Effect
			         button_menu.addMouseListener(new MouseAdapter() {
			             @Override
			             public void mouseEntered(MouseEvent e) {
			                 button_menu.setBackground(new Color(100, 149, 237)); // Lighter blue
			             }

			             @Override
			             public void mouseExited(MouseEvent e) {
			                 button_menu.setBackground(new Color(70, 130, 180)); // Original color
			             }
			         });

			         // Layout the components
			         gradientPanel.add(infoPanel, BorderLayout.CENTER);
			         gradientPanel.add(button_menu, BorderLayout.SOUTH);

			         // Add the panel to the frame
			         frame_oil.add(gradientPanel);
			         frame_oil.setLocationRelativeTo(null); // Center the frame
			         frame_oil.setVisible(true); // Make the window visible
			     }




	 ////


	  static void flamp_price_screen() {//1-1
	         JFrame newFramef = new JFrame("Front Lamp  price");
	         newFramef.setSize(400, 300);
	         newFramef.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	         // Set the background color for the new frame
	         newFramef.getContentPane().setBackground(new Color(230, 230, 250)); // Lavender color

	         // Create a label to display the price


	         JLabel label = new JLabel("The price is: " + Double.toString(handleprice_flamb()));

	         label.setFont(new Font("Arial", Font.BOLD, 20)); // Bold font
	         label.setForeground(new Color(0, 51, 102)); // Dark blue color
	         label.setHorizontalAlignment(SwingConstants.CENTER); // Center the text


	         // Create "ageed" button
	         JPanel panel2 = new JPanel(new GridLayout(1 ,2,50,50));

	 		    JButton D_button = new JButton("Agreed");

	 		                     D_button.addActionListener(new Station());

	  JButton c_button = new JButton("Not agreed");

	 		                     c_button.addActionListener(new Station());


	 		    panel2.add(D_button);
	 		    		    panel2.add(c_button);


	 		        newFramef.add(panel2, BorderLayout.SOUTH);







	         // Add the label to the frame and make the frame visible
	         newFramef.add(label, BorderLayout.CENTER);
	         newFramef.setLocationRelativeTo(null); // Center the window
	         newFramef.setVisible(true); // Make the window visible
	     }




/////

	  static void blamp_price_screen() {//2-1
	         JFrame newFramef = new JFrame("back Lamp  price");
	         newFramef.setSize(400, 300);
	         newFramef.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	         // Set the background color for the new frame
	         newFramef.getContentPane().setBackground(new Color(230, 230, 250)); // Lavender color

	         // Create a label to display the price


	         JLabel label = new JLabel("The price is: " + Double.toString(handleprice_blamb()));

	         label.setFont(new Font("Arial", Font.BOLD, 20)); // Bold font
	         label.setForeground(new Color(0, 51, 102)); // Dark blue color
	         label.setHorizontalAlignment(SwingConstants.CENTER); // Center the text


	         // Create "ageed" button
	         JPanel panel2 = new JPanel(new GridLayout(1 ,2,50,50));

	 		    JButton D_button = new JButton("agreed");

	 		                     D_button.addActionListener(new Station());

	  JButton c_button = new JButton("Not agreed");

	 		                     c_button.addActionListener(new Station());


	 		    panel2.add(D_button);
	 		    		    panel2.add(c_button);


	 		        newFramef.add(panel2, BorderLayout.SOUTH);

////////////





	         // Add the label to the frame and make the frame visible
	         newFramef.add(label, BorderLayout.CENTER);
	         newFramef.setLocationRelativeTo(null); // Center the window
	         newFramef.setVisible(true); // Make the window visible
	     }






 public static void blambagreed() {
JFrame frame_oil = new JFrame("Back lamb price");
        frame_oil.setSize(400, 300);
        frame_oil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel with gradient background
        JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, new Color(70, 130, 180), 0, getHeight(), new Color(100, 149, 237));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        gradientPanel.setLayout(new BorderLayout(10, 10));

        // Create a panel to hold the labels
        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        infoPanel.setBackground(new Color(230, 240, 250)); // Light background
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding

        // Labels for index, name, and price
        JLabel indexLabel = new JLabel("Index:");
        JLabel nameLabel = new JLabel("Item Name:");
        JLabel priceLabel = new JLabel("Price:");

        // Values for the index, name, and price
        JLabel indexValue = new JLabel("1");
        JLabel nameValue = new JLabel("Back lamb");
        JLabel priceValue = new JLabel(Double.toString(handleprice_blamb()));//////////////////////

        // Customize label appearance
        Font labelFont = new Font("Arial", Font.BOLD, 16);
        indexLabel.setFont(labelFont);
        nameLabel.setFont(labelFont);
        priceLabel.setFont(labelFont);

        indexValue.setFont(new Font("Arial", Font.PLAIN, 16));
        nameValue.setFont(new Font("Arial", Font.PLAIN, 16));
        priceValue.setFont(new Font("Arial", Font.PLAIN, 16));

        // Add labels and values to the panel
        infoPanel.add(indexLabel);
        infoPanel.add(indexValue);
        infoPanel.add(nameLabel);
        infoPanel.add(nameValue);
        infoPanel.add(priceLabel);
        infoPanel.add(priceValue);

        // Menu button with smooth hover effect
        JButton button_menu = new JButton("Menu");
        button_menu.setFont(new Font("Arial", Font.BOLD, 16));
        button_menu.setBackground(new Color(70, 130, 180));
        button_menu.setForeground(Color.WHITE);
        button_menu.setFocusPainted(false);
        button_menu.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button_menu.setPreferredSize(new Dimension(100, 40)); // Button size
        button_menu.addActionListener(new Station()); // Close the frame on click

        // Button Hover Effect
        button_menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button_menu.setBackground(new Color(100, 149, 237)); // Lighter blue
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button_menu.setBackground(new Color(70, 130, 180)); // Original color
            }
        });

        // Layout the components
        gradientPanel.add(infoPanel, BorderLayout.CENTER);
        gradientPanel.add(button_menu, BorderLayout.SOUTH);

        // Add the panel to the frame
        frame_oil.add(gradientPanel);
        frame_oil.setLocationRelativeTo(null); // Center the frame
        frame_oil.setVisible(true); // Make the window visible
    }


///////////////////////


 public static void flambagreed() {
JFrame frame_oil = new JFrame("Front lamb price");
        frame_oil.setSize(400, 300);
        frame_oil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel with gradient background
        JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, new Color(70, 130, 180), 0, getHeight(), new Color(100, 149, 237));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        gradientPanel.setLayout(new BorderLayout(10, 10));

        // Create a panel to hold the labels
        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        infoPanel.setBackground(new Color(230, 240, 250)); // Light background
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding

        // Labels for index, name, and price
        JLabel indexLabel = new JLabel("Index:");
        JLabel nameLabel = new JLabel("Item Name:");
        JLabel priceLabel = new JLabel("Price:");

        // Values for the index, name, and price
        JLabel indexValue = new JLabel("1");
        JLabel nameValue = new JLabel("Front  lamb");
        JLabel priceValue = new JLabel(Double.toString(handleprice_flamb()));//////////////////////

        // Customize label appearance
        Font labelFont = new Font("Arial", Font.BOLD, 16);
        indexLabel.setFont(labelFont);
        nameLabel.setFont(labelFont);
        priceLabel.setFont(labelFont);

        indexValue.setFont(new Font("Arial", Font.PLAIN, 16));
        nameValue.setFont(new Font("Arial", Font.PLAIN, 16));
        priceValue.setFont(new Font("Arial", Font.PLAIN, 16));

        // Add labels and values to the panel
        infoPanel.add(indexLabel);
        infoPanel.add(indexValue);
        infoPanel.add(nameLabel);
        infoPanel.add(nameValue);
        infoPanel.add(priceLabel);
        infoPanel.add(priceValue);

        // Menu button with smooth hover effect
        JButton button_menu = new JButton("Menu");
        button_menu.setFont(new Font("Arial", Font.BOLD, 16));
        button_menu.setBackground(new Color(70, 130, 180));
        button_menu.setForeground(Color.WHITE);
        button_menu.setFocusPainted(false);
        button_menu.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button_menu.setPreferredSize(new Dimension(100, 40)); // Button size
        button_menu.addActionListener(new Station()); // Close the frame on click

        // Button Hover Effect
        button_menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button_menu.setBackground(new Color(100, 149, 237)); // Lighter blue
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button_menu.setBackground(new Color(70, 130, 180)); // Original color
            }
        });

        // Layout the components
        gradientPanel.add(infoPanel, BorderLayout.CENTER);
        gradientPanel.add(button_menu, BorderLayout.SOUTH);

        // Add the panel to the frame
        frame_oil.add(gradientPanel);
        frame_oil.setLocationRelativeTo(null); // Center the frame
        frame_oil.setVisible(true); // Make the window visible
    }

//////
 public static void oil_screen_info() {
         // Create the main frame
         JFrame frame_oil = new JFrame("Oil Information");
         frame_oil.setSize(600, 500);
         frame_oil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         // Panel with gradient background
         JPanel gradientPanel = new JPanel() {
             @Override
             protected void paintComponent(Graphics g) {
                 super.paintComponent(g);
                 Graphics2D g2d = (Graphics2D) g;
                 GradientPaint gradient = new GradientPaint(0, 0, new Color(70, 130, 180), 0, getHeight(), new Color(100, 149, 237));
                 g2d.setPaint(gradient);
                 g2d.fillRect(0, 0, getWidth(), getHeight());
             }
         };
         gradientPanel.setLayout(new BorderLayout());

         // Panel for the last index
         JPanel panel_index = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
         panel_index.setOpaque(false);

         int lastIndex = oil_info1.size() - 1; // Get the last index
         JLabel indexLabel = new JLabel("Index: " + (lastIndex + 1), JLabel.CENTER);
         indexLabel.setOpaque(true);
         indexLabel.setBackground(new Color(100, 149, 237));
         indexLabel.setFont(new Font("Arial", Font.BOLD, 18));
         indexLabel.setForeground(Color.WHITE);
         indexLabel.setPreferredSize(new Dimension(250, 40));
         indexLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
         panel_index.add(indexLabel);

         // Panel for the last oil info data
         JPanel panel_info = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
         panel_info.setOpaque(false);

         JLabel infoLabel = new JLabel(oil_info1.isEmpty() ? "N/A" : oil_info1.get(oil_info1.size() - 1), JLabel.CENTER);
         infoLabel.setOpaque(true);
         infoLabel.setBackground(new Color(200, 220, 240));
         infoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
         infoLabel.setForeground(new Color(50, 50, 150));
         infoLabel.setPreferredSize(new Dimension(250, 40));
         infoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
         panel_info.add(infoLabel);

         // Menu button
         JButton button_menu = new JButton("Menu");
         button_menu.setFont(new Font("Arial", Font.BOLD, 16));
         button_menu.setBackground(new Color(70, 130, 180));
         button_menu.setForeground(Color.WHITE);
         button_menu.setFocusPainted(false);
         button_menu.setPreferredSize(new Dimension(120, 50));

         button_menu.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseEntered(MouseEvent e) {
                 button_menu.setBackground(new Color(100, 149, 237));
             }

             @Override
             public void mouseExited(MouseEvent e) {
                 button_menu.setBackground(new Color(70, 130, 180));
             }
         });

         button_menu.addActionListener(new Station());

         // Adding content to the main frame
         JPanel contentPanel = new JPanel(new GridLayout(3, 1, 10, 10));
         contentPanel.setOpaque(false);
         contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
         contentPanel.add(panel_index);
         contentPanel.add(panel_info);
         contentPanel.add(button_menu);

         gradientPanel.add(contentPanel, BorderLayout.CENTER);
         frame_oil.add(gradientPanel);
         frame_oil.setLocationRelativeTo(null);
         frame_oil.setVisible(true);
    }
static void Service_screen(){
	  JFrame frame_E= new JFrame("Service screen");
	 		        frame_E.setSize(500, 400);
		        frame_E.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        JPanel panel_E= new JPanel(new GridLayout(5,1,15,15));
 panel_E.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding for the panel
 		           panel_E.setBackground(new Color(230, 240, 250)); // Soft background color for the panel

		    JLabel label_E = new JLabel("Choose the service , please:", JLabel.CENTER);
        label_E.setFont(new Font("Arial", Font.BOLD, 18));
        label_E.setForeground(new Color(50, 50, 150)); // Dark blue text   JLabel label_v=new JLabel("Choose your vehicle please :",JLabel.LEFT);


        JButton[] button_R = new JButton[3];
		   for (int i=0;i<3;i++)
		   {button_R[i]= new JButton(Slabel_s2[i]);
		   panel_E.add( button_R[i]);
		     button_R[i].addActionListener(new Station());
// Add default styles to buttons
        button_R[i].setBackground(new Color(70, 130, 180)); // Steel Blue color
        button_R[i].setForeground(Color.WHITE); // White text
        button_R[i].setFocusPainted(false); // Remove focus border
        button_R[i].setFont(new Font("Arial", Font.BOLD, 14)); // Bold font
        button_R[i].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding inside button
  button_R[i].addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                JButton button = (JButton) e.getSource();
                button.setBackground(new Color(100, 149, 237)); // Light Blue on hover
            }
            public void mouseExited(MouseEvent e) {
			                JButton button = (JButton) e.getSource();
			                button.setBackground(new Color(70, 130, 180)); // Reset to Steel Blue
			            }
        });


 //Menu button with icon and smooth hover effect
        JButton button_menu = new JButton("Menu");
        button_menu.setFont(new Font("Arial", Font.BOLD, 16));
        button_menu.setIcon(new ImageIcon("menu-icon.png")); // Add an icon to the button
        button_menu.setBackground(new Color(70, 130, 180));
        button_menu.setForeground(Color.WHITE);
        button_menu.setFocusPainted(false);
        button_menu.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Button Hover Effect
        button_menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button_menu.setBackground(new Color(100, 149, 237)); // Lighter blue
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button_menu.setBackground(new Color(70, 130, 180)); // Original color
            }
        });
       button_menu.addActionListener(new Station());

frame_E.add(button_menu, BorderLayout.SOUTH);



             panel_E.add(button_R[i]);
			    }

			    frame_E.add(label_E, BorderLayout.NORTH);
			    frame_E.add(panel_E, BorderLayout.CENTER);
			    frame_E.setLocationRelativeTo(null);
			    frame_E.setVisible(true);
}




  static void show_servicetable() {
        // Create the main frame
        JFrame frame = new JFrame("Servace Table");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	       // Create a table model to hold data
	       String[] columnNames = {"Index", "Kwatt","Price"};
	       DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

	       // Assuming price_c is a List<Integer>
	       for (int i = 0; i < price_s.size(); i++) {
	           tableModel.addRow(new Object[]{i + 1,KiloWatt1.get(i) ,price_s.get(i)}); // Access list with .get(i)
	       }

	       // Create a JTable with the model
	       JTable table = new JTable(tableModel);
	       table.setFont(new Font("Arial", Font.PLAIN, 14));
	       table.setRowHeight(25);
	       table.setFillsViewportHeight(true);

	       // Add the table to a scroll pane for better visualization
	       JScrollPane scrollPane = new JScrollPane(table);



		    // Create a Back button
		       JButton backButton = new JButton("Back");
		       backButton.setFont(new Font("Arial", Font.BOLD, 14));
		       backButton.setBackground(new Color(70, 130, 180)); // Steel Blue
		       backButton.setForeground(Color.WHITE); // White text
		       backButton.setFocusPainted(false);
		       backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

		       // Add hover effect for the button
		       backButton.addMouseListener(new MouseAdapter() {
		           @Override
		           public void mouseEntered(MouseEvent e) {
		               backButton.setBackground(new Color(100, 149, 237)); // Lighter Blue
		           }

		           @Override
		           public void mouseExited(MouseEvent e) {
		               backButton.setBackground(new Color(70, 130, 180)); // Original color
		           }
		       });

		       // Add action listener for Back button
		       backButton.addActionListener(new Station());
		       // Create a panel for the button
		       JPanel buttonPanel = new JPanel();
		       buttonPanel.setBackground(new Color(230, 240, 250)); // Light background
    buttonPanel.add(backButton);


	       // Add the scroll pane to the frame
	       frame.add(scrollPane, BorderLayout.CENTER);
	           frame.add(buttonPanel, BorderLayout.SOUTH); // Add Back button at the bottom


	       // Center and display the frame
	       frame.setLocationRelativeTo(null);
	       frame.setVisible(true);
}
//////////////////clean servic



static void show_cleantable() {
        // Create the main frame
        JFrame frame = new JFrame("Clean Table");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	       // Create a table model to hold data
	       String[] columnNames = {"Index","Typre of vehical", "Price"};
	       DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

	       // Assuming price_c is a List<Integer>
	       for (int i = 0; i < price_C.size(); i++) {
	           tableModel.addRow(new Object[]{i + 1,vehical_t.get(i), price_C.get(i)}); // Access list with .get(i)
	       }

	       // Create a JTable with the model
	       JTable table = new JTable(tableModel);
	       table.setFont(new Font("Arial", Font.PLAIN, 14));
	       table.setRowHeight(25);
	       table.setFillsViewportHeight(true);

	       // Add the table to a scroll pane for better visualization
	       JScrollPane scrollPane = new JScrollPane(table);






 // Create a Back button
    JButton backButton = new JButton("Back");
    backButton.setFont(new Font("Arial", Font.BOLD, 14));
    backButton.setBackground(new Color(70, 130, 180)); // Steel Blue
    backButton.setForeground(Color.WHITE); // White text
    backButton.setFocusPainted(false);
    backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

    // Add hover effect for the button
    backButton.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            backButton.setBackground(new Color(100, 149, 237)); // Lighter Blue
        }

        @Override
        public void mouseExited(MouseEvent e) {
            backButton.setBackground(new Color(70, 130, 180)); // Original color
        }
    });

    // Add action listener for Back button
    backButton.addActionListener(new Station());
    // Create a panel for the button
    JPanel buttonPanel = new JPanel();
    buttonPanel.setBackground(new Color(230, 240, 250)); // Light background
    buttonPanel.add(backButton);










	       // Add the scroll pane to the frame
	       frame.add(scrollPane, BorderLayout.CENTER);
    frame.add(buttonPanel, BorderLayout.SOUTH); // Add Back button at the bottom

	       // Center and display the frame
	       frame.setLocationRelativeTo(null);
	       frame.setVisible(true);
}

///

static void Repairtable() {
    // Create the main frame
    JFrame frame = new JFrame("Repair Table");
    frame.setSize(400, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Create a table model to hold data
    String[] columnNames = {"Index", "Action", "Price"};
    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

    // Assuming price_M is a List<Integer>
    for (int i = 0; i < price_M.size(); i++) {
        tableModel.addRow(new Object[]{i + 1, mechan_name.get(i), price_M.get(i)}); // Access list with .get(i)
    }

    // Create a JTable with the model
    JTable table = new JTable(tableModel);
    table.setFont(new Font("Arial", Font.PLAIN, 14));
    table.setRowHeight(25);
    table.setFillsViewportHeight(true);

    // Add the table to a scroll pane for better visualization
    JScrollPane scrollPane = new JScrollPane(table);

    // Create a Back button
    JButton backButton = new JButton("Back");
    backButton.setFont(new Font("Arial", Font.BOLD, 14));
    backButton.setBackground(new Color(70, 130, 180)); // Steel Blue
    backButton.setForeground(Color.WHITE); // White text
    backButton.setFocusPainted(false);
    backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

    // Add hover effect for the button
    backButton.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            backButton.setBackground(new Color(100, 149, 237)); // Lighter Blue
        }

        @Override
        public void mouseExited(MouseEvent e) {
            backButton.setBackground(new Color(70, 130, 180)); // Original color
        }
    });

    // Add action listener for Back button
    backButton.addActionListener(new Station());
    // Create a panel for the button
    JPanel buttonPanel = new JPanel();
    buttonPanel.setBackground(new Color(230, 240, 250)); // Light background
    buttonPanel.add(backButton);

    // Add components to the frame
    frame.add(scrollPane, BorderLayout.CENTER);
    frame.add(buttonPanel, BorderLayout.SOUTH); // Add Back button at the bottom

    // Center and display the frame
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
}

static void Electricity_screen(){
	  JFrame frame_E= new JFrame("Electricity screen");
	 		        frame_E.setSize(500, 400);
		        frame_E.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        JPanel panel_E= new JPanel(new GridLayout(5,1,15,15));
 panel_E.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding for the panel
 		           panel_E.setBackground(new Color(230, 240, 250)); // Soft background color for the panel

		    JLabel label_E = new JLabel("Choose the action, please:", JLabel.CENTER);
        label_E.setFont(new Font("Arial", Font.BOLD, 18));
        label_E.setForeground(new Color(50, 50, 150)); // Dark blue text   JLabel label_v=new JLabel("Choose your vehicle please :",JLabel.LEFT);


        JButton[] button_R = new JButton[3];
		   for (int i=0;i<3;i++)
		   {button_R[i]= new JButton(label_R2[i]);
		   panel_E.add( button_R[i]);
		     button_R[i].addActionListener(new Station());
// Add default styles to buttons
        button_R[i].setBackground(new Color(70, 130, 180)); // Steel Blue color
        button_R[i].setForeground(Color.WHITE); // White text
        button_R[i].setFocusPainted(false); // Remove focus border
        button_R[i].setFont(new Font("Arial", Font.BOLD, 14)); // Bold font
        button_R[i].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding inside button
  button_R[i].addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                JButton button = (JButton) e.getSource();
                button.setBackground(new Color(100, 149, 237)); // Light Blue on hover
            }
            public void mouseExited(MouseEvent e) {
			                JButton button = (JButton) e.getSource();
			                button.setBackground(new Color(70, 130, 180)); // Reset to Steel Blue
			            }
        });


 //Menu button with icon and smooth hover effect
        JButton button_menu = new JButton("Menu");
        button_menu.setFont(new Font("Arial", Font.BOLD, 16));
        button_menu.setIcon(new ImageIcon("menu-icon.png")); // Add an icon to the button
        button_menu.setBackground(new Color(70, 130, 180));
        button_menu.setForeground(Color.WHITE);
        button_menu.setFocusPainted(false);
        button_menu.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Button Hover Effect
        button_menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button_menu.setBackground(new Color(100, 149, 237)); // Lighter blue
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button_menu.setBackground(new Color(70, 130, 180)); // Original color
            }
        });
       button_menu.addActionListener(new Station());

frame_E.add(button_menu, BorderLayout.SOUTH);



             panel_E.add(button_R[i]);
			    }

			    frame_E.add(label_E, BorderLayout.NORTH);
			    frame_E.add(panel_E, BorderLayout.CENTER);
			    frame_E.setLocationRelativeTo(null);
			    frame_E.setVisible(true);
}


    public void itemStateChanged(ItemEvent e) {

		 String selectedItem;
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedItem = (String) e.getItem();
                    System.out.println("You selected: " + selectedItem);
                    oil_info.add(selectedItem);

                }
            }


 public void actionPerformed(ActionEvent e) {
       String command = e.getActionCommand();

switch(command){
	case "Login":
	  handleLogin(e);
	  break;




	  case "Click to Charge":
	    handleService(command, e);
	    break;


	case "Discount":
		double Dis=handleprice(command,  e);

	  JOptionPane.showMessageDialog(null, "You  got a discount  "+Dis+" Qirsh", "Services", JOptionPane.INFORMATION_MESSAGE);

	  break;
	  case "OK":


 JFrame infoFrame = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
infoFrame.dispose();

Information_Screen();
		break;



 case "Service Charge":
  JFrame cr_Frame1 = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
		 	                cr_Frame1.dispose();
show_servicetable();
break;


///1
 case "Service Clean":
  JFrame cr_Framecc = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
		 	                cr_Framecc.dispose();
show_cleantable();
break;


case "Service Repair":
  JFrame cr_Framecc1 = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
		 	                cr_Framecc1.dispose();
Repairtable();
break;


 case "Small car":
  JFrame c_Frame1 = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
		 	                c_Frame1.dispose();
screen_price_clean(command);
break;
 case "Click to Clean":
  JFrame c_Frame2 = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
		 	                c_Frame2.dispose();
Cleaning_Screen();
break;

 case "Large car":
  JFrame c_Frame3 = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
		 	                c_Frame3.dispose();
screen_price_clean(command);
break;

 case "Large bus":
  JFrame c_Frame4 = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
		 	                c_Frame4.dispose();
screen_price_clean(command);
break;

case "Small bus":
 JFrame c_Frame5 = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
		 	                c_Frame5.dispose();
screen_price_clean(command);
break;

case "Mechanisms":
 JFrame c_Frame6 = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
		 	                c_Frame6.dispose();
screen_price_clean(command);
break;

		  case "Menu":
		  JFrame b_Frame = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
		 	                b_Frame.dispose();
		 	                Servic_Screen()	;
		 	                break;


case "Cancel":
		  JFrame c_Frame = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
		 	                c_Frame.dispose();
		 	                Servic_Screen()	;
		 	                break;




case "Agree":
		  JFrame A_Frame = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
		 	                A_Frame.dispose();
		 	                 showCleanedInfoScreen(command);
		 	                break;

		 	                case "Click to Repair":
									  JFrame R_Frame = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
									 	                R_Frame.dispose();
									 	              Repair_screen();
		 	                break;

		 	                  case "Click to Services":
																  JFrame RS_Frame = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
																 	                RS_Frame.dispose();
																 	              Service_screen();
		 	                break;



		 	                case "Oil change":
									  JFrame R_Frameo = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
									 	                R_Frameo.dispose();
                        oil_car();
                        break;

///////////
     case "Back":
									  JFrame Rb_Frameo = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
									 	                Rb_Frameo.dispose();
                      Service_screen();
                        break;


//////////
 case "Agreed":
									  JFrame ag_Frameo = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
									 	                ag_Frameo.dispose();
                        flambagreed();
                         price_M.add(flamb);
                       mechan_name.add("Mechanical  change");

                        break;


 case "agreed":
									  JFrame aga_Frameo = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
									 	                aga_Frameo.dispose();
                        blambagreed();

                        price_M.add(flambb );
                       mechan_name.add("Mechanical  change");
                        break;

                         case "done":
				 JFrame aga_Frameo1 = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
															 	                aga_Frameo1.dispose();
done();


price_M.add(Spark );
   mechan_name.add("Mechanical  change");

break;

                        	case "Not agreed":
                        	  JFrame notag_Frameo = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
									 	                notag_Frameo.dispose();
									 	                Electricity_screen();

							  break;


/////////
                          case "Electricity":
									  JFrame EE_Frameo = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
									 	                EE_Frameo.dispose();
                        Electricity_screen();
                        break;

                        case "Done":
					JFrame d_Frameo = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
					   d_Frameo.dispose();
//////////////////////////////////one two


                        oil_screen_info();

                        price_M.add(price_oil );
                       mechan_name.add("Oil change");
                        for(Double  i:price_M ){
System.out.println( i);}

                        break;
                             case "Submit":
						 JFrame s_Frameo = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
								 s_Frameo.dispose();


						      String input = textField.getText();

			    showoil_price(input); // Show the price in a new window


                        break;



                            case "Front lamb":
											JFrame f_Frameo = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
											   f_Frameo.dispose();

						                        flamp_price_screen();
                        break;


                          case "Back Lamb":
																	JFrame f11_Frameo = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
																	   f11_Frameo.dispose();

												                        blamp_price_screen();
                        break;

                          case "Car Spark Plugs":
																							JFrame f12_Frameo = (JFrame) SwingUtilities.getWindowAncestor((JButton) e.getSource());
																							   f12_Frameo.dispose();

																		                        Spark_screen();
                        break;





	}


 	                }



    public static void main(String[] args) {
        // Call the login screen
        Login_screen();
    }
}
