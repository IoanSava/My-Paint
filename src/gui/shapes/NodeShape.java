package gui.shapes;

import lombok.Getter;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * This shape could represents a
 * node/vertex in a graph
 *
 * @author Ioan Sava
 */
@Getter
public class NodeShape extends Ellipse2D.Double implements Shape {
    private Color color;
    public NodeShape(double x0, double y0, double radius, Color color) {
        super(x0 - radius / 2, y0 - radius / 2, radius, radius);
        this.color = color;
    }
}
