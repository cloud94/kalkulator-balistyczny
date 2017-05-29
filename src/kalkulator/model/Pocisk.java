package kalkulator.model;

public abstract class Pocisk implements G1{
	
	double x, y, z = 0; // polozenie pocisku
	double Vx, Vy, Vz = 0; // predkosci pocisku
	double m = 0; // masa pocisku w gramach
	public double Ek = 0; // energia kinetyczna pocisku
	
	// konstruktor ustawiajacy mase pocisku
	Pocisk(double masa)
	{
		this.setM(masa);	
	}
	
	// konstruktor ustawiajacy mase i wspolrzedne pocisku
	Pocisk(double masa, double x, double y, double z)
	{
		this.setM(masa);
		this.setP(x, y, z);
	}
	
	// konstruktor ustawiajacy mase, polozenie i predkosc pocisku
	Pocisk(double masa, double x, double y, double z, double vx, double vy, double vz)
	{
		this.setM(masa);
		this.setP(x, y, z);
		this.setV(vx, vy, vz);
	}
	
	// metoda ustawiajaca nowa mase pocisku
	void setM(double nowaMasa)
	{
		if(nowaMasa < 0)
		{
			System.out.println("Masa nie moze byc ujemna");
			System.exit(0);
		}
		else
		m = nowaMasa;
	}
	
	// metoda ustawiajaca nowe polozenie pocisku
	void setP(double nx, double ny, double nz)
	{
		x = nx;
		y = ny;
		z = nz;
	}
	
	// metoda ustawiajaca predkosc pocisku
	void setV(double nvx, double nvy, double nvz)
	{
		Vx = nvx;
		Vy = nvy;
		Vz = nvz;
		Ek = (m/1000*(Math.pow((Vx+Vy+Vz), 2)))/2; // Ek = (m*v^2)/2
	}
	
	// metoda ustawiajaca energie kinetyczna pocisku
	void setE(double nE)
	{
		Ek = nE;
		Vx = GetVfromE(nE, this.m); //Math.sqrt((2*Ek)/(m/1000)); // pocisk porusza sie tylko po wsp x
		Vy = 0;
		Vz = 0;
		
	}
	
	// metoda wyswietlajaca parametry pocisku
	public void show()
	{
		System.out.println("Masa pocisku: "+this.m+" g");
		System.out.println("Polozenie pocisku (x, y, z): ("+this.x+" m, "+this.y+" m, "+this.z+" m)");
		System.out.println("Predkosc pocisku (Vx, Vy, Vz): ("+this.Vx+" m/s, "+this.Vy+" m/s, "+this.Vz+" m/s)");
		System.out.println("Energia kinetyczna: "+this.Ek);
	}
	
	// metoda abstrakcyjna symulujaca lot pocisku na odleglosci 1 metra
	public abstract void simulate();

	//metoda abstrakcyjna ustawiajaca polozenie oraz predkosc strzalu
	public abstract void shot(double v);
	

}

