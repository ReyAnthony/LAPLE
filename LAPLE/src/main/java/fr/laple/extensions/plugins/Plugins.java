package fr.laple.extensions.plugins;

import javax.swing.*;

/**
 * Created by anthonyrey on 17/07/2015.
 */
public class Plugins {

    public static void pluginError(String errorMessage)
    {
        JOptionPane.showMessageDialog(null, errorMessage, "Fatal Error", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    public static void pluginWarning(String warning)
    {
        JOptionPane.showMessageDialog(null, warning, "Error", JOptionPane.WARNING_MESSAGE);
    }

    public static void pluginMessage(String message)
    {
        JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }
}
