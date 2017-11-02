//Adiwit Yeammaneechai 5710450014

package ku.sci.cs.myapp;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class View implements Initializable {

	// Initialize
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setDataTable();
		setComboBox();
		//setEditText();
	}

	private void setComboBox() {
		hourBox.setItems(hourList);
		minBox.setItems(minList);
		dayBox.setItems(dayList);
	}

	private void setDataTable() {
		try {
			control.con = control.databaseConnection();
			setCellTable();
			control.loadDataFromDB(tableList, table);
			table.setItems(tableList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Set column header
	private void setCellTable() {
		number.setCellValueFactory(new PropertyValueFactory<Calendar, Integer>("number"));
		date.setCellValueFactory(new PropertyValueFactory<Calendar, String>("date"));
		time.setCellValueFactory(new PropertyValueFactory<Calendar, String>("time"));
		detail.setCellValueFactory(new PropertyValueFactory<Calendar, String>("detail"));
	}
	
	private void setEditText() {
		table.setOnMousePressed(event -> {
			textArea.setText(table.getSelectionModel().getSelectedItem().getDetail());	
		});
	}

	private void clearForm() {
		table.getSelectionModel().select(null);
		dayBox.setDisable(true);
		dailyButt.setSelected(false);
		weeklyButt.setSelected(false);
		monthlyButt.setSelected(false);
		dp.setDisable(false);
		dp.getEditor().clear();
		hourBox.getSelectionModel().clearSelection();
		minBox.getSelectionModel().clearSelection();
		textArea.clear();
	}

	// CLEAR BUTTON ACTION
	public void clearDataInForm(ActionEvent event) {
		try {
			clearForm();
			System.out.println("Clear Form");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// RADIO BUTTON ACTION
	public void radioSelect(ActionEvent event) {
		if (dailyButt.isSelected()) {
			dp.setDisable(true);
			dayBox.setDisable(true);
		} else if (weeklyButt.isSelected()) {
			dp.setDisable(true);
			dayBox.setDisable(false);

		} else if (monthlyButt.isSelected()) {
			dp.setDisable(false);
			dayBox.setDisable(true);
		}
	}

	// SAVE BUTTON ACTION
	public void saveDate(ActionEvent event) throws SQLException {
		try {
			if (hourBox.getSelectionModel().isEmpty() || minBox.getSelectionModel().isEmpty() || textArea.getText().equals("")) {
				control.alertBox(AlertType.INFORMATION, "Empty Field", "Found empty field", "Please, check again");
			} else {
				// Daily
				if (dailyButt.isSelected()) {
					control.saveEvent(control.getEvent(1, "Daily", hourBox.getValue() + ":" + minBox.getValue(), textArea.getText()));
					setCellTable();
					control.loadDataFromDB(tableList, table);
					clearForm();
					System.out.println("Save Success!!");
				}
				// Weekly
				else if (weeklyButt.isSelected()) {
					control.saveEvent(control.getEvent(1, control.weeklyFormat(dayBox), hourBox.getValue() + ":" + minBox.getValue(), textArea.getText()));
					setCellTable();
					control.loadDataFromDB(tableList, table);
					clearForm();
					System.out.println("Save Success!!");
				}
				// Monthly
				else if (monthlyButt.isSelected()) {
					control.saveEvent(control.getEvent(1, control.monthlyFormat(dp.getValue()), hourBox.getValue() + ":" + minBox.getValue(), textArea.getText()));
					setCellTable();
					control.loadDataFromDB(tableList, table);
					clearForm();
					System.out.println("Save Success!!");
				} else {
					control.saveEvent(control.getEvent(1, control.changeDateFormat(dp.getValue()), hourBox.getValue() + ":" + minBox.getValue(), textArea.getText()));
					setCellTable();
					control.loadDataFromDB(tableList, table);
					clearForm();
					System.out.println("Save Success!!");
				}
			}
		} catch (Exception e) {
			control.alertBox(AlertType.ERROR, "Date Picker Error", "Something wrong", "Please, select date again.");
		}
	}

	// EDIT BUTTON ACTION
	public void updateData(ActionEvent event) throws SQLException {
		int index = table.getSelectionModel().getSelectedIndex();
		int num = 0;
		Calendar c = table.getSelectionModel().getSelectedItem();
		if (index >= 0) {
			try {
				if (hourBox.getSelectionModel().isEmpty() || minBox.getSelectionModel().isEmpty() || textArea.getText().equals("")) {
					control.alertBox(AlertType.INFORMATION, "Empty Field", "Found empty field", "Please, check again");
				} else {
					if (control.alertConfirm("Edit Event", "Do you want to edit this event?", "").get() == ButtonType.OK) {
						// Daily
						if (dailyButt.isSelected()) {
							num = c.getNumber();
							control.editEvent(num, control.getEvent(num, "Daily", hourBox.getValue() + ":" + minBox.getValue(), textArea.getText()));
							setCellTable();
							control.loadDataFromDB(tableList, table);
							clearForm();
							System.out.println("Edit Success!!");
						}
						// Weekly
						else if (weeklyButt.isSelected()) {
							num = c.getNumber();
							control.editEvent(num, control.getEvent(num, control.weeklyFormat(dayBox), hourBox.getValue() + ":" + minBox.getValue(), textArea.getText()));
							setCellTable();
							control.loadDataFromDB(tableList, table);
							clearForm();
							System.out.println("Edit Success!!");
						}
						// Monthly
						else if (monthlyButt.isSelected()) {
							num = c.getNumber();
							control.editEvent(num, control.getEvent(num, control.monthlyFormat(dp.getValue()), hourBox.getValue() + ":" + minBox.getValue(), textArea.getText()));
							setCellTable();
							control.loadDataFromDB(tableList, table);
							clearForm();
							System.out.println("Edit Success!!");
						} else {
							num = c.getNumber();
							control.editEvent(num, control.getEvent(num, control.changeDateFormat(dp.getValue()), hourBox.getValue() + ":" + minBox.getValue(), textArea.getText()));
							setCellTable();
							control.loadDataFromDB(tableList, table);
							clearForm();
							System.out.println("Edit Success!!");
						}
					} else {
						System.out.println("Edit Cancel");
					}
				}
			} catch (SQLException e) {
				control.alertBox(AlertType.ERROR, "Date Picker Error", "Something wrong", "Please, select date again.");
			}
		} else {
			control.alertBox(AlertType.INFORMATION, "No Selection", "No Event Selected", "Please, select an event in the table.");
		}
	}

	// DELETE BUTTON ACTION
	public void deleteData(ActionEvent event) throws SQLException {
		int index = table.getSelectionModel().getSelectedIndex();
		int num = 0;
		Calendar c = table.getSelectionModel().getSelectedItem();
		if (index >= 0) {
			try {
				if (control.alertConfirm("Delete Event", "Do you want to delete this event?", "").get() == ButtonType.OK) {
					num = c.getNumber();
					control.deleteEvent(num);
					setCellTable();
					control.loadDataFromDB(tableList, table);
					clearForm();
					System.out.println("Delete Success!!");
				} else {
					System.out.println("Delete Cancel");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			control.alertBox(AlertType.INFORMATION, "No Selection", "No Event Selected", "Please, select an event in the table.");
		}
	}

	// SEARCH BUTTON ACTION : search data from date
	public void searchData(ActionEvent event) {
		try {
			if (searchField.getText().equals("")) {
				control.alertBox(AlertType.INFORMATION, "", "Search field is empty", "Please, fill up.");
			} else {
				control.searchEvent(tableList, table, searchField.getText());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// LOOK ALL EVENTS
	public void lookAllData(ActionEvent event) {
		try {
			control.loadDataFromDB(tableList, table);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ObservableList : value in ComboBox and Table
	ObservableList<String> minList = FXCollections.observableArrayList("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59");

	ObservableList<String> hourList = FXCollections.observableArrayList("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");

	ObservableList<String> dayList = FXCollections.observableArrayList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");

	ObservableList<Calendar> tableList = FXCollections.observableArrayList();

	Controller control = new Controller();

	@FXML
	private TableView<Calendar> table;
	@FXML
	private TableColumn<Calendar, Integer> number;
	@FXML
	private TableColumn<Calendar, String> date;
	@FXML
	private TableColumn<Calendar, String> time;
	@FXML
	private TableColumn<Calendar, String> detail;
	@FXML
	private TextArea textArea;
	@FXML
	private TextField searchField;
	@FXML
	private DatePicker dp;
	@FXML
	private Button saveButton;
	@FXML
	private Button editButton;
	@FXML
	private Button deleteButton;
	@FXML
	private Button clearButton;
	@FXML
	private Button searchButton;
	@FXML
	private Button lookAllButton;
	@FXML
	private ComboBox<String> hourBox;
	@FXML
	private ComboBox<String> minBox;
	@FXML
	private ComboBox<String> dayBox;
	@FXML
	private RadioButton dailyButt;
	@FXML
	private RadioButton weeklyButt;
	@FXML
	private RadioButton monthlyButt;

}
