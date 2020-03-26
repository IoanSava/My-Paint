package gui.panels;

import gui.MainFrame;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

/**
 * A configuration panel for introducing
 * parameters regarding the shapes that
 * will be drawn
 *
 * @author Ioan Sava
 */
@Getter
public class ConfigurationPanel extends JPanel {
    private final MainFrame frame;
    private JSpinner sidesField; // number of sides
    private JComboBox<String> colorCombo; // the color of the shape

    public ConfigurationPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    /**
     * Initialize configuration panel according to
     * what is selected in shape manager panel
     */
    private void init() {
        if (frame.getShapeMangerPanel().getRegularPolygonRadioButton().isSelected()) {
            JLabel sidesLabel = new JLabel("Number of sides");
            sidesLabel.setFont(new Font("Dialog", Font.PLAIN, 15));
            sidesField = new JSpinner(new SpinnerNumberModel(3, 3, 30, 1));
            sidesField.setValue(6); //default number of sides
            add(sidesLabel);
            add(sidesField);
        }

        JLabel colorLabel = new JLabel("Choose a color");
        colorLabel.setFont(new Font("Dialog", Font.PLAIN, 15));
        String[] colors = {"Black", "Random"};
        colorCombo = new JComboBox<>(colors);

        //JPanel uses FlowLayout by default
        add(colorLabel);
        add(colorCombo);
    }

    /**
     * Adapt the configuration panel according
     * to the selected shape in shape manager panel
     */
    public void adaptPanel() {
        this.removeAll();
        init();
        this.revalidate();
        this.repaint();
    }
}
