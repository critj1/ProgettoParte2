package GUI;

import javax.swing.*;
import java.awt.*;

class InvestmentFrame extends JFrame {
    private JPanel panel = new JPanel();
    private GridBagConstraints gbc = new GridBagConstraints();

    private JButton lowRiskButton;
    private JButton mediumRiskButton;
    private JButton highRiskButton;

    private JButton shortTermButton;
    private JButton midTermButton;
    private JButton longTermButton;

    private JTextField amountField = new JTextField();
    private JButton investButton;
    private JButton cancelButton;
    public InvestmentFrame() {
        setTitle("Nuovo Investimento");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);



        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(17,21,28));
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        //gbc.fill = GridBagConstraints.BOTH;
        //gbc.weightx = 1.0;
        //gbc.weighty = 1.0;


        lowRiskButton = new JButton("Basso Rischio");
        mediumRiskButton = new JButton("Medio Rischio");
        highRiskButton = new JButton("Alto Rischio");

        shortTermButton = new JButton("Breve Durata");
        midTermButton = new JButton("Media Durata");
        longTermButton = new JButton("Lunga Durata");


        JTextField amountField = new JTextField(15);
        amountField.setFont(new Font("Arial",Font.BOLD,16));
        amountField.setFont(new Font("Arial", Font.PLAIN, 16));
        investButton = new JButton("Investi");
        cancelButton = new JButton("Annulla");

        JButton[] buttons = {lowRiskButton,mediumRiskButton,highRiskButton,shortTermButton,midTermButton,longTermButton,investButton,cancelButton};

        for (JButton button : buttons) {
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setBackground(Color.GREEN);
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setPreferredSize(new Dimension(400, 50));
        }
        cancelButton.setBackground(Color.RED);



        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; panel.add(lowRiskButton, gbc);
        gbc.gridy = 1; panel.add(mediumRiskButton, gbc);
        gbc.gridy = 2; panel.add(highRiskButton, gbc);
        gbc.gridy = 3; panel.add(cancelButton, gbc);

        lowRiskButton.addActionListener(_ -> {
            resetPanel();
            termButtons();
        });
        mediumRiskButton.addActionListener(e -> {
            resetPanel();
            termButtons();

        });
        highRiskButton.addActionListener(e -> {
            resetPanel();
            termButtons();
        });

        shortTermButton.addActionListener(e -> {
            resetPanel();
            importButtons();
        });
        midTermButton.addActionListener(e -> {
            resetPanel();
            importButtons();

        });
        longTermButton.addActionListener(e -> {
            resetPanel();
            importButtons();
        });

        cancelButton.addActionListener(e -> {
            dispose();
        });

        add(panel);

    }

    private void resetPanel(){
        JButton[] buttons = {lowRiskButton,mediumRiskButton,highRiskButton,shortTermButton,midTermButton,longTermButton,cancelButton};
        for (JButton button : buttons) {
            panel.remove(button);
        }
        panel.updateUI();
    }

    private void termButtons(){
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; panel.add(shortTermButton, gbc);
        gbc.gridy = 1; panel.add(midTermButton, gbc);
        gbc.gridy = 2; panel.add(longTermButton, gbc);
        gbc.gridy = 3; panel.add(cancelButton, gbc);
        panel.updateUI();
    }

    private void importButtons(){
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; panel.add(new JLabel("Importo"), gbc);
        gbc.gridy = 1; panel.add(amountField, gbc);
        gbc.gridwidth = 1;
        gbc.gridy = 2; panel.add(investButton, gbc);
        gbc.gridx = 1; panel.add(cancelButton, gbc);



        panel.updateUI();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InvestmentFrame().setVisible(true));
    }
}

