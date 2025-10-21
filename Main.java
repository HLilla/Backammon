package jatek;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Backgammon");

        Tabla tabla = new Tabla();
        frame.add(tabla);

        // Bezáráskor lépjen ki
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Méretezés
        frame.pack();

        // Képernyő közepére helyezés
        frame.setLocationRelativeTo(null);

        // Ablak megjelenítése
        frame.setVisible(true);
    }
}