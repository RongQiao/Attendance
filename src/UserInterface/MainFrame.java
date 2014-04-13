package UserInterface;

import javax.swing.SwingUtilities;

public class MainFrame {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//EditCourse frame = new EditCourse();
				EditStudent frame = new EditStudent();
				frame.display();
			}
		});
	}

}
