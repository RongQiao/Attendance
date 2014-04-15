package UserInterface;

import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public abstract class AttdFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JMenuBar menuBar;
	public JMenu[] menu;
	public JPanel mainPanel;

	public abstract void display();
	public void initFrame() {
		//frame
		setSize(800, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));
		getContentPane().add(mainPanel);
		initMenu();
	}
	
	public void initMenu() {
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		String[] menuStr = {"Course", "Student", "Enrollment", "Attendance", "show", "report"};
		menu = new JMenu[menuStr.length];
		for (int i = 0; i < menuStr.length; i++) {
			menu[i] = new JMenu(menuStr[i]);
			menuBar.add(menu[i]);
			menu[i].addMenuListener(new AttdMenuListener());
		}
		//for attendance menu
		/*JMenu attdMenu = menu[3];
		JMenuItem menuItem = new JMenuItem("Edit Attendance Record", KeyEvent.VK_A);
		attdMenu.add(menuItem);
		menuItem = new JMenuItem("Show Attendance Record", KeyEvent.VK_S);
		attdMenu.add(menuItem);*/
	}
	
	public void menuAction(JMenu selectMenu) {
		MainFrame mainFrame = MainFrame.getInstance();
		AttdFrame workingFrame = mainFrame.getCurrentFrame();
		workingFrame.setVisible(false);
		for (int i = 0; i < menu.length; i++) {
			if (menu[i].equals(selectMenu)) {
				switch(i) {
				case 0:
					mainFrame.setCurrentFrameEditCourse();
					break;
				case 1:
					mainFrame.setCurrentFrameEditStudent();
					break;
				case 2:
					mainFrame.setCurrentFrameCourseEnroll();
					break;
				case 3:
					mainFrame.setCurrentFrameAttendance();
					break;
				case 4:
					mainFrame.setCurrentFrameShowClassAR();
					break;
				case 5:
					mainFrame.setCurrentFrameShowClassRP();
					break;
				default: 
					break;
				}
				break;	//exit loop
			}
		}
		
		workingFrame = mainFrame.getCurrentFrame();
		workingFrame.display();		
	}
	
	class AttdMenuListener implements MenuListener {

		@Override
		public void menuCanceled(MenuEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void menuDeselected(MenuEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void menuSelected(MenuEvent e) {
			JMenu selectMenu = (JMenu)e.getSource();
			menuAction(selectMenu);
		}
		
	}
}
