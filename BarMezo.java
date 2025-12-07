package jatek;

public class BarMezo extends Mezo {

    // BAR vizuális helyei
    private Koordinate aranyhely;
    private Koordinate feketehely;

    public BarMezo() {
        super(24, 0, 0, new Koordinate(450, 200));
        aranyhely = new Koordinate(425, 230);
        feketehely = new Koordinate(420, 390);
    }

    // BAR nem valós mező,de van indexe
    @Override
    public int getSzam() {
        return 24;
    }

    public Koordinate getAranyHely() {
        return aranyhely;
    }

    public Koordinate getFeketeHely() {
        return feketehely;
    }

    @Override
    public boolean lepheto(Korong k) {
        return true;
    }
    @Override
    public boolean adArany() {
        arany++;
        return true;
    }

    @Override
    public boolean adFekete() {
        fekete++;
        return true;
    }
}
