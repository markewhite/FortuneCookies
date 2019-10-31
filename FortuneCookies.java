package fortuneCookies;




//package rightSize;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;


/*import cap.CapDoc;
import cluster.ClusterDoc;
import sRS.SRSDoc;
import tutorials.TutDoc;
*/


//TODO Move sRS view stuff to bases

//TODO Add view stuff to cluster



/**
 *
 *  @author  Mark White <mark@markewhite.com> <markewhite@icloud.com>
 *  @version 30.2
 *  <p
 *Originally written in around 1990, RightSize was written in Microsoft C for MSDOS. I updated
 * it to Microsoft Visual C++ when this became available. In the early 2000's, I rewrote it
 * in Java for maximum portability.
 *  <p>
 *
 * This class contains the main class of RightSize, a teaching tool for survey statistics for
 * Field Epidemiology Training Program fellows.
 * </p>
 *
 * <p>
 * RightSize is the main user interface class. It extends JFrame and contains a JDesktopPane that contains the
 * main UI, including a menu, about, and tutorials. The menu calls the appropriate classes to gather user
 * information, calculate results, and display them graphically.
 *</p>
 *
 *<p>
 *     It uses the document-view model, where the document contains data and does calculations and the view deals
 *     with the user and shows results.
 *</p>
 *
 */
public class FortuneCookies extends JFrame implements ActionListener
{
    static final String signature = "Mark White\nphone 001-404-735-7547\nmark@markewhite.com";
    private static ArrayList<String> cookieList = new ArrayList<String>();
    String line;
    static String cookie;
    private static final long serialVersionUID = 1L;
    private JDesktopPane desktop = null;
/*    private sRS.SRSDoc SRSDoc =null;
    private ClusterDoc clusterDoc = null;
    private CapDoc capDoc= null;
    private TutDoc tutDoc = null;
*/    //private AboutMenu tutDoc = null;

    /*private RSChartDoc chartDoc = null;
2014*/
    public FortuneCookies()
    {
        super("Dedicated to Dionisio Herrera, visionary leader of TEPHINET.");



        // Make the big window be indented 50 pixels from each edge
        // of the screen.
        int inset = 50;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset, screenSize.width - inset * 2,
                screenSize.height - inset * 2);

        // Set up the GUI.
        ////     desktop = new JDesktopPane(); // a specialized layered pane
        JPanel panel = new JPanel();
        // createFrame(); //create first "window"
        setContentPane(panel);
///			SRSDoc = new SRSDoc(desktop);
        setJMenuBar(createMenuBar());

        // Make dragging a little faster but perhaps uglier.
////        desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);

        Charset charset=Charset.forName("UTF-8");
        Path path = Paths.get("/Users/mark/Desktop/Java/FortuneCookies/src/fortuneCookies/FortuneCoookies.txt");
    String line = "";
        // Create cookieList
        try (
                BufferedReader reader=Files.newBufferedReader(path, charset))
        {

            int numLines = 0;

            while ((line = reader.readLine()) != null)
            {
                //String wline=reader.readLine();
                if (!line.isEmpty())
                {
                    cookieList.add(line);
                    //    System.out.println(line);
                    numLines+=1;
                }
            }



            //   System.out.println("numLines is " + numLines + '\n');

            cookie = getRandomLine();
        }

