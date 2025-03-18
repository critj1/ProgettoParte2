package GUI;

import Gestione.Investimento;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class ListaInvestimentiFrame extends JFrame{

        public ListaInvestimentiFrame(Vector<Investimento> investimenti) {
//AGGIUNGI RENDER PER PIU CELLE DELGI INVESTIMENTI CELLRENDER
            investimenti.add(new Investimento(2,100));
            investimenti.add(new Investimento(2,100));
            investimenti.add(new Investimento(2,100));

            setTitle("Lista Investimenti");
            setSize(400, 500);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            panel.setBackground(new Color(17, 21, 28));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;


            JList<Investimento> investimentiList = new JList<>(investimenti);
            investimentiList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            investimentiList.setFont(new Font("Arial", Font.PLAIN, 16));
            investimentiList.setBackground(new Color(67, 83, 125));
            investimentiList.setForeground(new Color(218,156,156));
            investimentiList.setSelectionBackground(Color.RED);


            JScrollPane scrollPane = new JScrollPane(investimentiList);
            scrollPane.setBackground(new Color(17, 21, 28));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.weighty = 3.0;
            panel.add(scrollPane, gbc);

            JLabel informazioniLabel = new JLabel("Seleziona un investimento");
            informazioniLabel.setFont(new Font("Arial", Font.BOLD, 14));
            informazioniLabel.setHorizontalAlignment(SwingConstants.CENTER);
            gbc.gridy = 1;
            gbc.weighty = 0.5;
            panel.add(informazioniLabel, gbc);


            investimentiList.addListSelectionListener(ignored -> {
                Investimento investimentoSelezionato = investimentiList.getSelectedValue();
                if (investimentoSelezionato != null) {
                    informazioniLabel.setText("Selezionato: " + investimentoSelezionato);
                }
            });

            JButton closeButton = new JButton("Chiudi");
            closeButton.setFont(new Font("Arial", Font.BOLD, 16));
            closeButton.setBackground(Color.RED);
            closeButton.setForeground(Color.WHITE);
            closeButton.setFocusPainted(false);
            closeButton.setBorderPainted(false);
            closeButton.addActionListener(ignored -> dispose());


            gbc.gridy = 2;
            gbc.gridwidth = 1;
            gbc.weighty = 0.5;
            panel.add(closeButton, gbc);

            add(panel);
            setVisible(true);
        }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("non  stato caricato il UImanager");
        }

        SwingUtilities.invokeLater(() -> new ListaInvestimentiFrame(new Vector<>()));
    }
}
