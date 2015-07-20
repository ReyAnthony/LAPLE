package fr.laple.extensions.plugins;

import javax.swing.*;

/**
 * Utility Class for Plugins
 *
 * @author anthonyrey
 */
public class Plugins {

    /**
     * Send an error message and quit
     * @param errorMessage The message
     */
    public static void pluginError(String errorMessage)
    {
        JOptionPane.showMessageDialog(null, errorMessage, "Fatal Error", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    /**
     * Send a warning message
     *
     * @param warning The message
     */
    public static void pluginWarning(String warning)
    {
        JOptionPane.showMessageDialog(null, warning, "Error", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * send a message
     * @param message The message
     */
    public static void pluginMessage(String message)
    {
        JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }
}
