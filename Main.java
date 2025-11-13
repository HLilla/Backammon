package jatek;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Backgammon");
        frame.setSize(900, 700);  // A méretezés beállítása
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);  // Képernyő közepére helyezés
        
        // Panel létrehozása
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Backgammon tábla
        Tabla tabla = new Tabla();
        mainPanel.add(tabla, BorderLayout.CENTER);  // Tábla a közepére

        // Korongok létrehozása és hozzáadása a táblához
        ArrayList<Korong> korongok = new ArrayList<>();
        korongok.add(new Korong(100, 100, Color.RED));  // Példa korong
        korongok.add(new Korong(200, 200, Color.BLACK));  // Példa korong
        tabla.setKorongok(korongok);  // Korongok beállítása

        // Dobókocka panel létrehozása
        Kocka dobokockaPanel = new Kocka();
        mainPanel.add(dobokockaPanel, BorderLayout.WEST);  // A dobókocka panel az aljára
        
        frame.add(mainPanel);
        /*
        Menu menu=new Menu();
        mainPanel.add (menu, BorderLayout.CENTER);*/
        // Bezáráskor lépjen ki
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Ablak megjelenítése
        frame.setVisible(true);
    }
}


