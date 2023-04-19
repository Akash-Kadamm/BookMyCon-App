package com.bookmycon.dto;

import com.bookmycon.model.Guest;
import com.bookmycon.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;


public class GuestResponseDTO {

	private int guestId;
	private String guestName;
	private String guestCompany;
	private String guestEmail;
	private String guestMobileNo;
	private String thumbnail;

	private User user;

	public GuestResponseDTO(){

	}

	public GuestResponseDTO(int guestId, String guestName, String guestCompany, String guestEmail, String guestMobileNo, String thumbnail, User user) {
		this.guestId = guestId;
		this.guestName = guestName;
		this.guestCompany = guestCompany;
		this.guestEmail = guestEmail;
		this.guestMobileNo = guestMobileNo;
		this.thumbnail = thumbnail;
		this.user = user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public int getGuestId() {
		return guestId;
	}

	public void setGuestId(int guestId) {
		this.guestId = guestId;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public String getGuestCompany() {
		return guestCompany;
	}

	public void setGuestCompany(String guestCompany) {
		this.guestCompany = guestCompany;
	}

	public String getGuestEmail() {
		return guestEmail;
	}

	public void setGuestEmail(String guestEmail) {
		this.guestEmail = guestEmail;
	}

	public String getGuestMobileNo() {
		return guestMobileNo;
	}

	public void setGuestMobileNo(String guestMobileNo) {
		this.guestMobileNo = guestMobileNo;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	@Override
	public String toString() {
		return "GuestResponseDTO{" +
				"guestId=" + guestId +
				", guestName='" + guestName + '\'' +
				", guestCompany='" + guestCompany + '\'' +
				", guestEmail='" + guestEmail + '\'' +
				", guestMobileNo='" + guestMobileNo + '\'' +
				", thumbnail='" + thumbnail + '\'' +
				", user=" + user +
				'}';
	}

	
	/*public static GuestResponseDTO fromEntity(Guest entity)
	{
		GuestResponseDTO guestResponseDTO = new GuestResponseDTO();
		BeanUtils.copyProperties(entity, guestResponseDTO);
		return guestResponseDTO;
	}*/
}
