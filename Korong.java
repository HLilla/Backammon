package jatek;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Korong {
	Koordinate k;
    private int szin;  // Korong színe
    private BufferedImage kep; //Korong képe

    public Korong(Koordinate k, int szin) {
        this.szin = szin;
        this.k=k;
        try {
        	if (szin==0) {
        		kep = ImageIO.read(new File("arany.png"));
            }
        	else {
        		kep = ImageIO.read(new File("fekete.png"));
        	}
        } catch (IOException ex) {
            System.err.println("Nem sikerült betölteni a képet");
        }
    }
    public int getSzin() {return szin;}
    public BufferedImage getKep() {return kep;}
    public void setKoordinate(Koordinate k) {this.k=k;}
    public Koordinate getKoordinate() {return k;}
}
