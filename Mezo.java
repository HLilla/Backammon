package jatek;

public class Mezo {
	private int szam;
	private Koordinate hely;
	private int arany;
	private int fekete;
	public Mezo(int s, int a, int f, Koordinate k) {
		hely=k;
		szam=s;
		fekete=f;
		arany=a;
	}
	public Koordinate getKoordinate() {return hely;}
	public int getSzan(){return szam;}
	public int getArany() {return arany;}
	public int getFekete() {return fekete;}
	public boolean lepheto(Korong k) {
		int csapat=k.getSzin();
		if (csapat==0){
			return arany<2;
		}
		return fekete<2;
	}
	public void adArany() {arany=arany+1;}
	public void adFekete() {fekete=fekete+1;}
	public void minArany() {arany=arany-1;}
	public void minFekete() {fekete=fekete-1;}

}
