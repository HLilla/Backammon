package jatek;

import javax.swing.*;
import java.awt.*;

public class Tabla extends JPanel {
    private final int NUM_TRIANGLES = 12;  // 12 felül, 12 alul = 24
    public Tabla() {
        setPreferredSize(new Dimension(800, 500));
        setBackground(new Color(200, 160, 100));
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        int barWidth = width / 13;
        int triangleWidth = (width - barWidth) / 12;
        int triangleHeight = height *2/5;
        // Felső sor
        for (int i = 0; i < NUM_TRIANGLES; i++) {
            int x = i * triangleWidth;
            if (i >= 6) x += barWidth;

            Polygon triangle = new Polygon();
            triangle.addPoint(x, 0);
            triangle.addPoint(x + triangleWidth, 0);
            triangle.addPoint(x + triangleWidth / 2, triangleHeight);

            g.setColor((i % 2 == 0) ? Color.WHITE : Color.DARK_GRAY);
            g.fillPolygon(triangle);
            g.setColor(Color.BLACK);
            g.drawPolygon(triangle);
        }
        // Alsó sor
        for (int i = 0; i < NUM_TRIANGLES; i++) {
            int x = i * triangleWidth;
            if (i >= 6) x += barWidth;

            Polygon triangle = new Polygon();
            triangle.addPoint(x, height);
            triangle.addPoint(x + triangleWidth, height);
            triangle.addPoint(x + triangleWidth / 2, height - triangleHeight);

            g.setColor((i % 2 == 0) ? Color.DARK_GRAY : Color.WHITE);
            g.fillPolygon(triangle);
            g.setColor(Color.BLACK);
            g.drawPolygon(triangle);
        }
        // Középső sáv (bar)
        g.setColor(new Color(120, 80, 20));
        g.fillRect((width - barWidth) / 2, 0, barWidth, height);
    }
}
