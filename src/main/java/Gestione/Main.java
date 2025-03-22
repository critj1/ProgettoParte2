package Gestione;

import GUI.LoginFrame;
import GUI.MainFrame;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("non  stato caricato il UImanager");
        }

        LoginFrame loginFrame = new LoginFrame();

        loginFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                SwingUtilities.invokeLater(() -> new MainFrame(loginFrame.getGestioneUtente()));
            }
        });
    }

}
