package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.servicies.DepartmentService;
/*Essa classe é Observer é Subject ou seja a classe que Emite o evento */
public class DepartmentFormController implements Initializable{
	
	private Department entity;
	
	private DepartmentService service;
	//Lista de objetos escritos interessados em receber eventos 
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
	@FXML
	private TextField txtId;
	
	@FXML
	private TextField txtName;
	
	@FXML
	private Label labelErroName;
	
	@FXML
	private Button btSave;
	
	@FXML
	private Button btCancel;
	
	//injeção de independencia
	public void setDepartment(Department entity) {
		this.entity = entity;
	}
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}
	//Resposavel em escrever listener na lista
	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}
	
	@FXML
	public void onBtSaveAction(ActionEvent event) {
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		try {
			entity = getFormData();
			service.saveOrUpdate(entity);
			//metodo de notificação
			notifyDataChangeListeners();
			
			//Apos salvar os dado fechar a jenela
			Utils.currentsStage(event).close();;
			
		}catch(DbException e) {
			Alerts.showAlerts("Error Saving object", null, e.getMessage(), AlertType.ERROR);
		}
		
	}
	//Responsavel por percorrer a lista e notificando 
	private void notifyDataChangeListeners() {
		for(DataChangeListener listener : dataChangeListeners) {
			listener.ondaDataChanged();
			
		}
		
	}
	/*Resposavel por pegar os dados do digitados no TextField
	 * e adicionar no objeto Department */
	private Department getFormData() {
		
		Department obj = new Department();
		obj.setId(Utils.tryParseToInt(txtId.getText()));
		obj.setName(txtName.getText());
		
		return obj;
	}
	@FXML
	public void onBtCancelAction(ActionEvent event) {
		Utils.currentsStage(event).close();;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializaNode();
		
	}
	//Metodo de restrições
	public void initializaNode() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName, 20);
		
	}
	//Metodo Responsavel por pegar uma entity e popular caixa de texto do formulario
	public void updateFormData() {
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		//String.valueOf converte para string
		txtId.setText(String.valueOf(entity.getId()));
		txtName.setText(entity.getName());
	}

}
