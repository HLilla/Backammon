package jatek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Kocka extends JPanel {
    private int oldal;  // A dobókocka aktuális értéke
    private Random random;  // Random objektum a dobás véletlenszerűségéhez
    private JButton dobasGomb;  // A gomb, ami dobja a kockát

    // Konstruktor
    public Kocka() {
        random = new Random();
        oldal = 1;  // Kezdeti érték

        // Layout beállítás
        setLayout(new BorderLayout());
        
        // Dobás gomb
        dobasGomb = new JButton("Dobás");
        dobasGomb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dobas();  // Dobás végrehajtása
                repaint();  // Újrarajzolás
            }
        });

        // Hozzáadjuk a gombot és a címkét
        add(dobasGomb, BorderLayout.SOUTH);      // Gomb az aljára
    }

    // Dobás metódus (1 és 6 közötti értéket generál)
    private void dobas() {
        oldal = random.nextInt(6) + 1;
    }

    // A kocka grafikájának kirajzolása
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // A dobókocka megjelenítése
        int x = getWidth() / 2 - 50;  // Kocka X pozíció
        int y = getHeight() / 2 - 50;  // Kocka Y pozíció
        int size = 100;  // Kocka mérete

        // Kocka alap háttér
        g2d.setColor(Color.WHITE);
        g2d.fillRect(x, y, size, size);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, size, size);

        // A dobókocka oldalainak kirajzolása
        drawDots(g2d, x, y, size);
    }

    // A kockán lévő pöttyök kirajzolása
    private void drawDots(Graphics2D g2d, int x, int y, int size) {
        int offset = 20;  // A pöttyök közötti távolság

        switch (oldal) {
            case 1:
                g2d.fillOval(x + size / 2 - offset / 2, y + size / 2 - offset / 2, offset, offset);
                break;
            case 2:
                g2d.fillOval(x + offset, y + offset, offset, offset);
                g2d.fillOval(x + size - offset * 2, y + size - offset * 2, offset, offset);
                break;
            case 3:
                g2d.fillOval(x + offset, y + offset, offset, offset);
                g2d.fillOval(x + size - offset * 2, y + size - offset * 2, offset, offset);
                g2d.fillOval(x + size / 2 - offset / 2, y + size / 2 - offset / 2, offset, offset);
                break;
            case 4:
                g2d.fillOval(x + offset, y + offset, offset, offset);
                g2d.fillOval(x + size - offset * 2, y + offset, offset, offset);
                g2d.fillOval(x + offset, y + size - offset * 2, offset, offset);
                g2d.fillOval(x + size - offset * 2, y + size - offset * 2, offset, offset);
                break;
            case 5:
                g2d.fillOval(x + offset, y + offset, offset, offset);
                g2d.fillOval(x + size - offset * 2, y + offset, offset, offset);
                g2d.fillOval(x + offset, y + size - offset * 2, offset, offset);
                g2d.fillOval(x + size - offset * 2, y + size - offset * 2, offset, offset);
                g2d.fillOval(x + size / 2 - offset / 2, y + size / 2 - offset / 2, offset, offset);
                break;
            case 6:
                g2d.fillOval(x + offset, y + offset, offset, offset);
                g2d.fillOval(x + size - offset * 2, y + offset, offset, offset);
                g2d.fillOval(x + offset, y + size / 2 - offset / 2, offset, offset);
                g2d.fillOval(x + size - offset * 2, y + size / 2 - offset / 2, offset, offset);
                g2d.fillOval(x + offset, y + size - offset * 2, offset, offset);
                g2d.fillOval(x + size - offset * 2, y + size - offset * 2, offset, offset);
                break;
        }
    }

}
