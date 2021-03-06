/**
 * Show the contents of an array as a histogram, using Swing.
 *
 * @author Sean Ho
 * @studentid 314159
 * @course CMPT 167
 */

// any imports go here
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.text.*;

public class Histogram extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	double[] counts;         // The number of data values that fall in i-th bin.
    double[] heights;        // The height, for a relative histogram.
    double delta;            // Width of each bin.
    int numIntervals;        // Number of bins.
    double left, right;      // Left and right ends of interval. All other values are discarded.
    int numSamples;          // # data points added.

    // To prettify.
    DecimalFormat df = new DecimalFormat();

    // GUI stuff.
    int inset=60;            // Inset of axes and bounding box.


    public Histogram (double left, double right, int numIntervals)
    {
        this.left = left;
        this.right = right;
        this.numIntervals = numIntervals;
        delta = (right-left) / numIntervals;
        counts = new double [numIntervals];
        heights = new double [numIntervals];
	numSamples = 0;
	df.setMaximumFractionDigits (4);
    }


    public void add (double x)
    {
	numSamples ++;
	int bin = (int) ((x-left) / delta);
        if ( (bin >= 0) && (bin < numIntervals) ) {
            counts[bin] ++;
        }
        else {
            // Discard.
        }
    }


    void computeHeights ()
    {
        for (int i=0; i<numIntervals; i++) {
            heights[i] = (counts[i] / numSamples);
        }
    }
    

    public String toString ()
    {
        computeHeights ();
	String str = "Proportional histogram: \n";
        for (int i=0; i<numIntervals; i++) {
            double a = left + i*delta;
            double b = a + delta;
            str += "[" + df.format(a) + "," + df.format(b) + "]: " + heights[i] + "\n";
        }
        return str;
    }


    public void display ()
    {
        computeHeights ();
        // Make a frame and add this instance (as JPanel).
        JFrame frame = new JFrame ();
        frame.setSize (600, 480);
        frame.getContentPane().add (this);
        frame.addWindowListener (new WindowAdapter () {
                public void windowClosing (WindowEvent e) 
                {
                    System.exit(0);
                }
            });
        frame.setVisible (true);
    }
    

    public void paintComponent (Graphics g)
    {
        super.paintComponent (g);

        // Background.
        Dimension D = this.getSize ();
        g.setColor (Color.white);
        g.fillRect (0,0, D.width, D.height);
        g.setColor (Color.black);


        // Axes, bounding box.
        g.drawLine (inset,D.height-inset, D.width-inset, D.height-inset);
        g.drawLine (inset,inset, inset,D.height-inset);
        g.drawLine (D.width-inset,inset, D.width-inset,D.height-inset);
        g.drawLine (inset,inset, D.width-inset, inset);

        // X-ticks and labels.
        int modVal = numIntervals / 5;
        for (int i=1; i<=numIntervals; i++) {
            double xTickd = i*delta;
            int xTick = (int) ( xTickd/(right-left) * (D.width-2*inset));
            g.drawLine (inset+xTick,D.height-inset-5, inset+xTick,D.height-inset+5);
            double x = left + i*delta;
            if (numIntervals <= 10) {
                g.drawString (df.format(x), xTick+inset-5, D.height-inset+20);
            }
            else if (i % modVal == 0) {
                // If there are too many values, write out only 5.
                g.drawString (df.format(x), xTick+inset-5, D.height-inset+20);
            }
            
        }

        // Y-ticks
        // First, find max.
        double maxY = Double.MIN_VALUE;
        for (int i=0; i<numIntervals; i++) {
            if (heights[i] > maxY) {
                maxY = heights[i];
            }
        }
        // We'll make only 10 ticks.
        int numYTicks = 10;
        double yDelta = maxY / numYTicks;
        for (int i=0; i<numYTicks; i++) {
            int yTick = (i+1) * (int) ((D.height-2*inset) / (double)numYTicks);
            g.drawLine (inset-5, D.height-yTick-inset, inset+5, D.height-yTick-inset);
            double yValue = (i+1)  * yDelta;
            g.drawString (df.format(yValue), 1, D.height-yTick-inset);
        }

        // Finally, draw the histogram.
        g.setColor (Color.blue);
        for (int i=0; i<numIntervals; i++) {
            int topLeftY = (int) ((heights[i] / maxY) * (D.height-2.0*inset));
            double x1 = i*delta;
            int topLeftX = (int) ( x1/(right-left) * (D.width-2*inset));
            double x2 = (i+1)*delta;
            int endX = (int) ( x2/(right-left) * (D.width-2*inset));
            int barWidth = endX - topLeftX - 10;
            g.fillRect (inset+topLeftX+5, D.height-topLeftY-inset, barWidth + 5, topLeftY + 5);
        }
        
    }


   /**
    * Set up the window and pane.
    */
   private static void createAndShowGUI() {

	   Histogram panel = new Histogram(0, 100, 5);		// content pane
     panel.display();
   }

   /**
    * Schedule a job for the event-dispatching thread:
    * creating and showing this application's GUI.
    * @param args Command-line arguments
    */
   public static void main( String args[] ) {
     // This is the thread-safe way to initialize the GUI
     Runnable doRun = new Runnable() {
       public void run() { createAndShowGUI(); }
     };
     javax.swing.SwingUtilities.invokeLater( doRun );
   }
}
