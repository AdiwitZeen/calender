//Adiwit Yeammaneechai 5710450014

package ku.sci.cs.myapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public interface DatabaseController {

	public Connection con = null;
	public PreparedStatement pst = null;
	public ResultSet rs = null;
	
	Connection databaseConnection() throws SQLException;;
	void loadDataFromDB(ObservableList<Calendar> tableList, TableView<Calendar> table) throws SQLException;
	void saveEvent(Calendar c) throws SQLException;
	void editEvent(int num, Calendar c) throws SQLException;
	void deleteEvent(int num) throws SQLException;
	void searchEvent(ObservableList<Calendar> tableList, TableView<Calendar> table, String date) throws SQLException;

	
}
