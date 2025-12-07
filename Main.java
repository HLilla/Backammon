package jatek;

import javax.swing.*;
import java.awt.*;

public class Main {
	private static JPanel mainPanel;
	private static CardLayout cl;
	public static void visszaMenu() {
	    cl.show(mainPanel, "MENU");
	}
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Backgammon");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 700);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);

            // CardLayout a panelváltáshoz
            mainPanel = new JPanel();
            cl = new CardLayout();
            mainPanel.setLayout(cl);

            // Menu panel
            Menu menu = new Menu(mainPanel);
            mainPanel.add(menu, "MENU");
            int option = menu.valasztottSzam;

            // Tabla panel létrehozása (LayeredPane)
            JPanel gamePanel = createGamePanel();
            
            if(option==2 || option==4) {
            	Jatek.vanRobot=true;
            	}
            mainPanel.add(gamePanel, "TABLA");

            frame.setContentPane(mainPanel);
            frame.setVisible(true);
        });
    }

    // Segédfüggvény a játék panel létrehozásához
    public static JPanel createGamePanel() {
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
        layerPane.add(jatekPanel, JLayeredPane.PALETTE_LAYER); // magasabb réteg

        // Dobókocka réteg
        Kocka dobokockaPanel = new Kocka();
        dobokockaPanel.setOpaque(false);
        dobokockaPanel.setBounds(100, 210, 692, 256);
        layerPane.add(dobokockaPanel, JLayeredPane.MODAL_LAYER);

        /// Kiírás panel a bal oldalra
        Kiiras kiirasPanel = new Kiiras();
        kiirasPanel.setBounds(0, 0, 150, 700);

        // Wrapper panel BorderLayout-tal
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(kiirasPanel, BorderLayout.WEST);      // bal oldalra
        wrapper.add(layerPane, BorderLayout.CENTER);      // középre a játék

        return wrapper;
    }
}

