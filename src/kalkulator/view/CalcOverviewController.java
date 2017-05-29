package kalkulator.view;

import java.awt.TextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import kalkulator.MainApp;

public class CalcOverviewController {
	
	ObservableList<String> options = 
		    FXCollections.observableArrayList(
		        "AK-47",
		        "M4",
		        "Broń pneumatyczna"
		    );
	
	@FXML
	private ComboBox rodzajPocisku;
	
//	@FXML
//	private TextField masa;
//	
//	@FXML
//	private TextField predkosc;
//	
//	@FXML
//	private TextField pozycja;
	
	
	
	private MainApp mainApp;

	//konstruktor
	//@return
	public void Controller(){
		
	}
	
	@FXML
    private void initialize() {
		
		rodzajPocisku.setValue("Wybierz rodzaj pocisku");
		rodzajPocisku.setItems(options);
    }

}
