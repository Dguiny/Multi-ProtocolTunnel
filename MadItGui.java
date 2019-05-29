import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MadItGui extends JFrame implements ActionListener{
	
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	boolean listening = false;//Shows if a listening port is open NB NOT currently in use
	String toolSelect = "";

/**
 * Declaration of all the JPannels that are being used.
 * The center panel is set to Borderlayout and the panels
 * cpnPanel (Center Panel North) and cpcPanel (Center Panel Center)
 * are added.
 */
	JPanel northPanel,centerPanel,infoPanel,cpnPanel,cpcPanel;
/************ JLabels ***********/

	JLabel mainLogo;	
	JLabel lblusername;
	JLabel lblpassword;
//	JLabel status;// This will probably be implemented at a later date. Running/Not Running

/************* JTextFields ********/
	
	static JTextField tfusername;
	static JPasswordField pfpassword;

/************* JButtons **********/
	
	JButton launch;
	JButton instructions;
//	JButton login; //Depreciated button replaced with "Launch" Button

/**************** misc **************/

	ImageIcon MadItLogo;
	JComboBox toolSelection;
	
/************************************/
	public MadItGui(){
		setTitle("MadIT - Tunnelling Tool");
		setSize(325, 390);
		setLocation(350, 150);
		setVisible(true);
/************ Creating Instances of all Objects ********************/

		northPanel = new JPanel();
		centerPanel = new JPanel(new BorderLayout());
			cpnPanel = new JPanel();
			cpcPanel = new JPanel();
		infoPanel = new JPanel();

/*******************************************************************/
		
		MadItLogo = new ImageIcon(getClass().getResource("images/mptLogo.png"));
		
		//MadItLogo = new ImageIcon("images/mptLogo.png");
		
		mainLogo = new JLabel();
			mainLogo.setIcon(MadItLogo);

/*******************************************************************/

		String[] tools = {"- Select Tool -","netcat","cryptcat","dns2tcp","iodine",};
		
		toolSelection = new JComboBox(tools);
			toolSelection.addActionListener(this);

/*******************************************************************/
			
		lblusername = new JLabel("Username");
		lblpassword = new JLabel("Password ");
		
		tfusername = new JTextField(16);
			tfusername.setEditable(false);
			tfusername.setText("");
		pfpassword = new JPasswordField(16);
			pfpassword.setEditable(false);
			pfpassword.setText("");

/*******************************************************************/
//		May be implemented at a later date
			
//		status = new JLabel("Not Listening");
//		//status.setForeground(Color.red);
//		status.setBackground(Color.red);
//		status.setOpaque(true);

		launch = new JButton("Launch");
		launch.setEnabled(false);
		launch.addActionListener(this);
		
		instructions = new JButton("Instructions");
		instructions.setEnabled(false);
		instructions.addActionListener(this);

/***************** Adding Objects to Appropriate Panels ******************************/
		
		northPanel.add(mainLogo);
		
		cpnPanel.add(toolSelection);
		
		cpcPanel.add(lblusername);
		cpcPanel.add(tfusername);
		cpcPanel.add(lblpassword);
		cpcPanel.add(pfpassword);
		
		cpcPanel.add(launch);
//		cpcPanel.add(login);
		infoPanel.add(instructions);

/*********************************************************/
		
		centerPanel.add(cpnPanel,BorderLayout.NORTH);
		centerPanel.add(cpcPanel,BorderLayout.CENTER);

/*********************************************************/		
		/** Add Panels to JFrame **/
		
		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(infoPanel, BorderLayout.SOUTH);
	}
	/**
	 *
	 */
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
	
	/** This Makes sure that the 'toolSelect' String value, will always
	 *  be assigned the value of the tool that is currently selected in
	 *  the combo-box.
	 *  
	 *  The if statement, stops it from trying to update if the launch
	 *  button is pressed and therefore, no update is needed. Also with-
	 *  out it, it ran into some errors.
	 */
		
		if(e.getSource() != launch && e.getSource() != instructions){
			JComboBox cb = (JComboBox) e.getSource();
			toolSelect = (String)cb.getSelectedItem();	
		}
	/** The Following block of if/else if statements, dictate what happens
	 *  as you change what tool is selected. For example, if you haven't
	 *  selected a tool, then the launch button is disable and so are the
	 *  username and password fields
	 */
		if(toolSelect.equals("- Select Tool -")){
			tfusername.setText("");
			tfusername.setEditable(false);
			pfpassword.setText("");
			pfpassword.setEditable(false);
			launch.setEnabled(false);
			instructions.setEnabled(false);
		}
		else if(toolSelect.equals("dns2tcp")){
			tfusername.setEditable(true);
			pfpassword.setText("");
			pfpassword.setEditable(false);
			launch.setEnabled(true);
			instructions.setEnabled(true);
		}
		else if(toolSelect.equals("iodine")){
			tfusername.setEditable(true);
			pfpassword.setText("");
			pfpassword.setEditable(false);
			launch.setEnabled(true);
			instructions.setEnabled(true);
		}
		else if(toolSelect.equals("netcat")){
			tfusername.setText("");
			tfusername.setEditable(false);
			pfpassword.setText("");
			pfpassword.setEditable(false);
			launch.setEnabled(true);
			instructions.setEnabled(true);
		}
		else if(toolSelect.equals("cryptcat")){
			tfusername.setText("");
			tfusername.setEditable(false);
			pfpassword.setEditable(true);
			launch.setEnabled(true);
			instructions.setEnabled(true);
		}
/***************************************************************/
		
	/**
	 *  These if/else statements will call the appropriate function(s)
	 *  based on what tool is selected.
	 * 	
	 */
		if(e.getSource() == launch){
			if(toolSelect.equals("- Select Tool -")){
				JOptionPane.showMessageDialog(this, "You Must Select A Tool To Use!");
			}
			else if(toolSelect.equals("dns2tcp")){
				
				boolean valid = checkInput();//Boolean to check if all appropriate fields have been filled
				
				if(!valid){
					JOptionPane.showMessageDialog(this,
					        "You must enter a user name.",
					        "Error",
					        JOptionPane.ERROR_MESSAGE);
				}
				else{
					Run.dns2tcpOpenListeningPort();
					try {
					    Thread.sleep(1000);
					} catch(InterruptedException ex) {
					    Thread.currentThread().interrupt();
					}
					Run.dns2tcpSSHLogin(tfusername.getText());
				}
			}
			else if(toolSelect.equals("iodine")){
				boolean valid = checkInput();
				
				if(!valid){
					JOptionPane.showMessageDialog(this,
					        "You must enter a user name.",
					        "Error",
					        JOptionPane.ERROR_MESSAGE);
				}
				else{
					Run.iodineOpenListeningPort();
					try {
					    Thread.sleep(5000);
					} catch(InterruptedException ex) {
					    Thread.currentThread().interrupt();
					}
					Run.iodineSSHLogin(tfusername.getText());
				}
			}
			else if(toolSelect.equals("netcat")){
				Run.netcatOpenListeningPort();
			}
			else if(toolSelect.equals("cryptcat")){
				boolean valid = checkInput();
				
				if(!valid){
					JOptionPane.showMessageDialog(this,
					        "You must enter a password.",
					        "Error",
					        JOptionPane.ERROR_MESSAGE);
				}
				else if(valid){
					Run.cryptcatOpenListeningPort(pfpassword.getText());
				}
			}
/********************************************************************************/
		}	
		else if(e.getSource() == instructions){

				if(toolSelect.equals("dns2tcp")){
					Instructions.getDns2tcpInstructions();
				}
				else if(toolSelect.equals("iodine")){
					Instructions.getIodineInstructions();				
				}
				else if(toolSelect.equals("netcat")){
					Instructions.getNetcatInstructions();				
				}
				else if(toolSelect.equals("cryptcat")){
					Instructions.getCryptcatInstructions();				
				}
		}
	}
	@SuppressWarnings("deprecation")
	public static boolean checkInput(){
		
		if(tfusername.isEditable() && pfpassword.isEditable() && (tfusername.getText().equals("") || pfpassword.getText().equals(""))){	
			return false;
		}
		else if(tfusername.isEditable() && tfusername.getText().equals("")){
			return false;
		}
		else if(pfpassword.isEditable() && pfpassword.getText().equals("")){
			return false;
		}
		else{
			System.out.println(pfpassword.getText());
			return true;
		}
		
	}
}

