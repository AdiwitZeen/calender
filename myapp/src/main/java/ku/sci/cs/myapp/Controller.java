//Adiwit Yeammaneechai 5710450014

package ku.sci.cs.myapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;

public class Controller {
	
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	public Connection databaseConnection() throws SQLException {
		Connection conn = null;
		try {
			// setup
			Class.forName("org.sqlite.JDBC");
			String dbURL = "jdbc:sqlite:db_calendar.db";
			conn = DriverManager.getConnection(dbURL);
			if (conn != null) {
				System.out.println("Connected to the database.");
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return conn;
	}
	
	//Load Database to table
	public void loadDataFromDB(ObservableList<Calendar> tableList, TableView<Calendar> table) throws SQLException {
		tableList.clear();
		try {
			pst = con.prepareStatement("Select * from db_calendar");
			rs = pst.executeQuery();
			while(rs.next()) {
				tableList.add(new Calendar(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		table.setItems(tableList);
	}
	
	public Calendar getEvent(Integer num, String date, String time, String details) {
		Calendar entry = new Calendar(num, date, time, details);
		return entry;
	}
	
	//Date Format YYYY/MM/DD to DD/MM/YYYY
	public String changeDateFormat(LocalDate dp) {
		// change date format and return date(String)
		LocalDate ld = dp;
		String newdate[] = ld.toString().split("-");
		return newdate[2] + "/" + newdate[1] + "/" + newdate[0];
	}
	
	//Alert Box Type : ERROR, INFOMATION, WARNING
	public void alertBox(Alert.AlertType alertType, String title, String header, String content) {
		Alert alert = new Alert(alertType);
		alert.initOwner(Main.getPrimaryStage());
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	//Confirm Box
	public Optional<ButtonType> alertConfirm(String title, String header, String content) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.initOwner(Main.getPrimaryStage());
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		Optional<ButtonType> rs = alert.showAndWait();
		return rs;
	}
	
	//SAVE CONTROL
	public void saveEvent(Calendar c) throws SQLException {
		int num = 0;
		pst = con.prepareStatement("Select Max(num) from db_calendar");
		rs = pst.executeQuery();
		while(rs.next()) {
			num = rs.getInt(1)+1;	
		}
		String sql = "INSERT INTO db_calendar(num, date, time, details) VALUES(?,?,?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, num);
			pst.setString(2, c.getDate());
			pst.setString(3, c.getTime());
			pst.setString(4, c.getDetail());
			int i = pst.executeUpdate();
			if(i == 1) {
				System.out.println("Add Event");
				System.out.println("No: " + c.getNumber() + " Date:" + c.getDate() + " Time: " + c.getTime() + " Details:" + c.getDetail());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		finally {
			pst.close();
		}
	}
	
	//EDIT CONTROL
	public void editEvent(int num, Calendar c) throws SQLException {
		String sql = "update db_calendar set date=? , time=? , details=? where num = "+num;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, c.getDate());
			pst.setString(2, c.getTime());
			pst.setString(3, c.getDetail());
			pst.executeUpdate();
			System.out.println("Update Event");
			System.out.println("No: " + c.getNumber() + " Date:" + c.getDate() + " Time: " + c.getTime() + " Details:" + c.getDetail());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		finally {
			pst.close();
		}
	}
	
	//DELETE CONTROL
	public void deleteEvent(int num) throws SQLException {
		String sql = "DELETE FROM db_calendar WHERE num = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, num);
			pst.executeUpdate();
			System.out.println("Delete Event");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		finally {
			pst.close();
		}
	}

}
