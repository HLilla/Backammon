package jatek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class Kocka extends JPanel {
    private static int oldal1;  // A dobókocka1 aktuális értéke
    private static int oldal2; // A dobókocka2 aktuális értéke
    private Random random;  // Random objektum a dobás véletlenszerűségéhez
    private static JButton dobasGomb;  // A gomb, ami dobja a kockát
    private static JLabel kockaKepe1; //Az első kocka gife
    private static JLabel kockaKepe2; // A második kocka gif-e
    public static boolean marDobot = false; //Történt e már dobás körben
    public static int kijeloltszam=0; //Mejik oldal érték van kijelölve
    public static ArrayList<Integer> lepesek = new ArrayList<>();
    
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
            	marDobot = true;
            	dobasGomb.setEnabled(false);
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
        
        kockaKepe1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Jatek.ervenyesLepesek.contains(oldal1)) {
                    Kocka.setBorder();   // előző kijelölés törlése
                    kijeloltszam = oldal1;
                    kockaKepe1.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                    // HA BARON van saját korong, legyen zöld jelzés és lerakható
                    if (Jatek.koreVan == 0 && Jatek.bar.getArany() > 0 && Jatek.barbol(oldal1)) {
                        kockaKepe1.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
                    }
                    else if (Jatek.koreVan == 1 && Jatek.bar.getFekete() > 0 && Jatek.barbol(oldal1)) {
                        kockaKepe1.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
                    }
                }
            }
        });

        kockaKepe2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Jatek.ervenyesLepesek.contains(oldal2)) {
                    Kocka.setBorder();   // előző kijelölés törlése
                    kijeloltszam = oldal2;
                    kockaKepe2.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                 // HA BARON van saját korong, legyen zöld jelzés és lerakható
                    if (Jatek.koreVan == 0 && Jatek.bar.getArany() > 0 && Jatek.barbol(oldal2)) {
                        kockaKepe2.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
                    }
                    else if (Jatek.koreVan == 1 && Jatek.bar.getFekete() > 0 && Jatek.barbol(oldal2)) {
                        kockaKepe2.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
                    }
                }
            }
        });
    }
    
    // Dobás metódus (1 és 6 közötti értéket generál)
    private void dobas() {
        oldal1 = random.nextInt(6) + 1;
        oldal2 = random.nextInt(6) + 1;
        lepesek.clear();
        lepesek.add(oldal1);
        lepesek.add(oldal2);
	    Jatek.feldolgozDobasok();

    }

    //Kockadobás megjelenitése
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
    public static void setBorder() {
    	kockaKepe1.setBorder(null);
    	kockaKepe2.setBorder(null);
    }
    public static void ujKor() {
        marDobot = false;
        dobasGomb.setEnabled(true);
        kijeloltszam = 0;
        setBorder();
    }
}
