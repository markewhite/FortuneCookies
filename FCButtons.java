package fortuneCookies;


import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class creates a test panel, responds to button pushes, and then passes into to
 * calling FortuneCookie class via a pointer to the class.
 */
class FCButtons
{

    JPanel panel;
    FortuneCookies fc;

    FCButtons(FortuneCookies fcin)
    {
        panel=new JPanel();
        fc=fcin;







        // Create and add buttons
        JButton buttonCopy=new JButton("Copy");

        buttonCopy.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onCopy();
            }
        });
        panel.add(buttonCopy);


        JButton buttonNext=new JButton("Next");

        buttonNext.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onNext();
            }
        });
        panel.add(buttonNext);

        JButton buttonQuit=new JButton("Quit");
        buttonQuit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onQuit();
            }
        });
        panel.add(buttonQuit);

    }






    public JPanel createButtonPanel()
    {
        return panel;
    }

    private void onCopy()
    {
        String sigCookie= fc.getSignature() + "\n\n" + fc.getCurrentCookie();

        // Copy to System Clipboard

        Clipboard cb=Toolkit.getDefaultToolkit().getSystemClipboard(); //Get access to System clipboard.

        StringSelection sigCookieSelection=new StringSelection(sigCookie); // Wrap current cookie for transport there.

        cb.setContents(sigCookieSelection, null); // copy to clipboard
    }

    /*** Responses to Button Commands ***/


    private void onNext()
    {
        String nextCookie = fc.getRandomLine(); //Put new aphorism into the active cookie where it can be copied to
        // the System clipboard as needed.
        fc.textPane.setText(nextCookie);
    }
    private void onQuit()
    {
        System.exit(0);
    }
}


