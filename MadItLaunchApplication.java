import javax.swing.JFrame;

/**
 * This class simply launches the program by creating
 * an instance of the GUI and validates it. Also it
 * makes sure that when the GUI window of the application
 * is closed, that the application is actually closed by
 * setting the Default Close Operation to EXIT_ON_CLOSE
 *
 * @author mwalsh, aryan, dguinney
 *
 */

public class MadItLaunchApplication {
	
	public static void main(String[]args){
		MadItGui MadIt = new MadItGui();
		MadIt.validate();
		MadIt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
