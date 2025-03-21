package GUI;

import Utenti.GestioneUtente;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private final GestioneUtente gestioneUtente = new GestioneUtente();

    public LoginFrame() {

        setTitle("Login Screen");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(17, 21, 28));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;


        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Arial", Font.BOLD, 18));
        userLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField userText = new JTextField(15);
        userText.setFont(new Font("Arial", Font.PLAIN, 16));


        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        passLabel.setFont(new Font("Arial", Font.BOLD, 18));
        passLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JPasswordField passText = new JPasswordField(15);
        passText.setFont(new Font("Arial", Font.PLAIN, 16));


        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(Color.GREEN);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);


        JButton registerButton = new JButton("Register");
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.setBackground(Color.RED);
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setBorderPainted(false);


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(userLabel, gbc);
        gbc.gridy = 1;
        panel.add(userText, gbc);
        gbc.gridy = 2;
        panel.add(passLabel, gbc);
        gbc.gridy = 3;
        panel.add(passText, gbc);

        gbc.gridy = 4;
        gbc.gridwidth = 1;
        panel.add(loginButton, gbc);
        gbc.gridx = 1;
        panel.add(registerButton, gbc);


        loginButton.addActionListener(ignored -> {
            String username = userText.getText().trim();
            String password = new String(passText.getPassword()).trim();
            if (!username.isEmpty() && !password.isEmpty()) {
                if (gestioneUtente.caricaUtente(username, password)) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    LoginFrame.this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Username or Password is incorrect", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Username or Password cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        registerButton.addActionListener(ignored -> {
            String username = userText.getText().trim();
            String password = new String(passText.getPassword()).trim();
            if (!username.isEmpty() && !password.isEmpty()) {
                if (gestioneUtente.registraUtente(username, password)) {
                    JOptionPane.showMessageDialog(null, "Registration Successful!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Username already exists", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Username or Password cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        add(panel);
        setVisible(true);
    }

    public GestioneUtente getGestioneUtente() {
        return gestioneUtente;
    }
}
