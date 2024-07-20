package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;


public class Main extends Application {
	//Atributo para ser referenciado
	private static Scene mainScene;
	@Override
	public void start(Stage primaryStage) {
		try {
			/*Instanciar FXMLLoader para manipular a tela antes de carregar */
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			/*Ajustando a tela*/
			ScrollPane scrollPane = loader.load();
			//setando altura true
			scrollPane.setFitToHeight(true);
			//setando largura true
			scrollPane.setFitToWidth(true);
			
			//Instanciando a cena passando como argumento principal no caso AnchoPane fazio
			mainScene = new Scene(scrollPane);
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
	//metodo para pegar de forma extena a referencia mainScene
	public static Scene getMainScene() {
		return mainScene;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
