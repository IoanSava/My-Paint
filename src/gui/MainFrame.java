package gui;

import gui.panels.ConfigurationPanel;
import gui.panels.ControlPanel;
import gui.panels.DrawingPanel;
import gui.panels.ShapeManagerPanel;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

/**
 * Frame/Window of the application
 *
 * @author Ioan Sava
 */
@Getter
public class MainFrame extends JFrame {
    private ConfigurationPanel configurationPanel;
    private ControlPanel controlPanel;
    private DrawingPanel canvas;
    private ShapeManagerPanel shapeMangerPanel;

    public MainFrame() {
        super("My Paint");
        init();
    }

    private void initPanels() {
        canvas = new DrawingPanel(this);
        shapeMangerPanel = new ShapeManagerPanel(this);
        configurationPanel = new ConfigurationPanel(this);
        controlPanel = new ControlPanel(this);
    }

    /**
     * arrange the components in the container (frame)
     * JFrame uses a BorderLayout by default
     */
    private void addElementsInContainer() {
        add(canvas, BorderLayout.CENTER);
        add(shapeMangerPanel, BorderLayout.EAST);
        add(configurationPanel, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.SOUTH);
    }

    /**
     * Initialize the Mainframe Component
     * adding its children (panels)
     */
    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initPanels();
        addElementsInContainer();
        //invoke the layout manager
        pack();
    }
}
