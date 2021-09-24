package model;

public class Bookingdates{
	private String checkin;
	private String checkout;

	public Bookingdates(String checkin, String checkout){
		this.checkin = checkin;
		this.checkout = checkout;
	}


    public void setCheckin(String checkin){
		this.checkin = checkin;
	}

	public String getCheckin(){
		return checkin;
	}

	public void setCheckout(String checkout){
		this.checkout = checkout;
	}

	public String getCheckout(){
		return checkout;
	}
}
