package GUI;

import Gestione.GestioneInvestimenti;
import Utenti.GestioneUtente;
import Utenti.Utente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
    private final Utente utente;
    private final GestioneInvestimenti gestioneInvestimenti;
    private final JLabel contoLabel;
    private final JLabel portafoglioLabel;
    private final JLabel settimanaLabel;

    public MainFrame(GestioneUtente gestioneUtente) {
        this.utente = gestioneUtente.getUtente();
        this.gestioneInvestimenti = gestioneUtente.getGestioneInvestimenti();


        setTitle("Banca");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(17, 21, 28));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // Account Balance Label
        contoLabel = new JLabel("Conto: €" + gestioneUtente.getUtente().getConto());
        contoLabel.setForeground(new Color(239, 111, 108));
        contoLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Portfolio Label
        portafoglioLabel = new JLabel("Portafoglio: €" + gestioneUtente.getUtente().getPortafoglio());
        portafoglioLabel.setForeground(new Color(239, 111, 108));
        portafoglioLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Week Label
        settimanaLabel = new JLabel("Settimana: " + gestioneUtente.getUtente().getSettimana());
        settimanaLabel.setForeground(Color.WHITE);
        settimanaLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Buttons
        JButton depositaButton = new JButton("Deposita");
        JButton prelevaButton = new JButton("Preleva");
        JButton newInvestimentoButton = new JButton("Nuovo Investimento");
        JButton viewInvestimentiButton = new JButton("Guarda Investimenti");
        JButton listaTransazioniButton = new JButton("Transazioni");
        JButton settimanaButton = new JButton("Avanza Settimana");
        JButton salvaEdEsciButton = new JButton("Salva ed Esci");

        JButton[] buttons = {depositaButton, prelevaButton, newInvestimentoButton, viewInvestimentiButton, listaTransazioniButton, settimanaButton, salvaEdEsciButton};

        for (JButton button : buttons) {
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setBackground(new Color(111, 156, 235));
            button.setForeground(new Color(250, 207, 173));
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setPreferredSize(new Dimension(300, 50));

            // Hover effect
            button.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    button.setBackground(new Color(48, 107, 172));
                }

                public void mouseExited(MouseEvent e) {
                    button.setBackground(new Color(111, 156, 235));
                }
            });
        }

        salvaEdEsciButton.setBackground(new Color(56, 86, 63));
        salvaEdEsciButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                salvaEdEsciButton.setBackground(new Color(39, 60, 44));
            }

            public void mouseExited(MouseEvent e) {
                salvaEdEsciButton.setBackground(new Color(56, 86, 63));
            }
        });

        // Layout setup
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(settimanaLabel, gbc);
        gbc.gridy = 1;
        panel.add(portafoglioLabel, gbc);
        gbc.gridy = 2;
        panel.add(contoLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 3;
        gbc.gridx = 0;
        panel.add(depositaButton, gbc);
        gbc.gridx = 1;
        panel.add(prelevaButton, gbc);
        gbc.gridy = 4;
        gbc.gridx = 0;
        panel.add(newInvestimentoButton, gbc);
        gbc.gridx = 1;
        panel.add(viewInvestimentiButton, gbc);
        gbc.gridy = 5;
        gbc.gridx = 0;
        panel.add(listaTransazioniButton, gbc);
        gbc.gridx = 1;
        panel.add(settimanaButton, gbc);
        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panel.add(salvaEdEsciButton, gbc);

        // Action Listeners (Placeholder)
        depositaButton.addActionListener(_ -> deposita());
        prelevaButton.addActionListener(_ -> prelieva());
        settimanaButton.addActionListener(_ -> avanzaSettimana());

        newInvestimentoButton.addActionListener(_ -> JOptionPane.showMessageDialog(null, "Nuovo Investimento clicked!"));
        viewInvestimentiButton.addActionListener(_ -> JOptionPane.showMessageDialog(null, "Guarda Investimenti clicked!"));
        listaTransazioniButton.addActionListener(_ -> JOptionPane.showMessageDialog(null, "Transazioni clicked!"));

        salvaEdEsciButton.addActionListener(_ -> {
            gestioneUtente.salvaDatiUtente(utente, gestioneInvestimenti);
            JOptionPane.showMessageDialog(null, "Arrivederci");
            System.exit(0);
        });

        // Add panel to frame
        add(panel);
        setVisible(true);
    }

    private void update() {
        portafoglioLabel.setText("Portafoglio: €" + utente.getPortafoglio());
        contoLabel.setText("Conto: €" + utente.getConto());
        settimanaLabel.setText("Settimana: " + utente.getSettimana());

    }

    private void deposita() {
        String deposita = JOptionPane.showInputDialog(this, " Deposita importo:", "0");
        if (deposita != null) {
            try {
                double importo = Double.parseDouble(deposita);

                if (!utente.deposita(importo)) {
                    JOptionPane.showMessageDialog(this, "Inserisci un importo valido.", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                update();
                utente.registraTransazione("Deposito", importo);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Inserisci un numero valido.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void prelieva() {
        String prelieva = JOptionPane.showInputDialog(this, " Preleva importo:", "0");
        if (prelieva != null) {
            try {
                double importo = Double.parseDouble(prelieva);

                if (!utente.preleva(importo)) {
                    JOptionPane.showMessageDialog(this, "Inserisci un importo valido.", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                update();
                utente.registraTransazione("Prelievo", importo);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Inserisci un numero valido.", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void avanzaSettimana() {
        utente.nuovaSettimana();
        utente.registraTransazione("Stipendio Settimanale", 100);
        double guadagno = gestioneInvestimenti.aggiornaInvestimenti();

        if (utente.depositaGuadagno(guadagno)) {
            utente.registraTransazione("Guadagno Investimenti", guadagno);
            JOptionPane.showMessageDialog(this, "Uno o piu investimenti sono finiti.");
        }
        update();
    }


}
