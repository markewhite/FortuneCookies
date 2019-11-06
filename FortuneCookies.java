package fortuneCookies;


import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
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

/**
 * UI is adapted from the tried and true RightSize 30.2 program.
 * It's modified to contain the guts of the FortuneCookie program, which select a random quote from a file, appends
 * it to an address, and then copies the result to the System clipboard. The user pastes it at the appropriate place
 * in thier email text.
 *
 *  @author  Mark White <mark@markewhite.com> <markewhite@icloud.com>
 *  @version 1.3
 *  <p
 * fyi:
 */

public class FortuneCookies extends JFrame implements ActionListener
{
    final String signature = "Mark White\nphone 001-404-735-7547\nmark@markewhite.com";
    private static ArrayList<String> cookieList = new ArrayList<String>();
    String line;
    private static String currrentCookie;
    private static final long serialVersionUID = 1L;
    JPanel contentPane = new JPanel();
    static JPanel buttonPanel;
    static JTextPane textPane;


    /**
     * Constructor
     */
    public FortuneCookies()
    {
        super("Fortune Cookie Chooser");
        setLayout(new BorderLayout());
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());




        // Set the frame to be wide and take up the top third of screen.
        // of the screen.
        int inset = 100;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(inset, inset, screenSize.width/2 - inset,
                screenSize.height/3 - inset);



        setJMenuBar(createMenuBar());

        //Create text pane and add style
        textPane = new JTextPane(); // Make sure this is created before we write to it
        Font font = new Font("Arial", Font.PLAIN, 18);
        textPane.setFont(font);
    //    textPane.setText("Overwrite Me.");





        add(textPane, BorderLayout.NORTH);
        createButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);


        Charset charset=Charset.forName("UTF-8");
        Path path = Paths.get("/Users/mark/Desktop/Java/FortuneCookies/src/fortuneCookies/FortuneCoookies.txt");




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




            currrentCookie= getRandomLine(); //Put new aphorism into the active cookie where it can be copied to the System
            // clipboard as needed.
            textPane.setText(currrentCookie); // Put it in the textPanel and show.
        }

        catch (
                IOException e)
        {
            System.err.println(e);
        }

    }


    static String getRandomLine()
    {
        Random rn = new Random();
        int lineNumber = rn.nextInt(cookieList.size());




        return cookieList.get(lineNumber);
    }

    private void getCurrentCookie()
    {
        currrentCookie= getRandomLine(); //Put new aphorism into the active cookie where it can be copied to the System
        // clipboard as needed.
        textPane.setText(currrentCookie); // Put it in the textPanel and show.
    }


    private JPanel createButtonPanel()
    {
        // Create panel and assign to class-level reference
        buttonPanel = new JPanel();

        // Create and add buttons
        JButton buttonCopy = new JButton("Copy");

        buttonCopy.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onCopy();
            }
        });
        buttonPanel.add(buttonCopy);

        JButton buttonNext = new JButton("Next");

        buttonNext.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onNext();
            }
        });
        buttonPanel.add(buttonNext);

        JButton buttonQuit = new JButton("Quit");
         buttonQuit.addActionListener(new ActionListener()
         {
         public void actionPerformed(ActionEvent e)
         {
            onQuit();
         }
         });
        buttonPanel.add(buttonQuit);

        return buttonPanel;
    }




    protected JMenuBar createMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();

        // Set up the lone menu.
        JMenu fileMenu = new JMenu("File");
        //menu.setMnemonic(KeyEvent.VK_D);
        menuBar.add(fileMenu);

        // Set up the first menu item.

        JMenuItem fileExitMenuItem = new JMenuItem("Exit");
        fileExitMenuItem = new JMenuItem("Quit  ⌘Q");
        fileExitMenuItem.setMnemonic(KeyEvent.VK_Q);
        fileExitMenuItem.setActionCommand("quit");
        fileExitMenuItem.addActionListener(this);
        fileMenu.add(fileExitMenuItem);

        JMenuItem aboutMI = new JMenuItem("About This Program");
        aboutMI.setActionCommand("aboutProgram");
        aboutMI.addActionListener(this);
        fileMenu.add(aboutMI);

        return menuBar;
    }


    // React to menu selections.
    public void actionPerformed(ActionEvent e)
    {

        if ("quit".equals(e.getActionCommand()))
        {
            quit();
        }
        else if ("aboutProgram".equals(e.getActionCommand())) {
            String text="This program produces a signature and chooses a random quote appended below it. This " +
                    "string is added to the system clipboard. Users need only place the cursor on in the text of the " +
                    "email " + "and insert the signture." + "\n\nWritten by Mark White, mark@markewhite.com\n\n" +
                    "It is distributed under the GNU Public License. Code is on GitHub at https://github.com/markewhite/FortuneCookies";

            JOptionPane.showMessageDialog(null, text, "Fortune Cookie Chooser.", JOptionPane
                    .PLAIN_MESSAGE, null);


        }
    }


    // Quit the application.
    protected void quit()
    {
        System.exit(0);

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


    /*** Responses to Button Commands ***/

    private void onCopy()
    {
        String sigCookie=signature + "\n\n" + currrentCookie;

        // Copy to System Clipboard

        Clipboard cb=Toolkit.getDefaultToolkit().getSystemClipboard(); //Get access to System clipboard.

        StringSelection sigCookieSelection=new StringSelection(sigCookie); // Wrap current cookie for transport there.

        cb.setContents(sigCookieSelection, null); // copy to clipboard
    }


    private void onNext()
    {
        currrentCookie= getRandomLine(); //Put new aphorism into the active cookie where it can be copied to the System
        // clipboard as needed.
        textPane.setText(currrentCookie); // Put it in the textPanel and show.
    }

    private void onQuit()
    {
        System.exit(0);
    }

}


