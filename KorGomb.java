package jatek;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;

class KorGomb extends JButton {

    public KorGomb(String text) {
        super(text);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
     // Fix méret beállítása
        Dimension d = new Dimension(100, 50);
        setPreferredSize(d);
        setMinimumSize(d);
        setMaximumSize(d);
        setSize(d);  // akkor is fix, ha null layout lenne
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // Antialiasing – szebb kör
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Kör háttér
        g2.setColor(new Color(201, 175, 122));           // háttérszín
        g2.fillOval(0, 0, getWidth(), getHeight());

        // Kör kontúr
        g2.setColor(Color.BLACK);           // vonal színe
        g2.drawOval(0, 0, getWidth()-1, getHeight()-1);

        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    public boolean contains(int x, int y) {
        int r = Math.min(getWidth(), getHeight()) / 2;
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        return (x - centerX)*(x - centerX) + (y - centerY)*(y - centerY) <= r*r;
    }
}
