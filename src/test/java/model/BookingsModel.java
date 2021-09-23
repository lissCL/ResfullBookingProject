package model;

import java.util.List;

public class BookingsModel{
	private List<BookingsModelItem> bookingsModel;

	public void setBookingsModel(List<BookingsModelItem> bookingsModel){
		this.bookingsModel = bookingsModel;
	}

	public List<BookingsModelItem> getBookingsModel(){
		return bookingsModel;
	}
}