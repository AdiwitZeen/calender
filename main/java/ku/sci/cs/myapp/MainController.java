package ku.sci.cs.myapp;

import java.awt.Label;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController implements Initializable {
	
	@FXML 
	private TableView<Calendar> table;
	@FXML 
	private TableColumn<Calendar, String> date;
	@FXML 
	private TableColumn<Calendar, String> time;
	@FXML
	private TableColumn<Calendar, String> detail;
	@FXML
	private TextArea textArea;
	@FXML 
	private DatePicker datepick;
	@FXML 
	private Button addButton;
	@FXML 
	private Button deleteButton;
	@FXML 
	private ComboBox<String> hourBox;
	@FXML
	private ComboBox<String> minBox;
	
	//ObservableList
	final ObservableList<String> hourList = FXCollections.observableArrayList("01","02","03","04","05","06","07","08","09","10",
																				"11","12","13","14","15","16","17","18","19","20",
																				"21","22","23","24"
		);
	final ObservableList<String> minList = FXCollections.observableArrayList("00","01","02","03","04","05","06","07","08","09","10",
																				"11","12","13","14","15","16","17","18","19","20",
																				"21","22","23","24","25","26","27","28","29","30",
																				"31","32","33","34","35","36","37","38","39","40",
																				"41","42","43","44","45","46","47","48","49","50",
																				"51","52","53","54","55","56","57","58","59"
																			 );
	final ObservableList<Calendar> tableList = FXCollections.observableArrayList();
	
	//Initialize
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Table
		date.setCellValueFactory(new PropertyValueFactory<Calendar, String>("date"));
		time.setCellValueFactory(new PropertyValueFactory<Calendar, String>("time"));
		detail.setCellValueFactory(new PropertyValueFactory<Calendar, String>("detail"));
		table.setItems(tableList);
		//Combo Box
		hourBox.setItems(hourList);
		minBox.setItems(minList);
	}
	
	private String getDate(){
		LocalDate ld = datepick.getValue();
		String newdate[] = ld.toString().split("-");
		return newdate[2]+"/"+newdate[1]+"/"+newdate[0];	
	}
	
	private void clearForm() {
		textArea.clear();
	}
	
	public void saveDate(ActionEvent event) {
		//System.out.println("save data");
		Calendar entry = new Calendar(getDate(), hourBox.getValue()+" : "+minBox.getValue(), textArea.getText());
		tableList.add(entry);
		clearForm();
	}
	
	public void deleteData(ActionEvent event) {
		//System.out.println("Delete selected data");
		int selectedIndex = table.getSelectionModel().getSelectedIndex();
	    if (selectedIndex >= 0) {
	        table.getItems().remove(selectedIndex);
	    } else {
	        // Nothing selected.
	        Alert alert = new Alert(AlertType.WARNING);
	      //  alert.initOwner(Main.getPrimaryStage());
	        alert.setTitle("No Selection");
	        alert.setHeaderText("No Person Selected");
	        alert.setContentText("Please select a person in the table.");
	        alert.showAndWait();
	    }
	}

}
