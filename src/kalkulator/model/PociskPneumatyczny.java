package kalkulator.model;

public class PociskPneumatyczny extends Pocisk {

	double wsp = 0.017; // wspolcczynnik straty energii kinetycznej

	// konstruktor klasy PociskPneumatyczny
	public PociskPneumatyczny(double masa, double x, double y, double z) {
		super(masa, x, y, z);
	}
	
	@Override public void simulate()
	{
		this.x++;
		this.setE(GetNewE(Ek, wsp));
		//System.out.println("Została wywołana metoda simulate z klasy PociskPneumatyczny.");
	}
	
	public void shot(double v)
	{
		this.setV(v, 0, 0);
	}
	
}
