package kalkulator.model;

public class PociskAK extends Pocisk{
	
	double wsp = 0.015; // wspolcczynnik straty energii kinetycznej
	
	// konstruktor klasy PociskAK
	public PociskAK(double masa, double x, double y, double z) {
		super(masa, x , y, z);
	}


	// metoda symulujaca lot pocisku na odleglosci 1 metra
	@Override public void simulate()
	{
		this.x++;
		this.setE(GetNewE(Ek, wsp));
		//System.out.println("Została wywołana metoda simulate z klasy PociskAK.");
	}
	
	//metoda ustawiajaca polozenie oraz predkosc strzalu
	public void shot(double v)
	{
		this.setV(v, 0, 0);
	}

}
