package Tools;

import Gestione.Investimento;

import javax.swing.*;
import java.awt.*;

public class InvestimentoRenderer extends JPanel implements ListCellRenderer<Investimento> {
    private final JLabel guadagnoLabel;
    private final JLabel settimaneLabel;

    public InvestimentoRenderer() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        guadagnoLabel = new JLabel();
        guadagnoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        guadagnoLabel.setForeground(new Color(218, 156, 156));

        settimaneLabel = new JLabel();
        settimaneLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        settimaneLabel.setForeground(new Color(67, 83, 125));


        gbc.gridx = 0;
        gbc.gridy = 0;
        add(guadagnoLabel, gbc);

        gbc.gridy = 1;
        add(settimaneLabel, gbc);

        setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.RED));
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Investimento> list, Investimento investimento, int index, boolean isSelected, boolean cellHasFocus) {
        guadagnoLabel.setText("Guadagno Stimato: €" + String.format("%.2f", investimento.getGuadagno()));
        settimaneLabel.setText("Settimane Rimaste: " + investimento.getSettimane());

        if (isSelected) {
            setBackground(new Color(173, 216, 230));
        } else {
            setBackground(new Color(17, 21, 28));
        }
        return this;
    }

}
