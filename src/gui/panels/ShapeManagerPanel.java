package gui.panels;

import gui.MainFrame;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * A panel which contains a list of
 * available shapes
 *
 * @author Ioan Sava
 */
@Getter
public class ShapeManagerPanel extends JPanel {
    private final MainFrame frame;
    private JRadioButton regularPolygonRadioButton = new JRadioButton("Regular Polygon");
    private JRadioButton nodeRadioButton = new JRadioButton("Node");

    public ShapeManagerPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void addElementsInContainer() {
        add(regularPolygonRadioButton);
        add(nodeRadioButton);
    }

    private void addListenersToElements() {
        regularPolygonRadioButton.addActionListener(this::adaptConfigurationPanel);
        nodeRadioButton.addActionListener(this::adaptConfigurationPanel);
    }

    private void init() {
        regularPolygonRadioButton.setSelected(true);
        setLayout(new GridLayout(2,1));

        // make radio buttons mutually exclusive
        ButtonGroup groupOfRadioButtons = new ButtonGroup();
        groupOfRadioButtons.add(regularPolygonRadioButton);
        groupOfRadioButtons.add(nodeRadioButton);

        addElementsInContainer();
        addListenersToElements();
    }

    /**
     * Adapt the configuration panel according
     * to the selected shape
     */
    private void adaptConfigurationPanel(ActionEvent event) {
        frame.getConfigurationPanel().adaptPanel();
    }
}
