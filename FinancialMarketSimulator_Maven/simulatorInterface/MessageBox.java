package simulatorInterface;

import javax.swing.JOptionPane;

/**
 * @author Grape <cos301.mainproject.grape@gmail.com>
 */

public class MessageBox {

    public static void infoBox(String infoMessage, String location) {
        JOptionPane.showMessageDialog(null, infoMessage, location, JOptionPane.INFORMATION_MESSAGE);
    }
}