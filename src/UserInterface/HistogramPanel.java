package UserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Logical.DomainReport.AttdCnt;

public class HistogramPanel extends JPanel{
	private List<AttdCnt> acList;
	// GUI stuff.
    int inset=60;            // Inset of axes and bounding box.

    int delta;            // Width of each bin.
    int numIntervals;        // Number of bins.
    int left, right;      // Left and right ends of interval. All other values are discarded.
    
    private boolean displayed;
    
    public HistogramPanel() {
    	acList = null;
    	displayed = false;
    }
    
    public HistogramPanel(List<AttdCnt> data) {
    	setData(data);
    	displayed = false;
	}    

	public void setData(List<AttdCnt> data) {
		acList = data;
    	if ((acList != null) && (acList.size()>0)) {
	    	numIntervals = acList.size();
    	}
	}
	
	public void paintComponent(Graphics g) {	
		super.paintComponent (g);

		if (acList == null) {
			return;
		}
    	left = 0;
    	right = this.getWidth();
    	delta = (right - left - inset*2) / numIntervals;   	

        // Background.
	    Dimension D = this.getSize ();
	    g.setColor (Color.white);
	    g.fillRect (0,0, D.width, D.height);
	    g.setColor (Color.black);

	    //description
	    g.setColor(Color.RED);
	    g.fillRect (inset, 10, 5, 5);
        g.drawString("expected number", inset + 10, 15);
        g.setColor(Color.BLUE);
	    g.fillRect (inset, 20, 5, 5);
        g.drawString("expected number", inset + 10, 25);
	    
        // Axes, bounding box.
	    //line bottom
        g.drawLine (inset-5,D.height-inset, D.width-inset, D.height-inset);
        g.drawString("days", D.width-inset, D.height-inset);
        //line left
        g.drawLine (inset-5,inset, inset-5,D.height-inset);
        g.drawString("students number", inset-5,inset-10);
        //line right
        //g.drawLine (D.width-inset,inset, D.width-inset,D.height-inset);
        //line top
        //g.drawLine (inset-5,inset, D.width-inset, inset);


        // X-ticks and labels.
        int i = 0;
        int maxY = 0;
        for (AttdCnt ac: acList) {
        	int xTick = i*delta;
        	int p1x = inset + xTick;
        	int p1y = D.height - inset - 5;
        	int p2x = p1x;
        	int p2y = p1y + 10;
            g.drawLine (p1x, p1y, p2x, p2y);  
            String dayStr = (new SimpleDateFormat("MM/dd/yy")).format(ac.getDate());
            g.drawString (dayStr, p2x, p2y+10);
            i++;
            //find max cnt
            if (ac.getExpectedNumbers() > maxY) {
            	maxY = ac.getExpectedNumbers();
            }
        }

        // Y-ticks
        // We'll make only 10 ticks.
        int numYTicks = (maxY > 10) ? 10 : maxY;
        int yDelta = maxY / numYTicks;
        int yDeltaHeight = (D.height-2*inset) / numYTicks;
        for (i=0; i<=numYTicks; i++) {
        	int yTick = (i) * (int) (yDeltaHeight);
        	int p1x = inset - 5;
        	int p1y = D.height - yTick - inset;
        	int p2x = p1x + 10;
        	int p2y = p1y;
            g.drawLine (p1x, p1y, p2x, p2y);
            g.drawString (Integer.toString(i * yDelta), p1x - 15, p1y);
        }

        // Finally, draw the histogram.
        i = 0;
        for (AttdCnt ac: acList) {
        	int num = ac.getAttdNumbers();
        	double heightTimes = (double)ac.getAttdNumbers()/(double)yDelta;
        	int barHeight = (int) (yDeltaHeight * heightTimes);
        	int x = delta * i + inset;
        	int y = D.height - inset - barHeight;
        	int yExpect = D.height - inset - maxY/yDelta*yDeltaHeight;
        	int barWidth = 10;
        	i++;
        	g.setColor(Color.RED);
        	g.fillRect (x, yExpect, barWidth, maxY/yDelta*yDeltaHeight);
            g.setColor (Color.blue);
            g.fillRect (x+barWidth, y, barWidth, barHeight);
            g.drawString(Integer.toString(num), x+barWidth*2, y);
        }        
	}

	public void display (String titile)
    {
		if (!displayed) {
			displayed = true;
	        // Make a frame and add this instance (as JPanel).
	        JFrame frame = new JFrame();
	        frame.setSize (800, 600);
	        frame.setTitle(titile);
	        frame.getContentPane().add (this);
	        frame.addWindowListener (new closeHistogram());
	        frame.setVisible (true);			
		}
    }
	
	public void unDisplay() {
		displayed = false;
	}
	
	class closeHistogram extends WindowAdapter {
		public void windowClosing (WindowEvent e) 
        {
			unDisplay();
        }
	}
	
	public static void main( String args[] ) {
	     // This is the thread-safe way to initialize the GUI
	     Runnable doRun = new Runnable() {
	       public void run() { 
	    	   List<AttdCnt> acList = new ArrayList<AttdCnt>();
	    	   acList.add(new AttdCnt(new Date(), 20, 19));
	    	   acList.add(new AttdCnt(new Date(), 20, 18));
	    	   HistogramPanel panel = new HistogramPanel(acList);		// content pane
	    	   panel.display("");
	       }
	     };
	     javax.swing.SwingUtilities.invokeLater( doRun );
   }

}
