package jatek;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.util.ArrayList;

public class Kiiras extends JPanel {
    private static JTextArea kore;
    private static JTextArea arany;
    private static JTextArea fekete;
 // Kilépett korongok megjelenítésére
    private static ArrayList<Korong> kilepettKorongok = new ArrayList<>();
    private static KilepettMezo kilepettMezo;

    public Kiiras() {
        setPreferredSize(new Dimension(100, 700));
        setOpaque(false);
        setLayout(null);

     // Kör kijelző
        kore = new JTextArea();
        kore.setBounds(10, 10, 80, 30); // nagyobb magasság
        kore.setEditable(false);
        kore.setLineWrap(true);
        kore.setWrapStyleWord(true);
        add(kore);

        // Arany korongok kijelző
        arany = new JTextArea();
        arany.setBounds(10, 60, 80, 40); 
        arany.setEditable(false);
        arany.setLineWrap(true);
        arany.setWrapStyleWord(true);
        add(arany);

        // Fekete korongok kijelző
        fekete = new JTextArea();
        fekete.setBounds(10, 120, 80, 40);
        fekete.setEditable(false);
        fekete.setLineWrap(true);
        fekete.setWrapStyleWord(true);
        add(fekete);

        // Kilépett korongokhoz mező a panelen
        kilepettMezo = new KilepettMezo(10, 190);
        
        frissit(); // kezdeti állapot
    }

    // Frissítő metódus a Jatek állapotának megjelenítésére
    public static void frissit() {
        // Kör
    	kore.setText((Jatek.koreVan == 0) ? "Arany köre" : "Fekete köre");
        // Korongok Kilépve
        arany.setText("Arany kilépett: " + KilepettMezo.aranyDB);
        fekete.setText("Fekete kilépett: " + KilepettMezo.feketeDB);
    }
    public static void uj() {
        // Kör
    	kore.setText("Arany köre");
        // Korongok Kilépve
    	KilepettMezo.aranyDB=0;
    	KilepettMezo.feketeDB=0;
        arany.setText("Arany kilépett: " + KilepettMezo.aranyDB);
        fekete.setText("Fekete kilépett: " + KilepettMezo.feketeDB);
    }
    public static void kilepett(Korong k) {
        // Átállítás a kilepett mező koordinátáira
        k.setKoordinate(kilepettMezo.ujKoordinate(k));
        kilepettKorongok.add(k);

        // Frissítés
        frissit();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Kilépett korongok kirajzolása
        for (Korong k : kilepettKorongok) {
            g.drawImage(k.getKep(), k.getKoordinate().getX(), k.getKoordinate().getY(), 60, 60, null);
        }
    }
}

