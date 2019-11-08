package fortuneCookies;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class FCMenu implements ActionListener
{
    FortuneCookies fc;
    JMenuBar menuBar;
    FCMenu(FortuneCookies fcin)
    {
        menuBar=new JMenuBar();


        // Set up the lone menu.
        JMenu fileMenu=new JMenu("File");
        //menu.setMnemonic(KeyEvent.VK_D);
        menuBar.add(fileMenu);

        JMenuItem aboutMI=new JMenuItem("About This Program");
        aboutMI.setActionCommand("aboutProgram");
        aboutMI.addActionListener(this);
        fileMenu.add(aboutMI);

        JMenuItem fileExitMenuItem=new JMenuItem("Exit");
        fileExitMenuItem=new JMenuItem("Quit  ⌘Q");
        fileExitMenuItem.setMnemonic(KeyEvent.VK_Q);
        fileExitMenuItem.setActionCommand("quit");
        fileExitMenuItem.addActionListener(this);
        fileMenu.add(fileExitMenuItem);


    }

    // React to menu selections.
    public void actionPerformed(ActionEvent e)
    {

        if ("quit".equals(e.getActionCommand())) {
            quit();
        } else if ("aboutProgram".equals(e.getActionCommand())) {
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

    public JMenuBar createMenuBar()
    {
        return menuBar;
    }

}
