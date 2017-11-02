//Adiwit Yeammaneechai 5710450014

package ku.sci.cs.myapp;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Calendar {
	
	private final SimpleIntegerProperty number;
	private final SimpleStringProperty date;
	private final SimpleStringProperty time;
	private final SimpleStringProperty detail;

	public Calendar(Integer number, String date, String time, String detail) {
		super();
		this.number = new SimpleIntegerProperty(number);
		this.date = new SimpleStringProperty(date);
		this.time = new SimpleStringProperty(time);
		this.detail = new SimpleStringProperty(detail);
	}
	
	public Integer getNumber() {
		return number.get();
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