This section of code is where the scripts we are using are called from and takes user input such as username or password and concatenates it to a string variable that can be used  to run the script.
Run.java

import java.io.IOException;


public class Run {
	/**
	 *
	 * @param uname
	 */
	public static void dns2tcpSSHLogin(String uname){
		
		String username = uname;
		// The sshLoginScript, builds the script that needs to be run, before running it.
		// This way is can be easily tested/edited, without having to run it first.
		
		String sshLoginScript = "xterm -e ssh "+username+"@localhost -p 8888 -D 8080";
	
//		System.out.println(sshLoginScript); //This was used for testing purposes to see what the output was.
		try {
			Runtime.getRuntime().exec(sshLoginScript);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * All the rest of these functions are done in mostly the same manner
	 */
	public static void dns2tcpOpenListeningPort(){

		String domain = "89.100.101.14";
		String domainScript = "dns2tcpc -z "+domain+" -l 8888 -r ssh "+domain;

//		System.out.println(domainScript);
		
		try {
			Runtime.getRuntime().exec(domainScript);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 *
	 */
	public static void iodineOpenListeningPort(){
		
		String domain = "madit.ie";
		String subDomain = "tunnel1";
		String domainScript = "iodine -f "+domain+" "+subDomain+"."+domain;

//		System.out.println(domainScript);
		
/*		try {
			Runtime.getRuntime().exec(domainScript);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
	}
	/**
	 *
	 * @param uname
	 */
	public static void iodineSSHLogin(String uname){
		
		String username = uname;
		String domain = "192.168.99.1";
		String port = "8080";
		
		String sshLoginScript = "xterm -e ssh -D "+port+" "+username+"@"+domain;
	
		System.out.println(sshLoginScript);
/*		try {
			Runtime.getRuntime().exec(sshLoginScript);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/	}
	/**
	 *
	 */
	public static void netcatOpenListeningPort(){
		
		String domain = "madit.ie";
		String domainScript = "nc "+domain+" -D 8080";

		System.out.println(domainScript);
		
/*		try {
			Runtime.getRuntime().exec(domainScript);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
	}
	/**
	 *
	 * @param uname
	 */
	public static void netcatSSHLogin(String uname){
		
		String username = uname;
		
		String sshLoginScript = "xterm -e ssh "+username+"@madit.ie -D 8080";
	
		System.out.println(sshLoginScript);
/*		try {
			Runtime.getRuntime().exec(sshLoginScript);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/	}
	/**
	 *
	 * @param psw
	 */
	public static void cryptcatOpenListeningPort(String psw){
		
		String domain = "madit.ie";
		String password = psw;
		String port = "8080";
		
		String domainScript = "xterm -e cryptcat "+domain+" "+port;

		System.out.println(domainScript);
		
/*		try {
			Runtime.getRuntime().exec(domainScript);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
	}
	/**
	 *
	 * @param uname
	 */
	public static void cryptcatSSHLogin(String uname){
	/*	
		String username = uname;
		
		String sshLoginScript = "INSERT cryptcat SSHLoginScript HERE";
	
		System.out.println(sshLoginScript);
/*		try {
			Runtime.getRuntime().exec(sshLoginScript);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/	}
	/**
	 *
	 */
	public static void ptunnelOpenListeningPort(){
		
		String domain = "madit.ie";
		String port = "8000";
		String domainScript = "ptunnel -p "+domain+" -lp "+port+" -da "+domain+" -dp 22";

		System.out.println(domainScript);
		
/*		try {
			Runtime.getRuntime().exec(domainScript);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
	}
	/**
  *
	 * @param uname
	 */
	public static void ptunnelSSHLogin(String uname){
		
		String username = uname;
		
		String sshLoginScript = "INSERT ptunnel SSHLoginScript HERE";
	
		System.out.println(sshLoginScript);
/*		try {
			Runtime.getRuntime().exec(sshLoginScript);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/	}
}
