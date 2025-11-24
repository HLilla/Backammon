package jatek;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Jatek extends JPanel  {
	private ArrayList<Mezo> mezok = new ArrayList<>();
	private ArrayList<Korong> korongok = new ArrayList<>();
	public int koreVan=0;
	public static int kockaSzama=0;
    public Jatek() {
        setPreferredSize(new Dimension(900, 700));
        setOpaque(false);
        setLayout(null); 
        for (int i = 0; i < 24; i++) {
        	if (i==0) {
        		Mezo uj = new Mezo(i, 5, 0, Koordinate.mezohelye(i));
                mezok.add(uj);
        	}
        	else if(i==4) {
        		Mezo uj = new Mezo(i, 0, 3, Koordinate.mezohelye(i));
                mezok.add(uj);
        	}
        	else if(i==6) {
        		Mezo uj = new Mezo(i, 0, 5, Koordinate.mezohelye(i));
                mezok.add(uj);
        	}
        	else if(i==11) {
        		Mezo uj = new Mezo(i, 2, 0, Koordinate.mezohelye(i));
                mezok.add(uj);
        	}
        	else if(i==12) {
        		Mezo uj = new Mezo(i, 0, 5, Koordinate.mezohelye(i));
                mezok.add(uj);
        	}
        	else if(i==16) {
        		Mezo uj = new Mezo(i, 3, 0, Koordinate.mezohelye(i));
                mezok.add(uj);
        	}
        	else if(i==18) {
        		Mezo uj = new Mezo(i, 5, 0, Koordinate.mezohelye(i));
                mezok.add(uj);
        	}
        	else if(i==23) {
        		Mezo uj = new Mezo(i, 2, 0, Koordinate.mezohelye(i));
                mezok.add(uj);
        	}
        	else {
        		Mezo uj = new Mezo(i, 0, 0, Koordinate.mezohelye(i));
                mezok.add(uj);
        	}
          int eltolas=0;   
         // Arany korongok létrehozása
            for (int j = 0; j < mezok.get(i).getArany(); j++) {
                Korong k = new Korong(ujMezore(mezok.get(i), eltolas), 0);
                korongok.add(k);
                eltolas+=1;
            }
            eltolas=0;
            // Fekete korongok létrehozása
            for (int j = 0; j < mezok.get(i).getFekete(); j++) {
                Korong k = new Korong(ujMezore(mezok.get(i), eltolas), 1);
                korongok.add(k);
                eltolas+=1;
            }
        }
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                korongKattintas(e.getX(), e.getY());
            }
        });
    }
	 @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);

	        // Korongok kirajzolása
	        for (Korong k : korongok) {
	            Koordinate pos = k.getKoordinate();
	            g.drawImage(k.getKep(), pos.getX(), pos.getY(), 60, 60, null);
	        }
	    }

	    public ArrayList<Mezo> getMezok() {
	        return mezok;
	    }

	    public ArrayList<Korong> getKorongok() {
	        return korongok;
	    }
	    public Koordinate ujMezore(Mezo m, int hanyadik) {
	    	int elojel=(m.getSzan()<12)?1:-1;
        	int x=m.getKoordinate().getX();
        	int y=m.getKoordinate().getY()+(hanyadik*30*elojel);
        	return new Koordinate(x,y);
	    }
	    private void korongKattintas(int x, int y) {
	        if (kockaSzama == 0) return;
	        for (Korong k : korongok) {            
	            if (korongon(k,x,y)) {
	                if (k.getSzin() != koreVan) return;
	                int aktIndex = mezoIndex(k);
	                if (aktIndex == -1) return;
	                int ujIndex = aktIndex + kockaSzama;
	                if (ujIndex < 0 || ujIndex >= mezok.size()) return;
	                // regi mezo
	                Mezo regi = mezok.get(aktIndex);
	                if (k.getSzin() == 0) regi.minArany();
	                else                 regi.minFekete();
	                // ujmezo
	                Mezo cel = mezok.get(ujIndex);
	                int hanyadik = (k.getSzin() == 0) ? cel.getArany() : cel.getFekete();
	                //koordinata atallitas
	                k.setKoordinate(ujMezore(cel, hanyadik));
	                // uj mezohoz adas
	                if (k.getSzin() == 0) cel.adArany();
	                else                 cel.adFekete();
	                repaint();
	                return;
	            }
	        }
	    }
	    private int mezoIndex(Korong k) {
	        Koordinate pos = k.getKoordinate();
	        for (int i = 0; i < mezok.size(); i++) {
	            Koordinate m = mezok.get(i).getKoordinate();
	            if (pos.getX() >= m.getX() && pos.getX() <= m.getX() + 60) {
	                return i;
	            }
	        }
	        return -1;
	    }
	    private boolean korongon(Korong k, int x, int y) {
	    	int cx = k.getKoordinate().getX();
            int cy = k.getKoordinate().getY();
            // Kattintás beleesik-e a korong 60x60-as területére
            return (x >= cx && x <= cx + 60 && y >= cy && y <= cy + 60);
	    }
}
