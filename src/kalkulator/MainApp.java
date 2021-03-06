package kalkulator;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Kalkulator balistyczny");
		
		initRootLayout();
		showCalcOverview();
		
	}
	
	public MainApp()
	{
		
	}
	
	// inicjalizacjia widoku podstawowego
	public void initRootLayout()
	{
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	// zaladowanie widoku kalkulatora
	public void showCalcOverview()
	{
		try{
			FXMLLoader loader= new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CalcOverview.fxml"));
			BorderPane calcOverview = (BorderPane) loader.load();
			rootLayout.setCenter(calcOverview);
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public Stage getPrimaryStage() {
		// TODO Auto-generated method stub
		return primaryStage;
	}
}
