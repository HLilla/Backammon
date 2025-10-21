package jatek;

import java.awt.*;

public class Korong{

    private int x; // Korong x pozíciója
    private int y; // Korong y pozíciója
    private Color szin; //Korong szine
    private final int DIAMETER = 30; // Korong mérete

    public Korong(int csapat, int xhol, int yhol) {
        this.szin = (csapat == 1) ? Color.WHITE : Color.BLACK;
        this.x=xhol;
        this.y=yhol;
    }
    
    public void kirajzol(Graphics g) {
        g.setColor(szin);
        g.fillOval(x, y, DIAMETER, DIAMETER);
    }

    // Pozíciók getterei
    public int getX() {return x;}
    public int getY() {return y;}
}