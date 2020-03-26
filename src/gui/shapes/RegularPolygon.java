package gui.shapes;

import java.awt.*;

/**
 * A polygon is a plane shape (two-dimensional) with straight sides.
 * A regular polygon has all sides equal and all angles equal.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Regular_polygon">https://en.wikipedia.org/wiki/Regular_polygon</a>
 * @author Ioan Sava
 */
public class RegularPolygon extends Polygon {
    public RegularPolygon(int x0, int y0, int radius, int sides) {
        double alpha = 2 * Math.PI / sides;
        for (int i = 0; i < sides; ++i) {
            double x = x0 + radius * Math.cos(alpha * i);
            double y = y0 + radius * Math.sin(alpha * i);
            this.addPoint((int) x, (int) y);
        }
    }
}