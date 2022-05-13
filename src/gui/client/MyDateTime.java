package gui.client;

public class MyDateTime {
	private String date;
	private String time;
	private String dateTime;
	
	//Constructor receives two Strings and initials associated fields.
	public MyDateTime(String date, String time) {
		this.date = date;
		this.time = time;
	}
	
	//Constructor receives one String and initials associated fields.
	public MyDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
	public void FromOneToTwo() {
		dateTime.replace(' ', 'T');

	
	}
	
	public void FromTwoToOne() {
		
	}
	
	//*******************
	//*Getters & Setters*
	//*******************
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
	
}
