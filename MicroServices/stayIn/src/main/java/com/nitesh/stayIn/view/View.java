package com.nitesh.stayIn.view;

public class View {
	public static class UserView {
	}

	public static class RentMasterWithUserView extends UserView {
	}

	public static class UserDetailsWithUserView extends UserView {

	}

	public static class UserDetailsWithBookingView extends UserDetailsWithUserView {

	}

	public static class BookingView extends UserDetailsWithUserView {

	}
}
