package GUI;

import Gestione.Investimento;
import Tools.InvestimentoRenderer;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class ListaInvestimentiFrame extends JFrame {
    private final JPanel panel;
    private final GridBagConstraints gbc;
    private final JLabel informazioniLabel;
    private JList<Investimento> investimentiList;
    private JScrollPane scrollPane;

    public ListaInvestimentiFrame(Vector<Investimento> investimenti) {

        setTitle("Lista Investimenti");
        setSize(400, 500);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(17, 21, 28));
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;


        investimentiList = new JList<>(investimenti);
        investimentiList.setCellRenderer(new InvestimentoRenderer());
        investimentiList.setBackground(new Color(17, 21, 28));
        investimentiList.setBorder(BorderFactory.createLineBorder(Color.RED, 2));


        scrollPane = new JScrollPane(investimentiList);
        scrollPane.setBackground(new Color(17, 21, 28));
        scrollPane.getVerticalScrollBar().setBackground(Color.WHITE);
        scrollPane.getHorizontalScrollBar().setBackground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weighty = 3.0;
        panel.add(scrollPane, gbc);

        informazioniLabel = new JLabel("Seleziona un investimento");
        informazioniLabel.setFont(new Font("Arial", Font.BOLD, 16));
        informazioniLabel.setHorizontalAlignment(SwingConstants.CENTER);
        informazioniLabel.setForeground(new Color(218, 156, 156));
        gbc.gridy = 1;
        gbc.weighty = 0.5;
        panel.add(informazioniLabel, gbc);


        investimentiList.addListSelectionListener(ignored -> {
            Investimento investimentoSelezionato = investimentiList.getSelectedValue();
            if (investimentoSelezionato != null) {
                informazioniLabel.setText("Investimento Settimane Rimanenti: " + investimentoSelezionato.getSettimane());
            }
        });

        JButton closeButton = new JButton("Chiudi");
        closeButton.setFont(new Font("Arial", Font.BOLD, 16));
        closeButton.setBackground(Color.RED);
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.setBorderPainted(false);
        closeButton.addActionListener(ignored -> setVisible(false));


        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weighty = 0.5;
        panel.add(closeButton, gbc);

        add(panel);
    }

    public void updateVector(Vector<Investimento> i) {
        panel.remove(scrollPane);
        investimentiList = new JList<>(i);
        investimentiList.setCellRenderer(new InvestimentoRenderer());
        investimentiList.setBackground(new Color(17, 21, 28));
        investimentiList.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

        investimentiList.addListSelectionListener(ignored -> {
            Investimento investimentoSelezionato = investimentiList.getSelectedValue();
            if (investimentoSelezionato != null) {
                informazioniLabel.setText("Investimento Settimane Rimanenti: " + investimentoSelezionato.getSettimane());
            }
        });

        scrollPane = new JScrollPane(investimentiList);
        scrollPane.setBackground(new Color(17, 21, 28));
        scrollPane.getVerticalScrollBar().setBackground(Color.WHITE);
        scrollPane.getHorizontalScrollBar().setBackground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weighty = 3.0;
        panel.add(scrollPane, gbc);

        panel.updateUI();
    }

}
