package jatek;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Jatek extends JPanel  {
	 public static ArrayList<Korong> korongok = new ArrayList<>();
	 public static ArrayList<Mezo> mezok = new ArrayList<>();
	 static BarMezo bar= new BarMezo();
	 public static int koreVan=0;
	 public boolean aranyvege=false;
	 public boolean feketevege=false;
	 public static boolean vanRobot=false;
	 public static ArrayList<Integer> ervenyesLepesek = new ArrayList<>();
	 public Jatek() {
		korongok.clear();
	    mezok.clear();
	    ervenyesLepesek.clear();
	    bar = new BarMezo();
	    koreVan = 0;
	    Kiiras.frissit();
	    setPreferredSize(new Dimension(900, 700));
	    setOpaque(false);
	    setLayout(null);

	    inicializalMezok();
	    inicializalKorongok();
	    inicializalEseménykezelo();
	 }
	 @Override
	 protected void paintComponent(Graphics g) {
	        super.paintComponent(g);

	        // Korongok kirajzolása
	        for (Korong k : korongok) {
	            k.render(g);
	        }
	 }
	 private void inicializalMezok() {
		    for (int i = 0; i < 24; i++) {
		        int arany = 0;
		        int fekete = 0;

		        switch (i) {
		            case 12 -> arany = 5;
		            case 16 -> fekete = 3;
		            case 18 -> fekete = 5;
		            case 23 -> arany = 2;
		            case 11 -> fekete = 5;
		            case 7  -> arany = 3;
		            case 5  -> arany = 5;
		            case 0  -> fekete = 2;
		        }

		        Mezo uj = new Mezo(i, arany, fekete, Koordinate.mezohelye(i));
		        mezok.add(uj);
		    }
		    mezok.add(bar);
		}
	 private void inicializalKorongok() {
		    for (Mezo m : mezok) {
		        int arany = m.getArany();
		        int fekete = m.getFekete();

		        // Arany korongok
		        for (int j = 0; j < arany; j++) {
		            Korong k = new Korong(
		                ujKoordinataKoronghoz(m, j, null), 0, m);
		            korongok.add(k);
		        }

		        // Fekete korongok
		        for (int j = 0; j < fekete; j++) {
		            Korong k = new Korong(ujKoordinataKoronghoz(m, j, null), 1, m);
		            korongok.add(k);
		        }
		    }
		}
	 private void inicializalEseménykezelo() {
		    addMouseListener(new java.awt.event.MouseAdapter() {
		        @Override
		        public void mouseClicked(java.awt.event.MouseEvent e) {
		        	// --- Ha robot játszik feketén, akkor a kattintás tilos ---
		            if (vanRobot && koreVan == 1) return;
		            
		        	korongKattintas(e.getX(), e.getY());
		            Kocka.setBorder();
		            Kocka.kijeloltszam = 0;
		        }
		    });
		}
	 public static void feldolgozDobasok() {
		    ervenyesLepesek.clear();
		    int vanBar = (koreVan == 0 ? bar.getArany(): bar.getFekete());
		    // BAR-ról kell nézni
		    if (vanBar>0) {
		    	for (int i: Kocka.lepesek) {
		    		if(barbol(i)) {
		    			ervenyesLepesek.add(i);
		    		}
		    	}
		    	if(ervenyesLepesek.size()>=vanBar) {
		    		ervenyesLepesek.clear();
				    ervenyesLepesek.add(Kocka.lepesek.get(0));
				    ervenyesLepesek.add(Kocka.lepesek.get(1));
				    
				    // DUPLA eset
				    if (ervenyesLepesek.get(0) == ervenyesLepesek.get(1)) {
				    	ervenyesLepesek.add(ervenyesLepesek.get(0));
					    ervenyesLepesek.add(ervenyesLepesek.get(1));
				    }
				    return;
		    	}
		    	else {
		    		if (ervenyesLepesek.isEmpty()) {
		    		    koreVan = 1 - koreVan;
		    		    Kocka.marDobot = false;
		    		    Kocka.ujKor();
		    		    Kiiras.frissit();
		    		}
		    	}
		    }
		    // sima játék
    		ervenyesLepesek.clear();
		    ervenyesLepesek.add(Kocka.lepesek.get(0));
		    ervenyesLepesek.add(Kocka.lepesek.get(1));
		    
		    // DUPLA eset
		    if (ervenyesLepesek.get(0) == ervenyesLepesek.get(1)) {
		    	ervenyesLepesek.add(ervenyesLepesek.get(0));
			    ervenyesLepesek.add(ervenyesLepesek.get(1));
		    }
		    return;
    	}
	 public ArrayList<Mezo> getMezok() {
	        return mezok;
	    }
	 public ArrayList<Korong> getKorongok() {
	        return korongok;
	    }
	 public Koordinate ujKoordinataKoronghoz(Mezo m, int hanyadik, Korong k) {
	        if (m == bar) {
	            return (k.getSzin() == 0) ? bar.getAranyHely() : bar.getFeketeHely();
	        }
	        int elojel = (m.getSzam() < 12) ? -1 : 1;
	        int x = m.getKoordinate().getX();
	        int y = m.getKoordinate().getY() + (hanyadik * 30 * elojel);
	        return new Koordinate(x, y);
	    }
	 private void korongKattintas(int x, int y) {
		 	if (vanRobot && koreVan == 1) return;
	        // Nincs kijelölt dobás
	        if (Kocka.kijeloltszam == 0) return;
	        Korong k = kattintottKorong(x, y);
	        if (k == null) return;
	        if (k.getSzin() != koreVan) return;
	        System.out.println(kilephet(k, Kocka.kijeloltszam));
	        
	        // Kilepés vizsgálat
	        if (kilephet(k, Kocka.kijeloltszam)) {
	        	System.out.println("Z");
	            kilepes(k, Kocka.kijeloltszam);
	            return;
	        }
	        // --- BAR-ból való lépés --
	        boolean vanBar = (koreVan == 0 ? bar.getArany() > 0 : bar.getFekete() > 0);
	        if (vanBar) {
	            if (k.getMezo() != bar) return;     // csak BAR-ról léphet
	            int d = Kocka.kijeloltszam;
	            int celIndex = (koreVan == 0) ? (24 - d) : (d - 1);
	            if (celIndex < 0 || celIndex >= 24) return;
	            Mezo cel = mezok.get(celIndex);
	            if (!cel.lepheto(k)) return;
	            lepes(k, cel);
	           return;
	        }
	        // --- Sima lépés ---

	        int akt = k.getMezo().getSzam();
	        int irany = (k.getSzin() == 0) ? -1 : +1;

	        // Normál mezőre lépés
	        int celIndex = akt + irany * Kocka.kijeloltszam;
	        if (celIndex < 0 || celIndex >= 24) return;

	        Mezo cel = mezok.get(celIndex);
	        if (!cel.lepheto(k)) return;

	        lepes(k, cel);
	    }
	 private Korong kattintottKorong(int x, int y) {
	    for (Korong k : korongok) {
	        // Csak a soron lévő szín
	        if (k.getSzin() != koreVan) continue;

	        int cx = k.getKoordinate().getX();
	        int cy = k.getKoordinate().getY();

	        // 60×60-as négyzetben van-e a kattintás
	        if (x >= cx && x <= cx + 60 && y >= cy && y <= cy + 60) {
	           	  return k;

	        }
	     }
	     return null;
	 }
	 private Korong mezobolKorong(Mezo m, int szin) {
	        for (Korong k : korongok) {
	            if (k.getMezo().equals(m) && k.getSzin() == szin) {
	                return k;
	            }
	        }
	        return null;
	 }
	 private void rajzolKorongokat(Mezo m) {
	    int aranyDB = 0;
	    int feketeDB = 0;
	    for (Korong k : korongok) {
	        if (k.getMezo() == m) {
	        if (k.getSzin() == 0) {//Arany
	        	k.setKoordinate(ujKoordinataKoronghoz(m, aranyDB, k));
                aranyDB++;
	        } else { //Fekete
	        	k.setKoordinate(ujKoordinataKoronghoz(m, feketeDB, k));
                feketeDB++;
	            }
	        }
	    }
	    repaint();
	 }
	 private void barRaHelyez(Korong kiutott) {
	    Mezo regi = kiutott.getMezo();
	    if (kiutott.getSzin() == 0 && bar.adArany()) {
	        kiutott.setKoordinate(bar.getAranyHely());
	    } 
	    else if (kiutott.getSzin() == 1 && bar.adFekete()) {
	        kiutott.setKoordinate(bar.getFeketeHely());
	    }
	    kiutott.setMezo(bar);
	
	    rajzolKorongokat(regi);
	    rajzolKorongokat(bar);
	    repaint();
	 }
	 public static boolean barbol(int oldal) {
		 // --- Arany ---
	     if (koreVan == 0) {
	        if (bar.getArany() == 0) return false;
	        int celIndex = 24 - oldal;
	        if (celIndex < 0 || celIndex >= 24) return false;
	        for (Korong k : korongok) {
	            if (k.getMezo() == bar && k.getSzin() == 0) {
	                return mezok.get(celIndex).lepheto(k);
	            }
	        }
	        return false;
	     }
	     // --- Fekete ---
	     if (koreVan == 1) {
	     if (bar.getFekete() == 0) return false;
	     int celIndex = oldal - 1;
	     if (celIndex < 0 || celIndex >= 24) return false;
	     for (Korong k : korongok) {
	         if (k.getMezo() == bar && k.getSzin() == 1) {
	            return mezok.get(celIndex).lepheto(k);
	         }
	     }
	     return false;
	}

	    return false;
	 }
	 private void lepes(Korong k, Mezo cel) {
		Mezo regi = k.getMezo();
	    // --- Kiütés ---
	    if (k.getSzin() == 0 && cel.getFekete() == 1) {
	    	Korong kiutott = mezobolKorong(cel, 1);
	    	   if(cel.minFekete())barRaHelyez(kiutott);
	    }
	    else if (k.getSzin() == 1 && cel.getArany() == 1) {
	    	Korong kiutott = mezobolKorong(cel, 0);
	    	if(cel.minArany())barRaHelyez(kiutott);
	    }
	    // --- Régi mező csökkentése csak, ha nem a BAR-ról lép ---
	    if (k.getSzin() == 0) {
	            regi.minArany();
	        } else {
	            regi.minFekete();
	        }

	    	// --- Új mező növelése ---
			if (k.getSzin() == 0 && cel.adArany()) {}
			else if (cel.adFekete()) {}
		// --- Korong helyének frissítése ---
		k.setMezo(cel);
		
		// --- Újrarajzolás ---
    	rajzolKorongokat(regi);
    	rajzolKorongokat(cel);
    	repaint();
    	
    	// --- Dobás elhasználása ---
    	ervenyesLepesek.remove((Integer) Kocka.kijeloltszam);
    	Kocka.kijeloltszam = 0;
    	Kocka.setBorder();
	    	
    	// --- Körváltás ---
    	if (!vanMegLepes()) {
    		ervenyesLepesek.clear();
    		koreVan = 1 - koreVan;
    		Kocka.marDobot = false;
    		Kocka.ujKor();
    	}
	    // Kiiras frissítése
	    Kiiras.frissit();
	    
	    // --- ROBOT lép, ha fekete kör jött ---
	    if (vanRobot && koreVan == 1) {
	        //Robot.lepj();
	    }
	 }
	 public boolean kilephet(Korong k, int dobottErtek) {
		    if (k.getSzin() != koreVan) return false;
		    // "Haza" mezők határai
		    int minIndex = (k.getSzin() == 1) ? 18 : 0;
		    int maxIndex = (k.getSzin() == 1) ? 23 : 5;

		    // Ellenőrizzük, hogy minden saját színű korong a haza zónában van-e
		    for (Korong kor : korongok) {
		        if (kor.getSzin() != koreVan) continue;
		        int idx = kor.getMezo().getSzam();
		        if (idx < minIndex || idx > maxIndex) return false; // van még kint lévő korong
		    }
		    System.out.println(k.getMezo().getSzam());
		    if(koreVan==0) {aranyvege=true;}
		    else {feketevege=true;}
		    
		    // Ellenőrizzük, hogy a dobás elég-e a kihozatalhoz
		    int index = k.getMezo().getSzam();
		    if (k.getSzin() == 0) { // arany
		        return (index - dobottErtek <= -1);
		    } else { // fekete
		        return (index + dobottErtek >= 24);
		    }
		}
	 private boolean vanMegLepes() {
        // ha nincs dobáskocka → nincs lépés
        if (Kocka.lepesek.isEmpty()) return false;
        //VAN KORONG A BAR-ON? 
        boolean vanBar = (koreVan == 0 ? bar.getArany() > 0 : bar.getFekete() > 0);
        if (vanBar) {
            // Csak BAR-ról lehet lépni
            for (int d : ervenyesLepesek) {
                if (barbol(d)) return true; 
            }
            return false;
        }
        for (Korong k : korongok) {
            if (k.getSzin() != koreVan) continue;
            int akt = k.getMezo().getSzam();
            int irany = (k.getSzin() == 0) ? -1 : +1;
            for (int d : ervenyesLepesek) {
                int celIndex = akt + irany * d;
                if (celIndex >= 0 && celIndex < 24) {
                    if (mezok.get(celIndex).lepheto(k)) return true;
                }
            }
        }
        return false;
     }
	 private boolean kilepes(Korong k, int dobottErtek) {
        Mezo regi = k.getMezo();
        if(kilephet(k, dobottErtek)) {
        	// Régi mező csökkentése, nem lehet bar
        	if (k.getSzin() == 0 && regi.minArany()) {
	        	// Korong eltávolítása a játékból
		        korongok.remove(k);
		        Kiiras.kilepett(k);
        	}
        	else if (k.getSzin() == 1 && regi.minFekete()) {
	        	// Korong eltávolítása a játékból
		        korongok.remove(k);
		        Kiiras.kilepett(k);
        	}
        // Rajzolás frissítése
        rajzolKorongokat(regi);
        repaint();
        
        // Dobás elhasználása
        ervenyesLepesek.remove((Integer) dobottErtek);
        Kocka.kijeloltszam = 0;
        Kocka.setBorder();
        Kiiras.frissit();
        
        nyert();
        
    	// --- Körváltás ---
    	if (ervenyesLepesek.isEmpty()) {
    		ervenyesLepesek.clear();
    		koreVan = 1 - koreVan;
    		Kocka.marDobot = false;
    		Kocka.ujKor();
    		 // --- ROBOT lép, ha fekete kör jött ---
    	    if (vanRobot && koreVan == 1) {
    	        //Robot.lepj();
    	    }
    	}
        return true;
        }
        return false;
	 }
	 private void vegekor() {
		 	koreVan = 1 - koreVan;
		    Kocka.marDobot = false;
		    Kocka.ujKor();
		    Kiiras.frissit();
		    // --- ROBOT lép, ha fekete kör jött ---
		    if (vanRobot && koreVan == 1) {
		        //Robot.lepj();
		    }
	 }
	 private void nyert() {
		 int winner = -1;
		 int aranydb=0;
		 int feketedb=0;
		 for(Korong k: korongok) {
			 if (k.getSzin() == 0)
		            aranydb++;
		        else
		            feketedb++;
		 }
		 if (aranydb == 0) winner=0; // arany nyert
		 if (feketedb == 0) winner=1; // fekete nyert		    
		 if (winner != -1) {
		        JOptionPane.showMessageDialog(this,
		            (winner == 0 ? "Arany nyert!" : "Fekete nyert!"));
		        Main.visszaMenu();
		        return;
		    }
		 return;
	 }
	 
}
