import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Instructions extends JFrame{
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1777211193730988500L;
	
	public Instructions(){
		
	}
	public static void getDns2tcpInstructions(){
		JOptionPane.showMessageDialog(null,
		        "1) Input the login name for the proxy server\n"
				+ "2) Click the 'Launch' button\n"
				+ "3) Wait for the SSH prompt for the password\n"
				+ "4) Input password\n"
				+ "5) Open Firefox and navigate to 'Edit', 'Preferences', 'Network','Settings'\n"
				+ "6) Set the SOCKS Host to 'localhost' port '8080'",
		        "Running dns2tcp",
		        JOptionPane.INFORMATION_MESSAGE);
		
		//System.out.println("dns2tcp");
	}
	public static void getIodineInstructions(){
		
		JOptionPane.showMessageDialog(null,
		        "1) Input the login name for the proxy server\n"
				+ "2) Click the 'Launch' button\n"
				+ "3) Wait for the SSH prompt for the password\n"
				+ "4) Input password\n"
				+ "5) Open Firefox and navigate to 'Edit', 'Preferences', 'Network','Settings'\n"
				+ "6) Set the SOCKS Host to 'localhost' port '8080'",
		        "Running iodine",
		        JOptionPane.INFORMATION_MESSAGE);
		
		//System.out.println("iodine");
	}
	public static void getNetcatInstructions(){
		JOptionPane.showMessageDialog(null,
		        "Click 'Launch' and you are good to go.",
		        "Running netcat",
		        JOptionPane.INFORMATION_MESSAGE);
		
		//System.out.println("netcat");
	}
	public static void getCryptcatInstructions(){
		JOptionPane.showMessageDialog(null,
		        "1) Input the cryptcat psk\n"
				+ "2) Click the 'Launch' button\n"
				+ "3) You are good to go!",
		        "Running cryptcat",
		        JOptionPane.INFORMATION_MESSAGE);
		//System.out.println("cryptcat");
	}
}
