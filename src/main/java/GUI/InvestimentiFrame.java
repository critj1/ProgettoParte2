package GUI;

import javax.swing.*;
import java.awt.*;

class InvestmentiFrame extends JFrame {
    private final JPanel panel = new JPanel();
    private final GridBagConstraints gbc = new GridBagConstraints();
    private final JButton shortTermButton;
    private final JButton midTermButton;
    private final JButton longTermButton;
    private final JLabel importoLable;
    private final JTextField amountField;
    private final JButton investButton;
    private final JButton cancelButton;
    private int settimane = 0;
    private int risk = 0;
    private double importo = 0;
    private boolean verifica = false;

    public InvestmentiFrame() {
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);


        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(17, 21, 28));
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;


        JButton lowRiskButton = new JButton("Basso Rischio");
        JButton mediumRiskButton = new JButton("Medio Rischio");
        JButton highRiskButton = new JButton("Alto Rischio");

        shortTermButton = new JButton("Breve Durata");
        midTermButton = new JButton("Media Durata");
        longTermButton = new JButton("Lunga Durata");

        importoLable = new JLabel("Importo");
        importoLable.setForeground(Color.WHITE);
        importoLable.setFont(new Font("Arial", Font.BOLD, 32));
        importoLable.setHorizontalAlignment(SwingConstants.CENTER);

        amountField = new JTextField(15);
        amountField.setFont(new Font("Arial", Font.BOLD, 16));
        amountField.setFont(new Font("Arial", Font.PLAIN, 16));
        investButton = new JButton("Investi");
        cancelButton = new JButton("Annulla");

        JButton[] buttons = {lowRiskButton, mediumRiskButton, highRiskButton, shortTermButton, midTermButton, longTermButton, investButton, cancelButton};

        for (JButton button : buttons) {
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setBackground(Color.GREEN);
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setPreferredSize(new Dimension(400, 50));
        }
        cancelButton.setBackground(Color.RED);


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(lowRiskButton, gbc);
        gbc.gridy = 1;
        panel.add(mediumRiskButton, gbc);
        gbc.gridy = 2;
        panel.add(highRiskButton, gbc);
        gbc.gridy = 3;
        panel.add(cancelButton, gbc);

        lowRiskButton.addActionListener(ignored -> {
            risk = 1;
            termButtons();
        });
        mediumRiskButton.addActionListener(ignored -> {
            risk = 2;
            termButtons();

        });
        highRiskButton.addActionListener(ignored -> {
            risk = 3;
            termButtons();
        });

        shortTermButton.addActionListener(ignored -> {
            settimane = 1;
            importButtons();
        });
        midTermButton.addActionListener(ignored -> {
            settimane = 2;
            importButtons();

        });
        longTermButton.addActionListener(ignored -> {
            settimane = 3;
            importButtons();
        });

        cancelButton.addActionListener(ignored -> dispose());

        investButton.addActionListener(ignored -> {
            String text = amountField.getText();
            double soldi;
            if (text != null) {
                try {
                    soldi = Double.parseDouble(text);

                    if (soldi > 0) {
                        this.importo = soldi;
                        this.verifica = true;
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Inserisci un numero maggiore di 0.", "Errore", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Inserisci un numero valido.", "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        add(panel);
        setVisible(true);
    }

    public int getRisk() {
        return risk;
    }

    public double getImporto() {
        return importo;
    }

    public int getSettimane() {
        return settimane;
    }

    public boolean getVerifica() {
        return verifica;
    }

    private void resetPanel() {
        panel.removeAll();
        panel.updateUI();
    }

    private void termButtons() {
        resetPanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(shortTermButton, gbc);
        gbc.gridy = 1;
        panel.add(midTermButton, gbc);
        gbc.gridy = 2;
        panel.add(longTermButton, gbc);
        gbc.gridy = 3;
        panel.add(cancelButton, gbc);
        panel.updateUI();
    }

    private void importButtons() {
        resetPanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(importoLable, gbc);
        gbc.gridy = 1;
        panel.add(amountField, gbc);
        gbc.gridwidth = 1;
        gbc.gridy = 2;
        panel.add(investButton, gbc);
        gbc.gridx = 1;
        panel.add(cancelButton, gbc);
        panel.updateUI();
    }

}

