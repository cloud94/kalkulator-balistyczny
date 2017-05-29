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
import kalkulator.model.PociskM4;
import kalkulator.model.PociskPneumatyczny;

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
				
				series.setName("AK-47, "+m+"g, Vo="+m+"m, Ho="+poz+"m");
				
				double wsp=m/v;
				
				for(int i=0; i<50; i++)
				{
					ak.simulate();
					energia[i]=ak.Ek;
					poz=poz-wsp*i-0.011;
					series.getData().add(new XYChart.Data(String.valueOf(i), poz));
				}
				wykres.setTitle("Wykres energii kinetycznej pocisku AK-47");
				wykres.getData().addAll(series);
			}
			else
	    	{
	    		// nic nie zaznaczono
	    		Alert alert = new Alert(Alert.AlertType.WARNING);
	    		alert.initOwner(mainApp.getPrimaryStage());
	            alert.setTitle("Błąd");
	            alert.setContentText("Proszę wprowadzić wszystkie parametry pocisku.");

	            alert.showAndWait();
	    	}
	
			break;
		// m4
		case 1:
			if(!masa.getText().isEmpty() &&
					!pozycja.getText().isEmpty() &&
					!predkosc.getText().isEmpty())
			{
				double m = Double.valueOf(masa.getText());
				double poz = Double.valueOf(pozycja.getText());
				double v = Double.valueOf(predkosc.getText());
				
				Pocisk m4 = new PociskM4(m, 0, poz, 0);
				
				m4.shot(v);
				double []energia = new double[50];
				
				XYChart.Series series = new XYChart.Series();
				
				series.setName("M4, "+m+"g, Vo="+m+"m, Ho="+poz+"m");
				
				double wsp=m/v;
				
				for(int i=0; i<50; i++)
				{
					m4.simulate();
					energia[i]=m4.Ek;
					poz=poz-wsp*i-0.015;
					series.getData().add(new XYChart.Data(String.valueOf(i), poz));
				}
				wykres.setTitle("Wykres energii kinetycznej pocisku M4");
				wykres.getData().addAll(series);
			}
			else
	    	{
	    		// nic nie zaznaczono
	    		Alert alert = new Alert(Alert.AlertType.WARNING);
	    		alert.initOwner(mainApp.getPrimaryStage());
	            alert.setTitle("Błąd");
	            alert.setContentText("Proszę wprowadzić wszystkie parametry pocisku.");

	            alert.showAndWait();
	    	}
			break;
		// pneumatyczna
		case 2:
			if(!masa.getText().isEmpty() &&
					!pozycja.getText().isEmpty() &&
					!predkosc.getText().isEmpty())
			{
				double m = Double.valueOf(masa.getText());
				double poz = Double.valueOf(pozycja.getText());
				double v = Double.valueOf(predkosc.getText());
				
				Pocisk pn = new PociskPneumatyczny(m, 0, poz, 0);
				
				pn.shot(v);
				double []energia = new double[50];
				
				XYChart.Series series = new XYChart.Series();
				
				series.setName("P. pneum., "+m+"g, Vo="+m+"m, Ho="+poz+"m");
				
				double wsp=m/v;
				
				for(int i=0; i<50; i++)
				{
					pn.simulate();
					energia[i]=pn.Ek;
					poz=poz-wsp*i-0.025;
					series.getData().add(new XYChart.Data(String.valueOf(i), poz));
				}
				wykres.setTitle("Wykres energii kinetycznej pocisku AK-47");
				wykres.getData().addAll(series);
			}
			else
	    	{
	    		// nic nie zaznaczono
	    		Alert alert = new Alert(Alert.AlertType.WARNING);
	    		alert.initOwner(mainApp.getPrimaryStage());
	            alert.setTitle("Błąd");
	            alert.setContentText("Proszę wprowadzić wszystkie parametry pocisku.");

	            alert.showAndWait();
	    	}
			break;
		}
		}
		
	}

}
