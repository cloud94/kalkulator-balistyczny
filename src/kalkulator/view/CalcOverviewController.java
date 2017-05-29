package kalkulator.view;


import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import kalkulator.MainApp;
import kalkulator.model.Pocisk;
import kalkulator.model.PociskAK;

public class CalcOverviewController {

	
	@FXML
	private ComboBox rodzajPocisku;
	
	@FXML
	private TextField masa;
	
	@FXML
	private TextField predkosc;
	
	@FXML
	private TextField pozycja;
	
    @FXML
    private LineChart<String, Double> wykres;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;
	
	private MainApp mainApp;

	//konstruktor
	public void Controller(){
		
	}
	
	@FXML
    private void initialize() {
		
		rodzajPocisku.setValue("Wybierz rodzaj pocisku");
		rodzajPocisku.getItems().addAll(
				"AK-47", "M4", "Broń pneumatyczna");
	
    }
	
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
	
	@FXML
	private void onSymulujClick()
	{
		if(!rodzajPocisku.getSelectionModel().isEmpty())
		{
		int wybor = rodzajPocisku.getSelectionModel().getSelectedIndex();
		switch(wybor)
		{
		// ak-47
		case 0:
			if(!masa.getText().isEmpty() &&
					!pozycja.getText().isEmpty() &&
					!predkosc.getText().isEmpty())
			{
				double m = Double.valueOf(masa.getText());
				double poz = Double.valueOf(pozycja.getText());
				double v = Double.valueOf(predkosc.getText());
				
				Pocisk ak = new PociskAK(m, 0, poz, 0);
				
				ak.shot(v);
				double []energia = new double[50];
				
				XYChart.Series series = new XYChart.Series();
				
				series.setName("Energia kinetyczna");
				
				double wsp=m/v;
				
				for(int i=0; i<50; i++)
				{
					ak.simulate();
					energia[i]=ak.Ek;
					poz=poz-wsp*i;
					series.getData().add(new XYChart.Data(String.valueOf(i), poz));
					System.out.println(energia[i]);
				}
				wykres.setTitle("Wykres energii kinetycznej pocisku AK-47");
				wykres.getData().addAll(series);
			}
			else
	    	{
	    		// nic nie zaznaczono
	    		Alert alert = new Alert(AlertType.WARNING);
	    		alert.initOwner(mainApp.getPrimaryStage());
	            alert.setTitle("Błąd");
	            alert.setContentText("Proszę wprowadzić wszystkie parametry pocisku.");

	            alert.showAndWait();
	    	}
	
			break;
		// m4
		case 1:
			System.out.println(wybor);
			break;
		// pneumatyczna
		case 2:
			System.out.println(wybor);
			break;
		}
		}
		
	}

}
