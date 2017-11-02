//Adiwit Yeammaneechai 5710450014

package ku.sci.cs.myapp;

import java.sql.Connection;
import java.sql.SQLException;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class DatabaseHandler {
	
	private DatabaseController finder;
	
	public DatabaseHandler(DatabaseController finder) {
		this.finder = finder;
	}
	
	public Connection databaseConnection() throws SQLException {
		return finder.databaseConnection();
	}
	
	public void loadDataFromDB(ObservableList<Calendar> tableList, TableView<Calendar> table) throws SQLException{
		finder.loadDataFromDB(tableList, table);
	}
	
	public void saveEvent(Calendar c) throws SQLException{
		finder.saveEvent(c);
	}
	
	public void editEvent(int num, Calendar c) throws SQLException{
		finder.editEvent(num, c);
	}
	
	public void deleteEvent(int num) throws SQLException{
		finder.deleteEvent(num);
	}
	
	public void searchEvent(ObservableList<Calendar> tableList, TableView<Calendar> table, String date) throws SQLException {
		finder.searchEvent(tableList, table, date);
	}


}
