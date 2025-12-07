package jatek;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel{
	private JPanel Panel;
	public int valasztottSzam=-1;
    public enum lehet{
        ONEPLAYER,
        TWOPLAYER,
        SAVEDONEPLAYER,
        SAVEDTWOPLAYER,
        QUIT
    }
    public Menu(JPanel mainPanel) {
        this.Panel = mainPanel;
        setPreferredSize(new Dimension(800, 500));
        setBackground(new Color(200, 160, 100));
        setLayout(new GridBagLayout());

        JButton op = new JButton("Játék egy játékossal");
        JButton tp = new JButton("Játék két játékossal");
        JButton sop = new JButton("Mentett játék egy játékossal");
        JButton stp = new JButton("Mentett játék két játékossal");
        JButton q = new JButton("Kilépés");

        Font font = new Font("Arial", Font.BOLD, 22);
        JButton[] buttons = {op, tp, sop, stp, q};

        FontMetrics fm = getFontMetrics(font);
        int maxWidth = 0;
        for (JButton b : buttons) {
            int textWidth = fm.stringWidth(b.getText());
            if (textWidth > maxWidth) {
                maxWidth = textWidth;
            }
        }

        int buttonWidth = maxWidth + 60;
        int buttonHeight = 50;

        for (JButton gomb : buttons) {
            gomb.setFont(font);
            gomb.setFocusPainted(false);
            gomb.setBackground(new Color(92, 51, 23));
            gomb.setForeground(new Color(255, 253, 208));
            gomb.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0); // gombok közti hely
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridy = 0; add(op, gbc);
        gbc.gridy = 1; add(tp, gbc);
        gbc.gridy = 2; add(sop, gbc);
        gbc.gridy = 3; add(stp, gbc);
        gbc.gridy = 4; add(q, gbc);

        op.addActionListener(e -> {
        	valasztottSzam = onSelect(lehet.ONEPLAYER);
            switchToGame();
        });
        tp.addActionListener(e -> {
        	valasztottSzam = onSelect(lehet.TWOPLAYER);
            switchToGame();
        });
        sop.addActionListener(e -> {
        	valasztottSzam = onSelect(lehet.SAVEDONEPLAYER);
            switchToGame();
        });
        stp.addActionListener(e -> {
        	valasztottSzam = onSelect(lehet.SAVEDTWOPLAYER);
            switchToGame();
        });
        q.addActionListener(e -> System.exit(0));
    }
    
    private void switchToGame() {
    	JPanel newGamePanel = Main.createGamePanel();
    	Panel.remove(Panel.getComponentCount() - 1);
    	Panel.add(newGamePanel, "TABLA");
        CardLayout cl = (CardLayout) Panel.getLayout();
        cl.show(Panel, "TABLA");
    }

     private int onSelect(lehet option) {
    	    switch (option) {
            case ONEPLAYER:
                return 1;
            case TWOPLAYER:
                return 2;
            case SAVEDONEPLAYER:
                return 3;
            case SAVEDTWOPLAYER:
                return 4;
            case QUIT:
                System.exit(0);
                return -1; // nem fog ide elérni
        }
        return -1;
    }
     
        @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        g.setFont(new Font("Georgia", Font.BOLD, 36));
        g.setColor(new Color(92, 51, 23));
        String title = "Játék Menü";
        int textWidth = g.getFontMetrics().stringWidth(title);
        g.drawString(title, (width - textWidth) / 2, 100);
        
    }
}


