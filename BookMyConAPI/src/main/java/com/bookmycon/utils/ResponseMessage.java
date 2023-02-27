package com.bookmycon.utils;

public enum ResponseMessage {
	
	USER_ADDED_SUCCESSFULLY("User Register Successfully"),
	WRONG_PASSWORD("Wrong password"),
	USER_NOT_FOUND("User not found"),
	INVALID_EMAIL_AND_PASSWORD("Invalid email and password"),
	USER_ALREADY_EXISTS_WITH_THIS_EMAIL("Email is already taken"),
	USER_FAILED_TO_UPDATE_PROFILE("Failed to update profile"),
	USER_UPDATED_SUCCESSFULLY("User Updated Successfully"),
	USER_ADDED_FAILED("User Added Failed"),
	AUDITORIUM_UPDATED("Auditorium Updated Successfully"),
	AUDITORIUM_ADDED("Auditorium Added Successfully"),
	GETTING_AUDITORIUM("get the auditorim ");
	
   private String message;
	
	private ResponseMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}

}
