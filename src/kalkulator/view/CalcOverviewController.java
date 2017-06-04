package kalkulator.view;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
	
	private ObservableList<Lot> shotData= FXCollections.observableArrayList();

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
				
				// zapis konfiguracji do pliku
				File file = new File("conf.txt");
				saveConf(file, m, poz, v, z);
				
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
				
				// zapis konfiguracji do pliku
				File file = new File("conf.txt");
				saveConf(file, m, poz, v, z);
				
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
				
				// zapis konfiguracji do pliku
				File file = new File("conf.txt");
				saveConf(file, m, poz, v, z);
				
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
	
	private void saveConf(File file, double m, double poz, double v, double z) {
		// TODO Auto-generated method stub
		try
		{
			BufferedWriter outWriter = new BufferedWriter(new FileWriter(file));
			
			outWriter.write(masa.getText());
			outWriter.newLine();
			outWriter.write(pozycja.getText());
			outWriter.newLine();
			outWriter.write(predkosc.getText());
			outWriter.newLine();
			outWriter.write(zakres.getText());

			outWriter.close();
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
	}

	@FXML
	private void onZapiszWynikiClick()
	{
		Stage secondaryStage = new Stage();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Zapisz wyniki do pliku");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		if(shotData.isEmpty())
		{
			Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Błąd");
            alert.setContentText("Tabela wyników jest pusta. Proszę wykonać symulację.");

            alert.showAndWait();
		}
		else
		{
			File file = fileChooser.showSaveDialog(secondaryStage);
			if(file!=null)
			{
				saveFile(shotData, file);
			}
		}
		
	}

	private void saveFile(ObservableList<Lot> shotData, File file) {
		// TODO Auto-generated method stub
		try
		{
			BufferedWriter outWriter = new BufferedWriter(new FileWriter(file));
			
			String bron = "";
			
			switch(rodzajPocisku.getSelectionModel().getSelectedIndex())
			{
			case 0: bron = "AK-47"; break;
			case 1: bron = "M-4"; break;
			case 2: bron = "Broń pneumatyczna"; break;
			}
			
			outWriter.write("Symulacja pocisku "+bron);
			outWriter.newLine();
			outWriter.write("Masa: "+masa.getText()+"g, Prędkość początkowa: "+
			predkosc.getText()+"m/s, Wysokość początkowa: "+pozycja.getText()+"m");
			outWriter.newLine();
			
			for(Lot lot : shotData)
			{
				outWriter.write("Odległość: "+
			String.valueOf(lot.xProperty().intValue())+"m\tWysokosc: "+
						String.valueOf(lot.yProperty().doubleValue())+"m");
				outWriter.newLine();
			}
			outWriter.close();
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
