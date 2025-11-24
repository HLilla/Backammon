package jatek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Kocka extends JPanel {
    private int oldal1;  // A dobókocka aktuális értéke
    private int oldal2;
    private Random random;  // Random objektum a dobás véletlenszerűségéhez
    private JButton dobasGomb;  // A gomb, ami dobja a kockát
    private JLabel kockaKepe1;
    private JLabel kockaKepe2;
    
    // Konstruktor
    public Kocka() {
        // Átlátszó panel
        setOpaque(false);
        setBackground(new Color(0, 0, 0, 0));
        setLayout(new BorderLayout());
        
        random = new Random();
        oldal1 = 1;  // Első kocka kezdeti érték
        oldal2 = 1;  //Második kocka kezdeti érték
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        // Kocka1
        kockaKepe1 = new JLabel();
        kockaKepe1.setOpaque(false);
        kockaKepe1.setPreferredSize(new Dimension(256, 256));
        kockaKepe1.setMaximumSize(new Dimension(256, 256));
        kockaKepe1.setMinimumSize(new Dimension(256, 256));
        add(kockaKepe1);
                
        add(Box.createHorizontalStrut(50));
        
        // Dobás gomb
        dobasGomb = new KorGomb("Dobás"); 
        add(dobasGomb);
        dobasGomb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dobas();
                kirajzol();
            }
        });
        
        add(Box.createHorizontalStrut(30));
                
        // Kocka2
        kockaKepe2 = new JLabel();
        kockaKepe2.setOpaque(false);
        kockaKepe2.setPreferredSize(new Dimension(256, 256));
        kockaKepe2.setMaximumSize(new Dimension(256, 256));
        kockaKepe2.setMinimumSize(new Dimension(256, 256));
        add(kockaKepe2);
        
        kockaKepe1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
            	Jatek.kockaSzama=oldal1;
            }
        });

        kockaKepe2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                Jatek.kockaSzama=oldal2;
            }
        });
    }
    
    

    // Dobás metódus (1 és 6 közötti értéket generál)
    private void dobas() {
        oldal1 = random.nextInt(6) + 1;
        oldal2 = random.nextInt(6) + 1;
    }

    //Kocka megjelenitése
 
    private ImageIcon melyik(int oldal) {
    	ImageIcon icon = null;
        switch (oldal) {
            case 1:
            	icon = new ImageIcon("egy.gif");
            	break;
            case 2:
            	icon = new ImageIcon("ketto.gif");
            	break;
            case 3:
            	icon = new ImageIcon("harom.gif");
            	break;
            case 4:
            	icon = new ImageIcon("negy.gif");
            	break;
            case 5:
            	icon = new ImageIcon("ot.gif");
            	break;
            case 6:
            	icon = new ImageIcon("hat.gif");
            	break;
        }
        return icon;
    }
    
    private void kirajzol() {
    	ImageIcon icon1 = melyik(oldal1);
        ImageIcon icon2 = melyik(oldal2);
        kockaKepe1.setIcon(icon1);
        kockaKepe2.setIcon(icon2);
        revalidate();
        repaint();
    }

}
