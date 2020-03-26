package gui.panels;

import gui.MainFrame;
import gui.shapes.NodeShape;
import gui.shapes.RegularPolygon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.Random;

/**
 * A canvas (drawing panel) for
 * drawing shapes
 *
 * @author Ioan Sava
 */
public class DrawingPanel extends JPanel {
    private final MainFrame frame;
    /**
     * default width and height
     */
    final static int WIDTH = 800;
    final static int HEIGHT = 600;

    /**
     * the offscreen image
     */
    BufferedImage image;

    /**
     * the "tools" needed to draw in the image
     */
    Graphics2D graphics;

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        init();
    }

    private void createOffscreenImage() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE); //fill the image with white
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
        //smooth graphic
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    private void init() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBorder(BorderFactory.createEtchedBorder());
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                drawShape(event.getX(), event.getY());
                repaint();
            }
        });
    }

    /**
     * Generate a random color using
     * RGB color model
     * @see <a href="https://en.wikipedia.org/wiki/RGB_color_model">https://en.wikipedia.org/wiki/RGB_color_model</a>
     */
    private Color generateRandomColor() {
        Random random = new Random();
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        return new Color(r, g, b);
    }

    private Color getShapeColor() {
        if (Objects.equals(frame.getConfigurationPanel().getColorCombo().getSelectedItem(), "Black")) {
            return Color.BLACK;
        } else {
            return generateRandomColor();
        }
    }

    /**
     * Draw a shape according to the selected option
     * in the shape manager panel radio.
     */
    private void drawShape(int x, int y) {
        Random random = new Random();
        int radius = random.nextInt(90) + 30; //generate a random angle
        graphics.setColor(getShapeColor());

        if (frame.getShapeMangerPanel().getRegularPolygonRadioButton().isSelected()) {
            int sides = (int) frame.getConfigurationPanel().getSidesField().getValue();
            graphics.fill(new RegularPolygon(x, y, radius, sides));
        } else {
            graphics.fill(new NodeShape(x, y, radius));
        }
    }

    @Override
    public void update(Graphics g) { } //optimization

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }

    public void resetPanel() {
        graphics.setColor(Color.WHITE); //fill the image with white
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
        repaint();
    }

    /**
     * Load an image into the canvas
     */
    public void loadImage(BufferedImage image) {
        this.image = image;
        graphics = image.createGraphics();
        repaint();
    }
}
