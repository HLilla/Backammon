package jatek;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Tabla extends JPanel {
    private BufferedImage kep;

    public Tabla() {
        try {
            kep = ImageIO.read(new File("tabla.png"));
        } catch (IOException ex) {
            System.err.println("Nem sikerült betölteni a képet");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        if (kep != null) {
            int panelW = getWidth();
            int panelH = getHeight();

            g2d.drawImage(kep, 0, 0, panelW, panelH, this);
        }
        g2d.dispose();
    }
}