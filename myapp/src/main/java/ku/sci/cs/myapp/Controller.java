//Adiwit Yeammaneechai 5710450014

package ku.sci.cs.myapp;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class Controller {

	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	ApplicationContext context = new ClassPathXmlApplicationContext("DatabaseManager.xml");
	DatabaseHandler db = (DatabaseHandler) context.getBean("lister");

	public Connection databaseConnection() throws SQLException {
		return db.databaseConnection();
	}

	// Load Database to table
	public void loadDataFromDB(ObservableList<Calendar> tableList, TableView<Calendar> table) throws SQLException {
		db.loadDataFromDB(tableList, table);
	}

	public Calendar getEvent(Integer num, String date, String time, String details) {
		Calendar entry = new Calendar(num, date, time, details);
		return entry;
	}

	// Date Format YYYY/MM/DD to DD/MM/YYYY
	public String changeDateFormat(LocalDate dp) {
		// change date format and return date(String)
		LocalDate ld = dp;
		String newdate[] = ld.toString().split("-");
		return newdate[2] + "/" + newdate[1] + "/" + newdate[0];
	}

	// Weekly Format
	public String weeklyFormat(ComboBox<String> dayBox) {
		return "Every " + dayBox.getValue();
	}

	// Monthly Format
	public String monthlyFormat(LocalDate dp) {
		LocalDate ld = dp;
		String newdate[] = ld.toString().split("-");
		return "Every " + newdate[2] + " of the month";
	}

	// Alert Box Type : ERROR, INFOMATION, WARNING
	public void alertBox(Alert.AlertType alertType, String title, String header, String content) {
		Alert alert = new Alert(alertType);
		alert.initOwner(Main.getPrimaryStage());
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}

	// Confirm Box
	public Optional<ButtonType> alertConfirm(String title, String header, String content) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.initOwner(Main.getPrimaryStage());
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		Optional<ButtonType> rs = alert.showAndWait();
		return rs;
	}

	// SAVE CONTROL
	public void saveEvent(Calendar c) throws SQLException {
		db.saveEvent(c);
	}

	// EDIT CONTROL
	public void editEvent(int num, Calendar c) throws SQLException {
		db.editEvent(num, c);
	}

	// DELETE CONTROL
	public void deleteEvent(int num) throws SQLException {
		db.deleteEvent(num);
	}

	// SEARCH CONTROL
	public void searchEvent(ObservableList<Calendar> tableList, TableView<Calendar> table, String date) throws SQLException {
		db.searchEvent(tableList, table, date);
	}

}
