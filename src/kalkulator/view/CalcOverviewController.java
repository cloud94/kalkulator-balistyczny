package kalkulator.view;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import kalkulator.MainApp;
import kalkulator.model.Pocisk;
import kalkulator.model.Lot;
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
	private TextField zakres;
	
    @FXML
    private LineChart<String, Double> wykres;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;
    
    @FXML
    private TableView<Lot> tabela;

    @FXML
    private TableColumn<Lot, Integer> odlColumn;

    @FXML
    private TableColumn<Lot, Double> wysColumn;
	
	private MainApp mainApp;

	//konstruktor
	public void Controller(){
		
	}
	
	@FXML
    private void initialize() {
		
		rodzajPocisku.setValue("Wybierz rodzaj pocisku");
		rodzajPocisku.getItems().addAll(
				"AK-47", "M4", "Broń pneumatyczna");
		odlColumn.setCellValueFactory(cellData->
		cellData.getValue().xProperty().asObject());
		
		wysColumn.setCellValueFactory(cellData -> 
	      cellData.getValue().yProperty().asObject());
	
    }
	
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
	
	
	@FXML
	private void onSymulujClick()
	{
		ObservableList<Lot> shotData= FXCollections.observableArrayList();
		
		if(!rodzajPocisku.getSelectionModel().isEmpty())
		{
		int wybor = rodzajPocisku.getSelectionModel().getSelectedIndex();
		switch(wybor)
		{
		// ak-47
		case 0:
			if(masa.getText().isEmpty() ||
					pozycja.getText().isEmpty() ||
					predkosc.getText().isEmpty() ||
					zakres.getText().isEmpty() || (Integer.valueOf(zakres.getText()) > 49))
			{
				System.out.println("fas");
	    		// nic nie zaznaczono
	    		Alert alert = new Alert(Alert.AlertType.WARNING);
	            alert.setTitle("Błąd");
	            alert.setContentText("Proszę wprowadzić/sprawdzić wszystkie parametry pocisku."
	            		+ " Zakres symulacji powinien wynosic od 0 do 49m.");

	            alert.showAndWait();
			}
			else
	    	{

				
				double m = Double.valueOf(masa.getText());
				double poz = Double.valueOf(pozycja.getText());
				double v = Double.valueOf(predkosc.getText());
				int z = Integer.valueOf(zakres.getText());
				
				Pocisk ak = new PociskAK(m, 0, poz, 0);
				
				ak.shot(v);
				double []energia = new double[50];
				
				XYChart.Series series = new XYChart.Series();
				
				series.setName("AK-47, "+m+"g, Vo="+m+"m, Ho="+poz+"m");
				
				double wsp=m/v;
				
				for(int i=0; i<=z; i++)
				{
					ak.simulate();
					energia[i]=ak.Ek;
					poz=poz-wsp*i-0.011;
					series.getData().add(new XYChart.Data(String.valueOf(i), poz));
					shotData.add(new Lot(i, poz));
				}
				wykres.setTitle("Wykres energii kinetycznej pocisku AK-47");
				wykres.getData().addAll(series);
				tabela.setItems(shotData);
	    	}
	
			break;
		// m4
		case 1:
			
			if(!masa.getText().isEmpty() ||
					!pozycja.getText().isEmpty() ||
					!predkosc.getText().isEmpty() ||
					!zakres.getText().isEmpty() || (Integer.valueOf(zakres.getText()) < 49))
			{
				double m = Double.valueOf(masa.getText());
				double poz = Double.valueOf(pozycja.getText());
				double v = Double.valueOf(predkosc.getText());
				double z = Integer.valueOf(zakres.getText());
				
				
				Pocisk m4 = new PociskM4(m, 0, poz, 0);
				
				m4.shot(v);
				double []energia = new double[50];
				
				XYChart.Series series = new XYChart.Series();
				
				series.setName("M4, "+m+"g, Vo="+m+"m, Ho="+poz+"m");
				
				double wsp=m/v;
				
				for(int i=0; i<=z; i++)
				{
					m4.simulate();
					energia[i]=m4.Ek;
					poz=poz-wsp*i-0.015;
					series.getData().add(new XYChart.Data(String.valueOf(i), poz));
					shotData.add(new Lot(i, poz));
				}
				wykres.setTitle("Wykres energii kinetycznej pocisku M4");
				wykres.getData().addAll(series);
				tabela.setItems(shotData);
			}
			else
	    	{
	    		// nic nie zaznaczono
	    		Alert alert = new Alert(Alert.AlertType.WARNING);
	            alert.setTitle("Błąd");
	            alert.setContentText("Proszę wprowadzić/sprawdzić wszystkie parametry pocisku."
	            		+ " Zakres symulacji powinien wynosic od 0 do 49m.");

	            alert.showAndWait();
	    	}
			break;
		// pneumatyczna
		case 2:
			if(!masa.getText().isEmpty() ||
					!pozycja.getText().isEmpty() ||
					!predkosc.getText().isEmpty() ||
					!zakres.getText().isEmpty() || (Integer.valueOf(zakres.getText()) < 49))
			{
				double m = Double.valueOf(masa.getText());
				double poz = Double.valueOf(pozycja.getText());
				double v = Double.valueOf(predkosc.getText());
				double z = Integer.valueOf(zakres.getText());
				
				Pocisk pn = new PociskPneumatyczny(m, 0, poz, 0);
				
				pn.shot(v);
				double []energia = new double[50];
				
				XYChart.Series series = new XYChart.Series();
				
				series.setName("P. pneum., "+m+"g, Vo="+m+"m, Ho="+poz+"m");
				
				double wsp=m/v;
				
				for(int i=0; i<=z; i++)
				{
					pn.simulate();
					energia[i]=pn.Ek;
					poz=poz-wsp*i-0.025;
					series.getData().add(new XYChart.Data(String.valueOf(i), poz));
					shotData.add(new Lot(i, poz));
				}
				wykres.setTitle("Wykres energii kinetycznej pocisku AK-47");
				wykres.getData().addAll(series);
				tabela.setItems(shotData);
			}
			else
	    	{
	    		// nic nie zaznaczono
	    		Alert alert = new Alert(Alert.AlertType.WARNING);
	            alert.setTitle("Błąd");
	            alert.setContentText("Proszę wprowadzić/sprawdzić wszystkie parametry pocisku."
	            		+ " Zakres symulacji powinien wynosic od 0 do 49m.");

	            alert.showAndWait();
	    	}
			break;
		}
		}
		else
		{	// nie zaznaczono rodzaju pocisku
			Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Błąd");
            alert.setContentText("Proszę wybrać rodzaj pocisku.");

            alert.showAndWait();
		}
		
	}

}
