package model;


public class Booking{
	private String firstname;
	private String lastname;
    private int totalprice;
	private boolean depositpaid;
    private BookingDates bookingdates;
	private String additionalneeds;
	public Booking(String firstname, String lastname, int totalprice, boolean depositpaid, String bookingdates_checkin, String bookingdates_checkout, String additionalneeds ) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.totalprice = totalprice;
		this.depositpaid = depositpaid;
		this.bookingdates = new BookingDates(bookingdates_checkin, bookingdates_checkout);
		this.additionalneeds = additionalneeds;
	}

    public void setFirstname(String firstname){
		this.firstname = firstname;
	}

	public String getFirstname(){
		return firstname;
	}

	public void setAdditionalneeds(String additionalneeds){
		this.additionalneeds = additionalneeds;
	}

	public String getAdditionalneeds(){
		return additionalneeds;
	}

	public void setBookingdates(BookingDates bookingdates){
		this.bookingdates = bookingdates;
	}

	public BookingDates getBookingdates(){
		return bookingdates;
	}

	public void setTotalprice(int totalprice){
		this.totalprice = totalprice;
	}

	public int getTotalprice(){
		return totalprice;
	}

	public void setDepositpaid(boolean depositpaid){
		this.depositpaid = depositpaid;
	}

	public boolean isDepositpaid(){
		return depositpaid;
	}

	public void setLastname(String lastname){
		this.lastname = lastname;
	}

	public String getLastname(){
		return lastname;
	}
}
