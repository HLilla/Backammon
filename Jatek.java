package jatek;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Jatek extends JPanel  {
	private ArrayList<Mezo> mezok = new ArrayList<>();
	private ArrayList<Korong> korongok = new ArrayList<>();
	public int koreVan=0;
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
                Korong k = new Korong(ujMezore(mezok.get(i), eltolas), 0, mezok.get(i));
                korongok.add(k);
                eltolas+=1;
            }
            eltolas=0;
            // Fekete korongok létrehozása
            for (int j = 0; j < mezok.get(i).getFekete(); j++) {
                Korong k = new Korong(ujMezore(mezok.get(i), eltolas), 1, mezok.get(i));
                korongok.add(k);
                eltolas+=1;
            }
        }
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                korongKattintas(e.getX(), e.getY());
                System.out.println("Katt");
                Kocka.setBorder();
                Kocka.kijeloltszam=0;
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
	        System.out.println(Kocka.kijeloltszam);	        
	        if (Kocka.kijeloltszam == 0) return;

	        for (Korong k : korongok) {            
	            if (korongon(k,x,y)) {
	                if (k.getSzin() != koreVan) return;

	                int aktIndex = mezoIndex(k);
	                if (aktIndex == -1) return;

	                int irany = (k.getSzin() == 0) ? +1 : -1;
	                int ujIndex = aktIndex + irany * Kocka.kijeloltszam;
	                if (ujIndex < 0 || ujIndex >= mezok.size()) return;

	                // régi mező korongjainak frissítése
	                Mezo regi = mezok.get(aktIndex);
	                if (k.getSzin() == 0) regi.minArany();
	                else regi.minFekete();

	                // új mező korongjainak frissítése
	                Mezo cel = mezok.get(ujIndex);
	                if (k.getSzin() == 0) cel.adArany();
	                else cel.adFekete();

	                // mindkét mező korongjainak újrarajzolása
	                rajzolKorongokat(regi);
	                rajzolKorongokat(cel);

	                repaint();
	                return;
	            }
	        }
	    }

	    private int mezoIndex(Korong k) {
	    	Mezo mezo = k.getMezo();
	    	return mezo.getSzan();
	    }
	    private boolean korongon(Korong k, int x, int y) {
	    	int cx = k.getKoordinate().getX();
            int cy = k.getKoordinate().getY();
            // Kattintás beleesik-e a korong 60x60-as területére
            return (x >= cx && x <= cx + 60 && y >= cy && y <= cy + 60);

	    }
	    private void rajzolKorongokat(Mezo m) {
	        korongok.removeIf(k -> k.getMezo() == m);
	        // arany korongok újragenerálása
	        for (int i = 0; i < m.getArany(); i++) {
	            Korong k = new Korong(ujMezore(m, i), 0, m);
	            korongok.add(k);
	        }
	        // fekete korongok újragenerálása
	        for (int i = 0; i < m.getFekete(); i++) {
	            Korong k = new Korong(ujMezore(m, i), 1, m);
	            korongok.add(k);
	        }
	    }
}
