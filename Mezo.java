package jatek;

public class Mezo {
	private int szam;
	private Koordinate hely;
	protected int arany;
	protected int fekete;
	public Mezo(int s, int a, int f, Koordinate k) {
		hely=k;
		szam=s;
		fekete=f;
		arany=a;
	}
	public Koordinate getKoordinate() {return hely;}
	public int getSzam(){return szam;}
	public int getArany() {return arany;}
	public int getFekete() {return fekete;}
	public boolean lepheto(Korong k) {
		int csapat=k.getSzin();
		if (csapat == 0) {
	        if (fekete >= 2) return false;
	        return true;
	    } else {
	        if (arany >= 2) return false;
	        return true;
	    }
	}
	 // Hozzáadás
    public boolean adArany() {
        if (fekete >= 2) return false;  // nem léphető
        arany++;
        return true;
    }

    public boolean adFekete() {
        if (arany >= 2) return false;  // nem léphető
        fekete++;
        return true;
    }

    // Levonás
    public boolean minArany() {
        if (arany <= 0) return false;   // nincs korong, nem lehet csökkenteni
        arany--;
        return true;
    }

    public boolean minFekete() {
        if (fekete <= 0) return false;  // nincs korong, nem lehet csökkenteni
        fekete--;
        return true;
    }
}