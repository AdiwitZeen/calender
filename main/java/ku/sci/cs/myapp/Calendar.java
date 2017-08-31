package ku.sci.cs.myapp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

public class Calendar {
	
	private final SimpleStringProperty date;
	private final SimpleStringProperty time;
	private final SimpleStringProperty detail;

	public Calendar(String date, String time, String detail) {
		super();
		this.date = new SimpleStringProperty(date);
		this.time = new SimpleStringProperty(time);
		this.detail = new SimpleStringProperty(detail);
	}

	public String getDate() {
		return date.get();
	}

	public String getTime() {
		return time.get();
	}

	public String getDetail() {
		return detail.get();
	}
	
}
