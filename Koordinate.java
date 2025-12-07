package jatek;

public class Koordinate {
	private int x;
	private int y;
	public Koordinate(int xe,int ye) {
		x=xe;
		y=ye;
	}
	public int getX() {return x;}
	public int getY() {return y;}
	public void setX(int e) {this.x=e;}
	public void setY(int e) {this.y=e;}
	public static Koordinate mezohelye(int szam) {
	    int x = 0;
	    int y = 0;
	    if (szam >= 12 && szam <= 17) {
	        y = 50;
	        x = 63 + 62 * (szam-12);
	    } else if (szam >= 18 && szam <= 23) {
	        y = 50;
	        x = 115 + 62 * (szam-12);

	    } else if (szam >= 6 && szam <= 11) {
	        y = 580;
	        x = 43 + 64 * (23-szam-12);

	    } else if (szam >= 0 && szam <= 5) {
	        y = 580;
	        x = 95 + 64 * (23-szam-12);
	    }

	    return new Koordinate(x, y);
	}

}
