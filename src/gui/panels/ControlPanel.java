package gui.panels;

import gui.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A control panel for managing the image being created
 *
 * @author Ioan Sava
 */
public class ControlPanel extends JPanel {
    private final MainFrame frame;
    private JButton loadButton = new JButton("Load");
    private JButton saveButton = new JButton("Save");
    private JButton resetButton = new JButton("Reset");
    private JButton exitButton = new JButton("Exit");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void addElementsToContainer() {
        add(loadButton);
        add(saveButton);
        add(resetButton);
        add(exitButton);
    }

    private void addListenersToButtons() {
        loadButton.addActionListener(this::load);
        saveButton.addActionListener(this::save);
        resetButton.addActionListener(this::reset);
        exitButton.addActionListener(this::exit);
    }

    /**
     * Initialize ControlPanel buttons
     * and add event listeners
     */
    private void init() {
        setLayout(new GridLayout(1, 4));
        addElementsToContainer();
        addListenersToButtons();
    }

    /**
     * Load an image into the canvas
     * using a file chooser
     */
    private void load(ActionEvent event) {
        BufferedImage image = null;
        try {
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            fileChooser.setDialogTitle("Select an image");
            fileChooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG images", "png");
            fileChooser.addChoosableFileFilter(filter);

            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                image = ImageIO.read(new File(fileChooser.getSelectedFile().getPath()));
                frame.getCanvas().loadImage(image);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * Save the current state of the canvas
     * using a file chooser
     */
    private void save(ActionEvent event) {
        try {
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            fileChooser.setDialogTitle("Choose a directory to save your file");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int returnValue = fileChooser.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                ImageIO.write(frame.getCanvas().image, "PNG",
                        new File(fileChooser.getSelectedFile() + "\\test.png"));
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * Reset the canvas
     */
    private void reset(ActionEvent event) {
        frame.getCanvas().resetPanel();
    }

    /**
     * Exit the application
     */
    private void exit(ActionEvent event) {
        System.exit(0);
    }
}
