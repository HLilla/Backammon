package jatek;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Backgammon");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 700);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);

            // CardLayout a panelváltáshoz
            JPanel mainPanel = new JPanel(new CardLayout());

            // Menu panel
            Menu menu = new Menu(mainPanel);
            mainPanel.add(menu, "MENU");
            int option = menu.valasztottSzam;

            // Tabla panel létrehozása (LayeredPane)
            JPanel gamePanel = createGamePanel();
            mainPanel.add(gamePanel, "TABLA");

            frame.setContentPane(mainPanel);
            frame.setVisible(true);
        });
    }

    // Segédfüggvény a játék panel létrehozásához
    private static JPanel createGamePanel() {
        JLayeredPane layerPane = new JLayeredPane();
        layerPane.setPreferredSize(new Dimension(900, 700));

        // Tábla réteg
        Tabla tabla = new Tabla();
        tabla.setBounds(0, 0, 900, 700);
        layerPane.add(tabla, JLayeredPane.DEFAULT_LAYER);
        
     // Játék réteg (korongok)
        Jatek jatekPanel = new Jatek();
        jatekPanel.setBounds(0, 0, 900, 700);
        jatekPanel.setOpaque(false); // átlátszó, hogy a tábla látszódjon
        layerPane.add(jatekPanel, JLayeredPane.MODAL_LAYER); // magasabb réteg

        // Dobókocka réteg
        Kocka dobokockaPanel = new Kocka();
        dobokockaPanel.setOpaque(false);
        dobokockaPanel.setBounds(100, 210, 692, 256);
        layerPane.add(dobokockaPanel, JLayeredPane.PALETTE_LAYER);

        // Új JPanel, ami tartalmazza a JLayeredPane-t
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(layerPane, BorderLayout.CENTER);
        return wrapper;
    }
}

