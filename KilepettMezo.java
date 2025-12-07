package jatek;

public class KilepettMezo extends Mezo {
    private int kezdoX;
    private int kezdoY;
    static int aranyDB = 0;
    static int feketeDB = 0;

    public KilepettMezo(int x, int y) {
        super(-1, 0, 0, new Koordinate(x, y)); // mező index nem számít
        this.kezdoX = x;
        this.kezdoY = y;
    }

    public Koordinate ujKoordinate(Korong k) {
        int eltolas = 30;
        if (k.getSzin() == 0) {
            aranyDB++;
            return new Koordinate(kezdoX, kezdoY + (aranyDB - 1) * eltolas);
        } else {
            feketeDB++;
            return new Koordinate(kezdoX + 40, kezdoY + (feketeDB - 1) * eltolas);
        }
    }

    // Ha szükséges, visszaállíthatod a számlálókat új körre
    public void reset() {
        aranyDB = 0;
        feketeDB = 0;
    }
}
