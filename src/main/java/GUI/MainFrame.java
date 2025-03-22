package GUI;

import Gestione.GestioneInvestimenti;
import Gestione.Investimento;
import Tools.TransazioniGraph;
import Utenti.GestioneUtente;
import Utenti.Utente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Objects;

public class MainFrame extends JFrame {
    private final Utente utente;
    private final GestioneInvestimenti gestioneInvestimenti;
    private final JLabel contoLabel;
    private final JLabel portafoglioLabel;
    private final JLabel settimanaLabel;
    private final ListaInvestimentiFrame lif;
    private final GestioneUtente gestioneUtente;

    public MainFrame(GestioneUtente gestioneUtente) {
        this.gestioneUtente = gestioneUtente;
        this.utente = gestioneUtente.getUtente();
        this.gestioneInvestimenti = gestioneUtente.getGestioneInvestimenti();
        this.lif = new ListaInvestimentiFrame(gestioneInvestimenti.getInvestimenti());


        setTitle("Banca");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("img/logoMain.jpg")));
        setIconImage(icon.getImage());

        addWindowListener(new WindowListener() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveAndExit();
            }

            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(17, 21, 28));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        contoLabel = new JLabel("Conto: €" + gestioneUtente.getUtente().getConto());
        contoLabel.setForeground(new Color(239, 111, 108));
        contoLabel.setFont(new Font("Arial", Font.BOLD, 24));

        portafoglioLabel = new JLabel("Portafoglio: €" + gestioneUtente.getUtente().getPortafoglio());
        portafoglioLabel.setForeground(new Color(239, 111, 108));
        portafoglioLabel.setFont(new Font("Arial", Font.BOLD, 24));

        settimanaLabel = new JLabel("Settimana: " + gestioneUtente.getUtente().getSettimana());
        settimanaLabel.setForeground(Color.WHITE);
        settimanaLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JButton depositaButton = new JButton("Deposita");
        JButton prelevaButton = new JButton("Preleva");
        JButton newInvestimentoButton = new JButton("Nuovo Investimento");
        JButton viewInvestimentiButton = new JButton("Guarda Investimenti");
        JButton graphTransazioniButton = new JButton("Grafico Transazioni");
        JButton settimanaButton = new JButton("Avanza Settimana");
        JButton deleteUserButton = new JButton("DELETE USER");
        JButton salvaEdEsciButton = new JButton("Salva ed Esci");

        JButton[] buttons = {depositaButton, prelevaButton, newInvestimentoButton, viewInvestimentiButton, graphTransazioniButton, settimanaButton, salvaEdEsciButton};

        for (JButton button : buttons) {
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setBackground(new Color(111, 156, 235));
            button.setForeground(new Color(250, 207, 173));
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setPreferredSize(new Dimension(300, 50));

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

        deleteUserButton.setFont(new Font("Arial", Font.BOLD, 13));
        deleteUserButton.setBackground(Color.RED);
        deleteUserButton.setForeground(Color.WHITE);
        deleteUserButton.setFocusPainted(false);
        deleteUserButton.setBorderPainted(false);
        deleteUserButton.setHorizontalAlignment(SwingConstants.RIGHT);
        deleteUserButton.setPreferredSize(new Dimension(125, 20));

        deleteUserButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                deleteUserButton.setBackground(new Color(255, 255, 10));
            }

            public void mouseExited(MouseEvent e) {
                deleteUserButton.setBackground(Color.RED);
            }
        });


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panel.add(settimanaLabel, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(deleteUserButton, gbc);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
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
        panel.add(graphTransazioniButton, gbc);
        gbc.gridx = 1;
        panel.add(settimanaButton, gbc);
        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        panel.add(salvaEdEsciButton, gbc);

        depositaButton.addActionListener(ignored -> deposita());

        prelevaButton.addActionListener(ignored -> prelieva());

        settimanaButton.addActionListener(ignored -> avanzaSettimana());

        newInvestimentoButton.addActionListener(ignored -> newInvestimento());

        viewInvestimentiButton.addActionListener(ignored -> viewInvestimenti());

        graphTransazioniButton.addActionListener(ignored -> new TransazioniGraph(utente.getTransazioniPath()));

        deleteUserButton.addActionListener(ignored -> deleteUser());

        salvaEdEsciButton.addActionListener(ignored -> saveAndExit());

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
        if (lif.isVisible()) {
            lif.updateVector(gestioneInvestimenti.getInvestimenti());
        }
        update();
    }

    private void newInvestimento() {
        if (utente.getConto() <= 0) {
            JOptionPane.showMessageDialog(this, "Non hai abbastanza Soldi nel Conto per Investire.", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }
        InvestmentiFrame investmentiFrame = new InvestmentiFrame();
        investmentiFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent windowEvent) {
                investi(investmentiFrame.getVerifica(), investmentiFrame.getSettimane(), investmentiFrame.getRisk(), investmentiFrame.getImporto());
            }
        });
    }

    private void investi(boolean verifica, int settimane, int risk, double importo) {
        if (!verifica) {
            JOptionPane.showMessageDialog(this, "Investimento annullato.", "Informazione", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!utente.investiSoldi(importo)) {
            JOptionPane.showMessageDialog(this, "Non hai abbastanza Soldi nel Conto per Investire.", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int percentuale = 0, soglia = 0;
        switch (risk) {
            case 1 -> {
                percentuale = (int) (Math.random() * 10);
                soglia = 80;
            }
            case 2 -> {
                percentuale = (int) ((Math.random() * 40) + 10);
                soglia = 50;
            }
            case 3 -> {
                percentuale = (int) ((Math.random() * 50) + 50);
                soglia = 30;
            }
        }

        gestioneInvestimenti.aggiungiInvestimento(new Investimento(importo, settimane, percentuale, soglia));
        update();
        if (lif.isVisible()) {
            lif.updateVector(gestioneInvestimenti.getInvestimenti());
        }
        utente.registraTransazione("Investimento", importo);
        JOptionPane.showMessageDialog(null, "Hai investito con successo");

    }

    private void viewInvestimenti() {
        if (!lif.isVisible()) {
            lif.updateVector(gestioneInvestimenti.getInvestimenti());
            lif.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "La Lista e gia attiva.", "Informazione", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteUser() {
        String verifica = JOptionPane.showInputDialog(this, " TYPE \"DELETE\" TO CONTINUE:", " ");
        if (verifica != null) {
            if (verifica.equalsIgnoreCase("DELETE")) {
                utente.eliminaUtente();
                JOptionPane.showMessageDialog(this, "Your account is being deleted.", "Informazione", JOptionPane.WARNING_MESSAGE);
                dispose();

            } else {
                JOptionPane.showMessageDialog(null, "Hai sbagliato a scrivere");
            }
        }

    }

    private void saveAndExit() {
        JOptionPane.showMessageDialog(null, "Saving...");
        gestioneUtente.salvaDatiUtente(utente, gestioneInvestimenti);
        System.exit(0);
    }

}
