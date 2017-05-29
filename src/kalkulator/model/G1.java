package kalkulator.model;

public interface G1 {

	// metoda obliczajaca nowa energie kinetyczna
	public default double GetNewE(double E, double wsp)
	{
		double newE = E * (1 - wsp);
		return newE;
	}
	
	// metoda obliczajaca predkosc na podstawie energii kinetycznej
	public default double GetVfromE(double E, double m)
	{
		return Math.sqrt((2*E)/(m/1000));
	}
	
	// metoda obliczajaca energie kinetczna na podstawie predkosci
	public default double GetEfromV(double V, double m)
	{
		return (m/1000*(Math.pow(V, 2)))/2;
	}
	
	public void simulate();
}
