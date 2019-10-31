package fortuneCookies;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;

public class FCDialog extends JDialog
{
    private String signature="";
    private String cookie;
    private JPanel contentPane;
    private JButton buttonCopy;
    private JButton buttonQuit;
    private JScrollPane scrollPane;
    private JTextPane textPane;
    private JButton buttonNext;
    private JLabel lblTitle;


    public FCDialog(String sig, String finCookie)
    {
        signature=sig;
        cookie=finCookie;
        setContentPane(contentPane);
        setModal(true);





        textPane.setText(cookie);
      //  getRootPane().setDefaultButton(buttonCopy);

        buttonCopy.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                OnCopy();
            }
        });

        buttonQuit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onQuit();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                onQuit();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onQuit();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        buttonNext.addActionListener(new ActionListener()
        {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //  FortuneCookies.getRandomLine();
                textPane.setText(FortuneCookies.getRandomLine());
                // Load the new cookie string into cookie so it can be copied to System clipboard
                cookie = textPane.getText();

            }
        });
    }

    /**
     * Copies signature and selected quote to sytem clipboard.
     */
    private void OnCopy()
    {
        String sigCookie=signature + "\n\n" + cookie;

        // Copy to System Clipboard

        Clipboard cb=Toolkit.getDefaultToolkit().getSystemClipboard(); //Get access to System clipboard.

        StringSelection sigCookieSelection=new StringSelection(sigCookie); // Wrap current cookie for transport there.

        cb.setContents(sigCookieSelection, null); // copy to clipboard
    }

    // Eat Me!
    private void onQuit()
    {
        // add your code here if necessary
        dispose();
    }

 /*   public static void main(String[] args)
    {
        FCDialog dialog = new FCDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
*/

}
