package kalkulator.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Lot {
	private final IntegerProperty x;
	private final DoubleProperty y;
	
	public Lot(int x, double y)
	{
		this.x=new SimpleIntegerProperty(x);
		this.y=new SimpleDoubleProperty(y);
	}

	public IntegerProperty xProperty() {
		// TODO Auto-generated method stub
		return x;
	}
	
	public DoubleProperty yProperty() {
		// TODO Auto-generated method stub
		return y;
	}
}

