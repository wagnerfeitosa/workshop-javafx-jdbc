package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			/*Instanciar FXMLLoader para manipular a tela antes de carregar */
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			//Chamando o loader.load() paracarregar a view
			Parent parent = loader.load();
			//Instanciando a cena passando como argumento principal no caso AnchoPane fazio
			Scene mainScene = new Scene(parent);
			//setando a cena principal
			primaryStage.setScene(mainScene);
			//definindo titulo
			primaryStage.setTitle("JavaFX application");
			//executando
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
