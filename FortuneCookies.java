package fortuneCookies;




import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
public class FortuneCookies extends JFrame
{
    private String signature="Mark White\nphone 001-404-735-7547\nmark@markewhite.com";
    private static ArrayList<String> cookieList=new ArrayList<String>();
    String line;
    private static String currentCookie;
    private static final long serialVersionUID=1L;
    JPanel contentPane=new JPanel();

    static JPanel buttonPanel;
    static JTextPane textPane;
    static JMenuBar menuBar;
    public FortuneCookies()
    {
        super("Fortune Cookie Chooser");
        setLayout(new BorderLayout());
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());


        // Set the frame to be wide and take up the top third of screen.
        // of the screen.
        int inset=100;
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(inset, inset, screenSize.width / 2 - inset,
                screenSize.height / 3 - inset);


      menuBar = new FCMenu(this).createMenuBar();
      add(menuBar, BorderLayout.NORTH);


        //Create text pane and add style
        textPane=new JTextPane(); // Make sure this is created before we write to it
        Font font=new Font("Arial", Font.PLAIN, 18);
        textPane.setFont(font);
        //    textPane.setText("Overwrite Me.");

        // Create ButtonPanel
        FCButtons buttons=new FCButtons(this);
        JPanel buttonPanel=buttons.createButtonPanel();
     //   add(buttonPanel, BorderLayout.CENTER);


        add(textPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        Charset charset=Charset.forName("UTF-8");
        Path path=Paths.get("/Users/mark/Desktop/Java/FortuneCookies/src/fortuneCookies/FortuneCoookies.txt");


        // Create cookieList
        try (
                BufferedReader reader=Files.newBufferedReader(path, charset)) {

            int numLines=0;

            while ((line=reader.readLine()) != null) {
                //String wline=reader.readLine();
                if (!line.isEmpty()) {
                    cookieList.add(line);
                    //    System.out.println(line);
                    numLines+=1;
                }
            }


            currentCookie=getRandomLine(); //Put new aphorism into the active cookie where it can be copied to the System
            // clipboard as needed.
            textPane.setText(currentCookie); // Put it in the textPanel and show.
        }

        catch (
                IOException e) {
            System.err.println(e);
        }

    }


    static String getRandomLine()
    {
        Random rn=new Random();
        int lineNumber=rn.nextInt(cookieList.size());


        return cookieList.get(lineNumber);
    }
    private static void createAndShowGUI()
    {

        // Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Create and set up the window.
        FortuneCookies frame=new FortuneCookies();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Display the window.
        frame.setVisible(true);


    }

    public static void main(String[] args)
    {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                createAndShowGUI();
            }
        });

    }

    String getSignature()
    {
        return signature;
    }

    void setCurrrentCookie()
    {
        currentCookie=getRandomLine(); //Put new aphorism into the active cookie where it can be copied to the System
        // clipboard as needed.
        textPane.setText(currentCookie); // Put it in the textPanel and show.
    }

    String getCurrentCookie()
    {
        // Can't pass static, so much create copy
        String s=currentCookie;
        return s;

    }

}