package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;
import model.servicies.DepartmentService;

public class DepartmentListController implements Initializable{
	
	private DepartmentService service;
	
	private ObservableList<Department> obsList;
	
	@FXML
	private TableView<Department> tableViewDepartment;
	
	@FXML
	private TableColumn<Department, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Department,String> tableColumnName;
	
	@FXML
	private Button btNew;
	
	@FXML
	public void onBtNewAction() {
		System.out.println("onBtNewAction");
	}
	/*injetando a injeção de dependencia*/
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}

	private void initializeNodes() {
		/*inicializando o comportamento das calunas padrao javaFx
		 * id e name são capturados do get das Classes */
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		/*Macete para ajustar tela department*/
		Stage stage = (Stage)Main.getMainScene().getWindow();
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
		
		
	}
	/*Responsavél por Acessar o serviço, carregar os Departemento
	 * e jogar os departamentos no obsList*/
	public void updateTableView() {
		//testando no caso de não entrar com um service
		if(service == null) {
			throw new IllegalStateException("Service é nulo");
			
		}
		//atribuindo a list dados do service.findAll
		List<Department> list = service.findAll();
		/*Carregando list dentro do obsList para que possa ser atribuido
		para um atributo da View*/ 
		obsList = FXCollections.observableArrayList(list);
		//setando ao atributo tableViewDepartmet o obsList
		tableViewDepartment.setItems(obsList);
		
	}

}
