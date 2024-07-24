package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.servicies.DepartmentService;

public class MainViewController implements Initializable{
	
	@FXML
	private MenuItem menuItemSeller;
	
	@FXML
	private MenuItem menuItemDepartment;
	
	@FXML
	private MenuItem menuItemAbout;
	
	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	}
	
	@FXML
	public void onMenuItemDepartmentAction() {
		loadView2("/gui/DepartmentList.fxml");
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/Aboult.fxml");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	/*symchronized garante que o metodo não será interrompido pela multthreds
	 * Esse metodo laodView carrenga uma View*/
	private synchronized void loadView(String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVbox = loader.load();
			//pegando a view de referencia do Main
			Scene mainScene = Main.getMainScene();
			/*metodo getRoot pega o primeiro elemento da view que no caso é ScrollPane*/
			VBox mainVBox = (VBox)((ScrollPane) mainScene.getRoot()).getContent();
			//pegando o primeiro elemento da view mainVBox
			Node mainMenu = mainVBox.getChildren().get(0);
			//Limpando todos os filho mainVBox
			mainVBox.getChildren().clear();
			//Adicionando o mainMenu
			mainVBox.getChildren().add(mainMenu);
			//assAll adiciona uma coleção no caso filhos do newVbox
			mainVBox.getChildren().addAll(newVbox.getChildren());
			
		}catch(IOException e) {
			Alerts.showAlerts("IOException", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
	private synchronized void loadView2(String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVbox = loader.load();
			//pegando a view de referencia do Main
			Scene mainScene = Main.getMainScene();
			/*metodo getRoot pega o primeiro elemento da view que no caso é ScrollPane*/
			VBox mainVBox = (VBox)((ScrollPane) mainScene.getRoot()).getContent();
			//pegando o primeiro elemento da view mainVBox
			Node mainMenu = mainVBox.getChildren().get(0);
			//Limpando todos os filho mainVBox
			mainVBox.getChildren().clear();
			//Adicionando o mainMenu
			mainVBox.getChildren().add(mainMenu);
			//assAll adiciona uma coleção no caso filhos do newVbox
			mainVBox.getChildren().addAll(newVbox.getChildren());
			
			/*Acessando o controller da view loader*/
			DepartmentListController controller = loader.getController();
			//injetando a dependencia 
			controller.setDepartmentService(new DepartmentService());	
			//Atualizando os dados na tela tableView
			controller.updateTableView();
			
		}catch(IOException e) {
			Alerts.showAlerts("IOException", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

}
