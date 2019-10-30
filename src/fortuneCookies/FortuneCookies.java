package fortuneCookies;

import java.awt.Component;
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


/**
 * Selects a random quote from a text file and copies it to the system clipboard.
 * Class must be static so orther classes in the project can refer to teh string
 * containing the signature (address and phone number) and the member getRandomLine().
 *
 */
public class FortuneCookies
{
    static final String signature = "Mark White\nphone 001-404-735-7547\nmark@markewhite.com";
    static private List<String> cookieList = new ArrayList<String>();
    String line;
    static String cookie;

    static JMenuBar menuBar = new JMenuBar();


    FortuneCookies()
    {

      // JFrame frame = new (JFrame) SwingUtilities.getRoot();




        Charset charset=Charset.forName("UTF-8");
        Path path = Paths.get("/Users/mark/Desktop/Java/FortuneCookies/src/fortuneCookies/FortuneCoookies.txt");




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


// Create cookieList
        try (BufferedReader reader=Files.newBufferedReader(path, charset))
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

                catch (IOException e)
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












        public static void main (String[]args)
        {

            FortuneCookies fortuneCookies = new FortuneCookies();


            fortuneCookies.FCDialog dialog = new fortuneCookies.FCDialog(signature, cookie);
            dialog.pack();
            dialog.setVisible(true);

            System.exit(0);

        }
    }






