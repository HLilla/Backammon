package jatek;

import java.awt.Color;

public class Korong {
    private int x, y;    // Korong pozíciója
    private Color szin;  // Korong színe

    public Korong(int x, int y, Color szin) {
        this.x = x;
        this.y = y;
        this.szin = szin;
    }
    public int getX() {return x;}
    public int getY() {return y;}
    public Color getSzin() {return szin;}
    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
    public void setSzin(Color szin) {this.szin = szin;}
}
