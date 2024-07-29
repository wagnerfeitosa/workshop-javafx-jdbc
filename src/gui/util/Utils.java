package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {
	//Metodo realiza a captura do evento de um botao retornando em um Stage
	public static Stage currentsStage(ActionEvent event) {
		return (Stage) ((Node)event.getSource()).getScene().getWindow();
	}

}
