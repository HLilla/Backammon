package jatek;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel{
    public enum lehet{
        ONEPLAYER,
        TWOPLAYER,
        SAVEDONEPLAYER,
        SAVEDTWOPLAYER,
        QUIT
    }
    public Menu(){
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
        gbc.insets = new Insets(10, 0, 10, 0); // space between buttons
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridy = 0; add(op, gbc);
        gbc.gridy = 1; add(tp, gbc);
        gbc.gridy = 2; add(sop, gbc);
        gbc.gridy = 3; add(stp, gbc);
        gbc.gridy = 4; add(q, gbc);

        op.addActionListener(e -> onSelect(lehet.ONEPLAYER));
        tp.addActionListener(e -> onSelect(lehet.TWOPLAYER));
        sop.addActionListener(e -> onSelect(lehet.SAVEDONEPLAYER));
        stp.addActionListener(e -> onSelect(lehet.SAVEDTWOPLAYER));
        q.addActionListener(e -> onSelect(lehet.QUIT));
    }

     private void onSelect(lehet option) {
        switch (option) {
            case ONEPLAYER -> JOptionPane.showMessageDialog(this, "Starting 1 Player game...");
            case TWOPLAYER -> JOptionPane.showMessageDialog(this, "Starting 2 Player game...");
            case SAVEDONEPLAYER -> JOptionPane.showMessageDialog(this, "Loading saved 1P game...");
            case SAVEDTWOPLAYER -> JOptionPane.showMessageDialog(this, "Loading saved 2P game...");
            case QUIT -> System.exit(0);
        }
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