        catch (
                IOException e)
        {
            System.err.println(e);
        }

    }




    protected JMenuBar createMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();

        // Set up the lone menu.
        JMenu menu = new JMenu("File");
        //menu.setMnemonic(KeyEvent.VK_D);
        menuBar.add(menu);

        // Set up the first menu item.
        /*
         * JMenuItem menuItem = new JMenuItem("New");
         * menuItem.setMnemonic(KeyEvent.VK_N);
         * menuItem.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_N,
         * ActionEvent.ALT_MASK)); menuItem.setActionCommand("new");
         * menuItem.addActionListener(this); menu.add(menuItem);
         */
        // Set up the second menu item.

      //  KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_C, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask
        //  ());
      //  fileExitMenuItem.setMnemonic(KeyEvent.stroke);

        JMenuItem fileExitMenuItem = new JMenuItem("Exit");
        fileExitMenuItem = new JMenuItem("Quit  ⌘Q");
        fileExitMenuItem.setMnemonic(KeyEvent.VK_Q);
      //  fileExitMenuItem.setAccelerator(KeyStroke.getKeyStroke(
        //⌘        KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        fileExitMenuItem.setActionCommand("quit");
        fileExitMenuItem.addActionListener(this);
        menu.add(fileExitMenuItem);


     /*   fileExitMenuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        fileExitMenuItem.setActionCommand("quit");
        fileExitMenuItem.addActionListener(this);
        menu.add(fileExitMenuItem);

      */

        JMenu sampleSizeMenu = new JMenu("Calculate Sample Sizes");
        menuBar.add(sampleSizeMenu);

        JMenuItem srsMI = new JMenuItem("Simple Random Sample");
        srsMI.setActionCommand("srs");
        srsMI.addActionListener(this);
        sampleSizeMenu.add(srsMI);

        JMenuItem clusterMI = new JMenuItem("Cluster Sample");
        clusterMI.setActionCommand("cluster");
        clusterMI.addActionListener(this);
        sampleSizeMenu.add(clusterMI);

        JMenuItem capMI = new JMenuItem("Capture-Recapture Calculation");
        capMI.setActionCommand("cap");
        capMI.addActionListener(this);

        sampleSizeMenu.add(capMI);

        JMenu aboutMenu = new JMenu("About This Program");
        menuBar.add(aboutMenu);

        JMenuItem aboutMI = new JMenuItem("About This Program");
        aboutMI.setActionCommand("aboutProgram");
        aboutMI.addActionListener(this);
        aboutMenu.add(aboutMI);

        JMenuItem supportersMI = new JMenuItem("Supporters and Contributors");
        supportersMI.setActionCommand("supporters");
        supportersMI.addActionListener(this);
        aboutMenu.add(supportersMI);

        JMenuItem tutorialsMI = new JMenuItem("Tutorials");
        tutorialsMI.setActionCommand("tutorials");
        tutorialsMI.addActionListener(this);
        menuBar.add(tutorialsMI);

        //menu.setMnemonic(KeyEvent.VK_D);
        //menuBar.add(aboutMenu);

        return menuBar;
    }



    // React to menu selections.
    public void actionPerformed(ActionEvent e)
    {

        if ("cap".equals(e.getActionCommand()))
        {
//            capDoc = new CapDoc(desktop);
            // D.b("RightSize: Reached RS menu CapDoc.");
        }
        else if ("quit".equals(e.getActionCommand()))
        {
            quit();
        } else if ("cluster".equals(e.getActionCommand()))
        {
            //           clusterDoc = new ClusterDoc(desktop);
        } else if ("srs".equals(e.getActionCommand()))
        {
            //       SRSDoc = new SRSDoc(desktop);

        }
        else if ("tutorials".equals(e.getActionCommand()))
        {
            //           tutDoc = new TutDoc(desktop);
        }
        else if ("srs".equals(e.getActionCommand()))
        {
            //           SRSDoc = new SRSDoc(desktop);

        }
        else if ("aboutProgram".equals(e.getActionCommand()))
        {
            String text = "This program is dedicated to Dionisio Herrera, TEPHINET director for many years—and all " +
                    "the FETP trainees and staff, present and past who need to" +
                    " learn to " +
                    "do accurate surveys. I hope this helps you learn to design\nfine surveys that help you improve the health of your population.\n\nWritten by Mark White";

            JOptionPane.showMessageDialog(null, text, "Rightsize 30.2 is not finished as of July 1, 2019.", JOptionPane
                    .PLAIN_MESSAGE, null);


        } else if ("supporters".equals(e.getActionCommand()))
        {
            String text =
                    "This program is dedicated to Dionisio Herrera, TEPHINET director for many years. RightSize is brought to you by TEPHINET, which provided moral and " +
                            "financial support. Many FETPs and their trainees provided input and provided helpful suggestions and advice. WHO and the US CDC provided technical assistance.\n\n" +
                            "Many people contributed thier expertise and pateince to testing and helping design the " +
                            "screens, especially Conchy Roces, Conky Lim-Quizon, and Vikki Delosreyes." +
                            "\n\nI'd also like to acknowledge the help of Li Vu Anh and the Vietnamese " +
                            "trainee who calculated along with the my presentation program and found an error.\n\n" +
                            "Thanks to Dr. Guang Zeng and the China FETP trainees who asked to be able to calculate " +
                            "with large populations. They will be pleased to know the program can handle populations of more than 10 to " +
                            "The 80th power. That's the estimated number of atoms in the universe.\n\n" +
                            "You can enter very large numbers in scientific format, such as 10.80e\n\n They also " +
                            "asked for Capture-Recapture surveys and they are included in this version.\n\n "+

                            "The Philippine FETP requested Lot Quality Assurance Surveys(LQAS) will be " +
                            "included in the next draft. Thanks to Peter Smith of the London School of " +
                            "Hygiene and Tropical Medicine, who derived the formulat for LQAS surveys" +
                            "from the binomial curve on the blackboard during my class at Makerere " +
                            "School of Public Health in Kampala, Uganda\n\n" +

                            "Speical thanks for my old mentor, Andy Dean, who inspired me to write programs back in " +
                            "the 1970s.";


            JOptionPane.showMessageDialog(null, text, "Rightsize 30.2 is not finished as of July 1, 2019.", JOptionPane
                    .PLAIN_MESSAGE, null);
        }
    }


    // Quit the application.
    protected void quit()
    {
        System.exit(0);

    }

    /**
     * reads a file, counts the lines, and selects a line at random. If the line is blank, it chooses a new random
     * number until a populated line is found.
     *
     * Pseudocode:
     * Read file, load lines of text to StringArrayList, choose one at random, then pass to dialog. If user chooses to
     * use the line, put slug of address, then copy this and line to system. User then presses cmd-v to insert signature
     * to mail file.
     *
     */





     static String getRandomLine()
     {
     Random rn = new Random();
     int lineNumber = rn.nextInt(cookieList.size());




     return cookieList.get(lineNumber);
     }





    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event-dispatching thread.
     */
    private static void createAndShowGUI()
    {
        // Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Create and set up the window.
        FortuneCookies frame = new FortuneCookies();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Display the window.
        frame.setVisible(true);


    }

    public static void main(String[] args)
    {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run()
            {
                createAndShowGUI();
            }
        });

    }

}






/**
 *


import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*import java.awt.Component;
import javax.swing.*;
import javax.swing.JMenuBar;
import java.awt.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
*/

/**
 * Selects a random quote from a text file and copies it to the system clipboard.
 * Class must be static so orther classes in the project can refer to teh string
 * containing the signature (address and phone number) and the member getRandomLine().
 *
 */

/*public class FortuneCookies
{
    static final String signature = "Mark White\nphone 001-404-735-7547\nmark@markewhite.com";
    static private List<String> cookieList = new ArrayList<String>();
    String line;
    static String cookie;

    static JMenuBar menuBar = new JMenuBar();


    FortuneCookies()
    {

      // JFrame frame = new (JFrame) SwingUtilities.getRoot();












/*





        public static void main (String[]args)
        {

            FortuneCookies fortuneCookies = new FortuneCookies();


            fortuneCookies.FCDialog dialog = new fortuneCookies.FCDialog(signature, cookie);
            dialog.pack();
            dialog.setVisible(true);

            System.exit(0);

        }
    }
    ***/






