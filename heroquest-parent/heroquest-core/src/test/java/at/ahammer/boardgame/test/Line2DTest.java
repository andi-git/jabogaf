package at.ahammer.boardgame.test;

import org.junit.Test;

import java.awt.geom.Line2D;

/**
 * Created by andreas on 8/29/14.
 */
public class Line2DTest {

    @Test
    public void testLine2D() {
        double fromX = 1.5;
        double fromY = 0.5;
        double toX = 2.5;
        double toY = 2.5;
        Line2D.Double line = new Line2D.Double(fromX, fromY, toX, toY);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5 ; j++) {
                System.out.println(i + "," + j + ": " + line.intersects(i, j, 1.0, 1.0));
            }
        }
    }
}
